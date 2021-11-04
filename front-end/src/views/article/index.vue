<template>
    <div>
        <el-row :gutter="10">
            <el-col :xs="24" :sm="16" :md="16" :lg="16" :xl="16">
                <div
                    class="markdown-content"
                    id="content"
                    v-html="this.article.html"
                />
                <div>
                    <mavon-editor
                        v-model="comment.value"
                        ref="md"
                        placeholder="请输入文档内容..."
                        :boxShadow="false"
                        style="
                            z-index: 1;
                            border: 1px solid #d9d9d9;
                            height: 30vh;
                        "
                        :shortCut="false"
                        :toolbars="toolbars"
                        :defaultOpen="'edit'"
                    />
                </div>
                <div>
                    <Comment :commentList="commentList" :parentId="0" />
                </div>
            </el-col>
            <el-col :xs="0" :sm="8" :md="8" :lg="8" :xl="8">
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
import { listComment } from "@/api/blog/comment";
import { Message } from "element-ui";
import AnchorLink from "@/components/AnchorLink";
import marked from "marked";
import { last } from "lodash";
import Clipboard from "clipboard";
import "@/assets/styles/github.css";
import Comment from "@/components/Comments";
const rendererMD = new marked.Renderer();
marked.setOptions({
    renderer: rendererMD,
    gfm: true,
    tables: true,
    breaks: true,
    pedantic: false,
    sanitize: false,
    smartLists: true,
    smartypants: false,
});
export default {
    components: {
        AnchorLink,
        Comment,
    },
    data() {
        return {
            article: {},
            clipboard: "",
            anchors: [],
            tocItems: [], // 目录递归列表
            index: [],
            commentQueryParams: {
                articleId: null,
            },
            parentId: null,
            commentList: [],
            toolbars: {
                bold: true, // 粗体
                italic: true, // 斜体
                header: true, // 标题
                underline: true, // 下划线
                strikethrough: true, // 中划线
                mark: true, // 标记
                superscript: true, // 上角标
                subscript: true, // 下角标
                quote: true, // 引用
                ol: true, // 有序列表
                ul: true, // 无序列表
                link: true, // 链接
                imagelink: false, // 图片链接
                code: true, // code
                table: true, // 表格
                fullscreen: true, // 全屏编辑
                readmodel: true, // 沉浸式阅读
                htmlcode: true, // 展示html源码
                help: true, // 帮助
                /* 1.3.5 */
                undo: true, // 上一步
                redo: true, // 下一步
                trash: true, // 清空
                save: false, // 保存（触发events中的save事件）
                /* 1.4.2 */
                navigation: true, // 导航目录
                /* 2.1.8 */
                alignleft: true, // 左对齐
                aligncenter: true, // 居中
                alignright: true, // 右对齐
                /* 2.2.1 */
                subfield: true, // 单双栏模式
                preview: true, // 预览
            },
            comment: {
                conetent: null,
                articleId: null,
                parentId: 0,
                commentUserId: 0,
                BecommentUserId: 0,
            },
        };
    },
    destroyed() {
        if (this.clipboard) this.clipboard.destroy();
    },
    created() {
        this.getArticleDetail(this.$route.params.articleId);
    },
    methods: {
        getArticleDetail(articleId) {
            if (articleId) {
                getArticle(articleId).then((res) => {
                    this.article = res.data;
                    this.article.html = this.compiledMarkdown();
                    this.render();
                });
                this.commentQueryParams = {
                    articleId: articleId,
                };
                listComment(this.commentQueryParams).then((res) => {
                    this.commentList = res.rows;
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
                    hljs.highlightBlock(pre[i].querySelector("code"));
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
.toc {
    background-color: rgba(0, 0, 0, 0);
}
.comment {
    line-height: 10px;
}
</style>