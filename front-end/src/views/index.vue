<template>
    <div>
        <el-row :gutter="20">
            <el-col :xs="24" :sm="16" :md="16" :lg="16" :xl="16">
                <el-card
                    v-for="article in articleList"
                    :key="article.articleId"
                    shadow="hover"
                    class="card"
                >
                    <router-link
                        tag="div"
                        :to="'/article/' + article.articleId"
                    >
                        <div>{{ article.tittle }}</div>
                        <div v-if="article.avatar">
                            <img v-bind:src="article.avatar" />
                        </div>
                        <div>{{ article.summary }}</div>
                        <div>
                            <span>{{ article.createTime }}</span>
                            <span>{{ article.pageviews }}</span>
                            <span>{{ article.likes }}</span>
                        </div>
                    </router-link>
                </el-card>
            </el-col>
            <el-col :xs="0" :sm="8" :md="8" :lg="8" :xl="8">
                <a-affix :offset-top="70"> class tag </a-affix>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import { listAplayerMusic } from "@/api/blog/music";
import { listArticle, likeArticle, dislikeArticle } from "@/api/blog/article";
import { listCategory } from "@/api/blog/category";
import { listTag } from "@/api/blog/tag";
export default {
    data() {
        return {
            articleList: [],
            categoryList: [],
            tagList: [],
            articleQuery: {
                pageNum: 1,
                pageSize: 10,
            },
        };
    },
    created() {
        this.getList();
    },
    methods: {
        getList() {
            listArticle().then((res) => {
                this.articleList = res.rows;
            });
            listAplayerMusic().then((res) => {
                this.musicList = res.data;
            });
            listCategory().then((res) => {
                this.categoryList = res.rows;
            });
            listTag().then((res) => {
                this.tagList = res.rows;
            });
        },
        getMoreArticle() {
            this.articleQuery.pageNum += 1;
            listArticle(this.articleQuery).then((res) => {});
        },
        testLike() {
            var data = {
                articleId: 9,
                userId: 1,
            };
            dislikeArticle(data);
        },
    },
};
</script>

<style>
.el-col {
    border-radius: 4px;
}
.grid-content {
    border-radius: 4px;
    min-height: 36px;
}
.card {
    width: auto;
    height: auto;
    padding: auto;
    margin-bottom: 30px;
    border-radius: 10px;
    line-height: 30px;
    background-color: rgba(0, 0, 0, 0);
}
</style>