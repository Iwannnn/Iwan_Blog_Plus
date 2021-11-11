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
                    <div class="tittle">
                        <i
                            class="iconfont icon-custom-business"
                            style="color: #6a5acd"
                        />
                        {{ article.tittle }}
                    </div>
                    <div v-if="article.avatar">
                        <img class="image" v-bind:src="article.avatar" />
                    </div>

                    <div class="summary" v-if="article.summary">
                        {{ article.summary }}
                    </div>
                    <div class="cate" v-if="article.category">
                        <i
                            class="iconfont icon-category"
                            style="color: #008000"
                        />
                        {{ article.category }}
                    </div>
                    <div>
                        <span class="cardbuttom">
                            <i
                                class="iconfont icon-shijian"
                                style="color: #d8bfd8"
                            />
                            {{ article.createTime }}
                        </span>
                        <span class="cardbuttom">
                            <i
                                class="iconfont icon-icon1"
                                style="color: #008080"
                            />
                            {{ article.pageviews }}
                        </span>
                        <span class="cardbuttom">
                            <i
                                class="iconfont icon-icon"
                                style="color: #ff4500"
                            />
                            {{ article.likes }}
                        </span>
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
                                {{ this.articleList.total }} | 分类
                                {{ this.categoryList.total }} | 标签
                                {{ this.tagList.total }}
                            </div>
                        </div>
                        <div class="infotop">
                            <i
                                class="iconfont icon-category"
                                style="margin: 10px"
                            />分类
                        </div>
                        <br />
                        <div class="info">
                            <el-card
                                v-for="category in categoryList"
                                :key="category.categoryId"
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
                        <div class="infotop">
                            <i
                                class="iconfont icon-biaoqian"
                                style="margin: 10px"
                            />标签
                        </div>
                        <br />
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
import {
    listArticle,
    listArticleByTagId,
    likeArticle,
    dislikeArticle,
} from "@/api/blog/article";
import { listCategory, getCategory } from "@/api/blog/category";
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
            listAplayerMusic().then((res) => {
                this.musicList = res.data;
            });
            listCategory().then((res) => {
                this.categoryList = res.rows;
                this.categoryList.total = res.total;
                this.getArticleList();
            });
            listTag().then((res) => {
                console.log(res);
                this.tagList = res.rows;
                this.tagList.total = res.total;
            });
        },
        getArticleList() {
            listArticle(this.queryParams).then((res) => {
                this.articleList = res.rows;
                this.articleList.total = res.total;
                this.total = res.total;
                this.matchCategory();
            });
        },
        getArticleListByTagId() {
            listArticleByTagId(this.queryParams).then((res) => {
                this.articleList = res.rows;
                this.total = res.total;
            });
        },
        matchCategory() {
            var i, j;
            for (i = 0; i < this.articleList.length; i++) {
                if (this.articleList[i].categoryId) {
                    for (j = 0; j < this.categoryList.length; j++) {
                        if (
                            this.articleList[i].categoryId ===
                            this.categoryList[j].categoryId
                        ) {
                            this.articleList[i].category =
                                this.categoryList[j].name;
                        }
                    }
                }
            }
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
            this.queryParams = {
                pageNum: 1,
                pageSize: 10,
                categoryId: categoryId,
            };
            this.getArticleList();
        },
        handleTagClick(tagId) {
            this.queryParams = {
                pageNum: 1,
                pageSize: 10,
                tagId: tagId,
            };
            this.getArticleListByTagId();
        },
    },
};
</script>
<style scoped>
.rightbar {
    box-shadow: inset 0 0 0 2px #fff0f5;
    color: #696969;
    line-height: 40px;
    padding: 20px;
}
.info {
    padding: 10px;
    color: #696969;
    border-bottom: 2px solid #fff0f5;
}
.infotop {
    float: left;
    font-size: 20px;
    margin-left: 10px;
    margin-bottom: 0px;
    color: #696969;
}
.avatar {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    margin-top: 30px;
}
.text {
    color: #696969;
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
    color: #696969;
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
.tittle {
    color: #696969;
    padding: 10px;
    font-size: 40px;
}
.image {
    border-radius: 10px;
}
.cate {
    font-size: 25px;
    color: #696969;
}
.summary {
    font-size: 17px;
    color: #d3d3d3;
}
.cardbuttom {
    margin: 5px;
    color: #696969;
}
</style>