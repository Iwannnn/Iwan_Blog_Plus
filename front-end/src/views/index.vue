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
            </el-col>
            <el-col :xs="0" :sm="8" :md="8" :lg="8" :xl="8">
                <a-affix :offset-top="70">
                    <div class="rightbar">
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
                    <div class="rightbar">
                        <div class="category-text">分类</div>
                        <div
                            v-for="category in categoryList"
                            :key="category.category"
                        >
                            <el-button class="category">
                                <span class="category-name">
                                    {{ category.name }}
                                </span>
                            </el-button>
                        </div>
                    </div>
                    <div class="rightbar">14314514</div>
                </a-affix>
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
                this.countCategory();
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
        countCategory() {
            var i = 0,
                j = 0;
            for (i = 0; i < this.categoryList.length; i++) {
                this.categoryList[i].num = 0;
            }
            for (i = 0; i < this.articleList.length; i++) {
                for (j = 0; j < this.categoryList.length; j++) {
                    if (
                        this.categoryList[j].categoryId ===
                        this.articleList[i].categoryId
                    ) {
                        this.categoryList[j].num = this.categoryList[j].num
                            ? this.categoryList[j].num + 1
                            : 0;
                        break;
                    }
                }
            }
        },
    },
};
</script>
<style scoped>
.rightbar {
    box-shadow: inset 0 0 0 2px #f5f5f5;
    line-height: 40px;
    margin: 0px 0px 20px 0px;
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
    padding-bottom: 30px;
}
.category {
    border-radius: 0px;
    width: 80%;
    margin-top: 10px;
    margin-bottom: 10px;
    font-size: 20px;
}
.category-name {
    float: left;
}
.category-num {
    float: right;
}
.tag {
}
</style>