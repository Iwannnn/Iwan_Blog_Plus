<template>
    <el-container>
        <el-header height="'auto'">
            <el-row>
                <el-col>
                    <div v-if="this.$route.path == '/index'" class="img-back">
                        <img
                            ref="background"
                            style="height: auto; width: 100%"
                            :src="require('@/assets/background/jk.png')"
                        />
                    </div>
                    <div v-else>
                        <img
                            ref="background"
                            style="height: auto; width: 100%"
                            :src="require('@/assets/background/double_jk.png')"
                        />
                    </div>
                </el-col>
            </el-row>
            <a-affix :offset_top="0">
                <div style="width: 100%">
                    <el-menu
                        class="blog-el-menu"
                        :router="true"
                        :default-active="'/index'"
                        style="background-color: #e6e6fa"
                    >
                        <el-row>
                            <el-col :xs="0">
                                <el-menu-item class="menu-item" index="/index">
                                    首页
                                </el-menu-item>
                                <el-menu-item class="menu-item" index="/about">
                                    关于
                                </el-menu-item>
                                <el-menu-item class="menu-item" index="/back">
                                    后台
                                </el-menu-item>
                                <el-menu-item
                                    class="menu-item"
                                    index="/account"
                                >
                                    我的
                                </el-menu-item>
                                <el-menu-item class="icon">
                                    <i class="iconfont icon-github"></i>
                                </el-menu-item>
                                <el-menu-item class="icon">
                                    <i class="iconfont icon-coffee"></i>
                                </el-menu-item>
                                <el-menu-item class="icon">
                                    <i class="iconfont icon-QQ"></i>
                                </el-menu-item>
                                <el-menu-item class="icon">
                                    <i class="iconfont icon-weixin"></i>
                                </el-menu-item>
                            </el-col>
                            <el-col :xs="24" :sm="0" :md="0" :lg="0" :xl="0">
                                <el-submenu index="/">
                                    <template slot="title">导航菜单</template>
                                    <el-menu-item index="/index">
                                        首页
                                    </el-menu-item>
                                    <el-menu-item index="/about">
                                        关于
                                    </el-menu-item>
                                    <el-menu-item index="/back">
                                        后台
                                    </el-menu-item>
                                    <el-menu-item index="/account">
                                        我的
                                    </el-menu-item>
                                </el-submenu>
                            </el-col>
                        </el-row>
                    </el-menu>
                </div>
            </a-affix>
        </el-header>
        <el-main>
            <keep-alive><router-view /></keep-alive>
        </el-main>
        <el-footer>
            <div>
                <small>
                    <a href="https://beian.miit.gov.cn">
                        浙ICP备2021004697号-1
                    </a>
                </small>
            </div>
        </el-footer>
    </el-container>
</template>

<script>
export default {
    name: "Blog",
    data() {
        return {
            navBarFixed: false,
            navBarIndex: [],
        };
    },
    mounted() {},
    methods: {},
};
</script>

<style>
.el-header {
    background-color: #e6e6fa;
    padding: 0px 0px;
    color: #333;
    text-align: center;
    line-height: 10px;
    width: 100%;
}
.el-footer {
    background-color: #e6e6fa;
    color: #333;
    text-align: center;
    line-height: 60px;
}
.el-main {
    color: #333;
    text-align: center;
    line-height: 160px;
}

.navbar-wrap {
    position: fixed;
    top: 0;
    z-index: 999;
}
.el-image {
    z-index: 100;
}
.blog-el-menu {
    padding-left: 30px;
    padding-right: 30px;
}
.img-back {
    display: flex;
    justify-content: center;
    align-items: center;
}
.menu-item {
    float: left;
}
.icon {
    float: right;
}
</style>
