<template>
    <div>111</div>
</template>
<script>
import { getArticle } from "@/api/blog/article";
import { Message } from "element-ui";
export default {
    data() {
        return {
            articleData: {},
        };
    },
    created() {},
    activated() {
        this.getArticleDetail(this.$route.params.articleId);
    },
    methods: {
        getArticleDetail(articleId) {
            if (articleId) {
                getArticle(articleId).then((res) => {
                    console.log(res.data);
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
