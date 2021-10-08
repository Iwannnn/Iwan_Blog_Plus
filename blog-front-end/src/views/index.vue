<template>
    <div>hello world</div>
</template>
<script>
import { listArticle } from "@/api/blog/article";
export default {
    name: "Article",
    data() {
        return {
            articleList: [],
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                userId: null,
                tittle: null,
                content: null,
                musicId: null,
                categoryId: null,
                avatar: null,
                summary: null,
                pageviews: null,
                likes: null,
            },
        };
    },
    created() {
        this.getList();
    },
    methods: {
        getList() {
            listArticle(this.queryParams).then((res) => {
                console.log(res);
                this.articleList = res.rows;
            });
        },
    },
};
</script>