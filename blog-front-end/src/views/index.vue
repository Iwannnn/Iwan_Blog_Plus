<template>
    <div>
        <el-row :gutter="10">
            <el-col :xs="0" :sm="5" :md="5" :lg="5" :xl="5">
                <div class="muisc">
                    <aplayer :audio="musicList" :lrcType="3" />
                </div>
            </el-col>
            <el-col :xs="24" :sm="14" :md="14" :lg="14" :xl="14">
                hello world hello world hello world hello world hello world
            </el-col>
            <el-col :xs="0" :sm="5" :md="5" :lg="5" :xl="5">
                <div class="grid-content bg-purple-light"></div>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import { listMusic } from "@/api/blog/music";
import { listArticle } from "@/api/blog/article";
import { listCategory } from "@/api/blog/category";
import { listTag } from "@/api/blog/tag";
export default {
    data() {
        return {
            articleList: [],
            musicList: [],
            categoryList: [],
            tagList: [],
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
            listMusic().then((res) => {
                this.musicList = res.data;
                console.log(this.musicList);
            });
            listCategory().then((res) => {
                this.categoryList = res.rows;
            });
            listTag().then((res) => {
                this.tagList = res.rows;
            });
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
</style>