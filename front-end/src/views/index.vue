<template>
    <div>
        <el-row :gutter="10">
            <el-col :xs="0" :sm="5" :md="5" :lg="5" :xl="5">
                <div class="muisc">
                    <aplayer :audio="musicList" :lrcType="3" />
                </div>
            </el-col>
            <el-col :xs="24" :sm="14" :md="14" :lg="14" :xl="14">
                <el-card
                    v-for="article in articleList"
                    :key="article.articleId"
                    shadow="hover"
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
            <el-col :xs="0" :sm="5" :md="5" :lg="5" :xl="5">
                <div class="grid-content bg-purple-light"></div>
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
            musicList: [],
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
.bg-purple-dark {
    background: #99a9bf;
}
.bg-purple {
    background: #d3dce6;
}
.bg-purple-light {
    background: #e5e9f2;
}
.grid-content {
    border-radius: 4px;
    min-height: 36px;
}
.el-card {
    width: auto;
    height: auto;
    background: #fff0f5;
    padding: auto;
    margin-bottom: 30px;
    border-radius: 10px;
    line-height: 30px;
}
</style>