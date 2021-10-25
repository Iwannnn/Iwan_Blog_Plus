<template>
    <div>
        <el-row :gutter="20">
            <el-col :xs="24" :sm="16" :md="16" :lg="16" :xl="16">
                <router-link
                    v-for="article in articleList"
                    :key="article.articleId"
                    class="card"
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
                <pagination
                    v-show="total > 0"
                    :total="total"
                    style="height: 150px"
                    :page.sync="queryParams.pageNum"
                    :limit.sync="queryParams.pageSize"
                    @pagination="getArticleList()"
                />
            </el-col>
            <el-col :xs="0" :sm="8" :md="8" :lg="8" :xl="8">
                <div>
                    <div class="rightbar">
                        <div class="info">
                            <img class="avatar" :src="avatarUrl" />
                            <div class="text">
                                熊猫圈里的老鼠屎
                                <br />
                                文章
                                {{ this.articleList.length }} | 分类
                                {{ this.categoryList.length }} | 标签
                                {{ this.tagList.length }}
                            </div>
                        </div>
                        <div class="info">
                            <el-card
                                v-for="category in categoryList"
                                :key="category.categoryList"
                                class="category"
                                shadow="hover"
                                @click.native="
                                    handleCategoryClick(category.categoryId)
                                "
                            >
                                <span class="category-left">
                                    {{ category.name }}
                                </span>
                                <span class="category-num" :style="color()">
                                    {{ category.num }}
                                </span>
                            </el-card>
                        </div>
                        <div class="info">
                            <el-card
                                v-for="tag in tagList"
                                :key="tag.tagId"
                                class="tag"
                                shadow="hover"
                                :style="color()"
                                @click.native="handleTagClick(tag.tagId)"
                            >
                                {{ tag.name }}
                            </el-card>
                        </div>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import { listAplayerMusic } from "@/api/blog/music";
import { listArticle, likeArticle, dislikeArticle } from "@/api/blog/article";
import { listCategory } from "@/api/blog/category";
import { listTag } from "@/api/blog/tag";
import "@/assets/styles/cardround.css";
export default {
    data() {
        return {
            articleList: [],
            categoryList: [],
            tagList: [],
            avatarUrl: require("@/assets/avatar/white.jpg"),
            queryParams: {
                pageNum: 1,
                pageSize: 10,
            },
            total: 0,
        };
    },
    created() {
        this.getList();
    },
    methods: {
        getList() {
            listArticle(this.queryParams).then((res) => {
                this.articleList = res.rows;
                this.total = res.total;
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

        getArticleList() {
            listArticle(this.queryParams).then((res) => {
                this.articleList = res.rows;
                this.total = res.total;
            });
        },
        testLike() {
            var data = {
                articleId: 9,
                userId: 1,
            };
            dislikeArticle(data);
        },
        color() {
            var r = Math.floor(Math.random() * 255);
            var g = Math.floor(Math.random() * 255);
            var b = Math.floor(Math.random() * 255);
            var color = "background:rgba(" + r + "," + g + "," + b + ",0.8)";
            return color;
        },
        handleCategoryClick(categoryId) {
            console.log(categoryId);
        },
        handleTagClick(tagId) {
            console.log(tagId);
        },
    },
};
</script>
<style scoped>
.rightbar {
    box-shadow: inset 0 0 0 2px #f5f5f5;
    line-height: 40px;
    padding: 20px;
}
.info {
    padding: 10px;
    border-bottom: 2px solid #f5f5f5;
}
.avatar {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    margin-top: 30px;
}
.text {
    text-align: center;
    font-size: 20px;
    padding-bottom: 10px;
}
.category {
    text-align: left;
    font-size: 1.2rem;
    background: none;
    line-height: 1.2rem;
    height: auto;
    color: #000000;
    position: relative;
    margin: 10px auto;
    background-color: rgba(0, 0, 0, 0);
    border-radius: 5px;
}
.category:hover {
    color: #ffdafc;
}
.category-name {
    display: flex;
    justify-content: space-between;
    color: var(--text-color);
    float: left;
}
.category-num {
    width: 1.6rem;
    height: 1.6rem;
    text-align: center;
    line-height: 1.6rem;
    border-radius: 0.25rem;
    background: #eee;
    font-size: 20px;
    color: #fff;
    float: right;
}
.tag {
    display: inline-flex;
    font-size: 10px;
    background: none;
    line-height: 1px;
    height: 30px;
    color: #ffffff;
    position: relative;
    margin: 2px 2px;
    background-color: rgba(0, 0, 0, 0);
    border-radius: 5px;
}
</style>