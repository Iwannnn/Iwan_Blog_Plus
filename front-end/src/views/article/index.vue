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
                <markdown-it-vue
                    class="markdown-it-wrap"
                    :content="this.article.content"
                />
            </el-col>
            <el-col :xs="0" :sm="5" :md="5" :lg="5" :xl="5">
                <a-affix :offset-top="70"> 123 </a-affix>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import { getArticle } from "@/api/blog/article";
import { getMusic } from "@/api/blog/music";
import { Message } from "element-ui";
import marked from "marked";
import MarkdownItVue from "markdown-it-vue";
import "markdown-it-vue/dist/markdown-it-vue.css";
export default {
    components: {
        MarkdownItVue,
    },
    data() {
        return {
            article: {},
            music: [],
        };
    },
    activated() {
        this.getArticleDetail(this.$route.params.articleId);
    },
    methods: {
        getArticleDetail(articleId) {
            if (articleId) {
                getArticle(articleId).then((res) => {
                    this.article = res.data;
                    console.log(this.article.content);
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
    },
};
</script>
<style scoped>
.markdown-it-wrap {
    line-height: 30px;
    text-align: left;
    box-sizing: border-box;
    padding: 40px;
}
</style>