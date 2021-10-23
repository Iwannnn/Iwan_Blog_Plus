<template>
    <div>
        <el-row :gutter="10">
            <el-col :xs="0" :sm="5" :md="5" :lg="5" :xl="5">
                <a-affix :offset-top="70">
                    <div class="muisc">
                        <aplayer :audio="music" :lrcType="3" />
                    </div>
                </a-affix>
            </el-col>
            <el-col :xs="24" :sm="0" :md="0" :lg="0" :xl="0">
                <div class="muisc">
                    <aplayer :audio="music" :lrcType="3" fixed />
                </div>
            </el-col>
            <el-col :xs="24" :sm="14" :md="14" :lg="14" :xl="14">
                <div
                    class="markdown-content"
                    id="content"
                    v-html="this.article.html"
                />
            </el-col>
            <el-col :xs="0" :sm="5" :md="5" :lg="5" :xl="5">
                <a-anchor
                    class="toc"
                    affix
                    showInkInFixed
                    :offsetTop="70"
                    v-if="this.tocItems.length > 0"
                >
                    <!-- 递归组件 -->
                    <AnchorLink :list="this.tocItems" />
                </a-anchor>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import { getArticle } from "@/api/blog/article";
import { getMusic } from "@/api/blog/music";
import { Message } from "element-ui";
import AnchorLink from "@/components/AnchorLink";
import marked from "marked";
import { last } from "lodash";
import Clipboard from "clipboard";
import "@/assets/styles/github.css";

const rendererMD = new marked.Renderer();
marked.setOptions({
    renderer: rendererMD,
    gfm: true,
    tables: true,
    breaks: false,
    pedantic: false,
    sanitize: false,
    smartLists: true,
    smartypants: false,
});
export default {
    components: {
        AnchorLink,
    },
    data() {
        return {
            article: {},
            music: [],
            clipboard: "",
            anchors: [],
            tocItems: [], // 目录递归列表
            index: [],
        };
    },
    destroyed() {
        if (this.clipboard) this.clipboard.destroy();
    },
    activated() {
        this.getArticleDetail(this.$route.params.articleId);
    },
    methods: {
        getArticleDetail(articleId) {
            if (articleId) {
                getArticle(articleId).then((res) => {
                    this.article = res.data;
                    this.article.html = this.compiledMarkdown();
                    this.render();
                    getMusic(this.article.musicId).then((res) => {
                        this.music = res.data;
                    });
                });
            } else {
                Message({
                    message: "请从正当方式进入页面",
                    type: "warning",
                });
            }
        },
        compiledMarkdown() {
            rendererMD.heading = (text, level) => {
                const anchor = this.renderToc(text, level); //渲染目录的方法
                return `<h${level} id="${anchor}">${text}</h${level}>`;
            };
            return marked(this.article.content); //content 是markdown的内容
        },
        render() {
            this.$nextTick(() => {
                let content = document.getElementById("content");
                let pre = content.querySelectorAll("pre");
                for (let i = 0; i < pre.length; i++) {
                    //这里应为是script引入的，所以直接调用hljs就行
                    //代码块高亮
                    hljs.highlightElement(pre[i].querySelector("code"));
                    //对代码块加行数
                    hljs.lineNumbersBlock(pre[i].querySelector("code"));
                    // 获取code去除标签，保留code里的内容 复制的时候用到
                    let median = pre[i]
                        .querySelector("code")
                        .innerHTML.replace(/<\/?.+?>/g, "");
                    let res = median.replace(/ /g, "");
                    //添加3个html标签，分别是复制按钮，放大按钮，和一个textarea存放code里的内容
                    let a = `<a class="copy-code" data-clipboard-action="copy" data-clipboard-target="#copy${i}"><i class="iconfont icon-fuzhi"></a>`;
                    let c = `<textarea style="position: absolute;top: -9999px;left: -9999px;z-index: -9999;" id="copy${i}">${res}</textarea>`;
                    //追加标签
                    pre[i]
                        .querySelector("code")
                        .insertAdjacentHTML("afterend", a);
                    pre[i]
                        .querySelector("code")
                        .insertAdjacentHTML("afterend", c);
                    // 给放大按钮添加点击事件
                }
                //绑定复制按钮
                this.clipboard = new Clipboard(".copy-code");
                this.clipboard.on("success", (e) => {
                    this.$message.success("复制成功");
                });
                this.clipboard.on("error", (e) => {
                    this.$message.error("复制失败");
                });
            });
        },
        //渲染目录开始
        renderToc(text, level) {
            const anchor = `toc${level}${++this.index}`;
            this.anchors.push(anchor);
            const item = { anchor, level, text };
            const items = this.tocItems;

            if (items.length === 0) {
                // 第一个 item 直接 push
                items.push(item);
            } else {
                let lastItem = last(items); // 最后一个 item
                if (item.level > lastItem.level) {
                    // item 是 lastItem 的 children
                    for (let i = lastItem.level + 1; i <= 6; i++) {
                        const { children } = lastItem;
                        if (!children) {
                            // 如果 children 不存在
                            lastItem.children = [item];
                            break;
                        }
                        lastItem = last(children); // 重置 lastItem 为 children 的最后一个 item
                        if (item.level <= lastItem.level) {
                            // item level 小于或等于 lastItem level 都视为与 children 同级
                            children.push(item);
                            break;
                        }
                    }
                } else {
                    // 置于最顶级
                    items.push(item);
                }
            }
            return anchor;
        },
    },
};
</script>
<style scoped>
.markdown-content {
    line-height: 30px;
    text-align: left;
    box-sizing: border-box;
    padding: 40px;
}
</style>