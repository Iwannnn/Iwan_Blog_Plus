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
                </el-col>
            </el-row>
            <div
                :class="navBarFixed == true ? 'navbar-wrap' : ''"
                style="width: 100%"
            >
                <el-menu :router="true" :default-active="'/index'">
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
                            <el-menu-item class="menu-item" index="/account">
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
                            <el-submenu>
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
        </el-header>
        <el-main>
            <div>
                <keep-alive><router-view /></keep-alive>
            </div>
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
    mounted() {
        // 事件监听滚动条
        window.addEventListener("scroll", this.watchScroll);
    },
    methods: {
        watchScroll() {
            var scrollTop =
                window.pageYOffset ||
                document.documentElement.scrollTop ||
                document.body.scrollTop;
            let height = this.$refs.background.offsetHeight;
            //  当滚动超过 50 时，实现吸顶效果
            if (scrollTop > height) {
                this.navBarFixed = true;
            } else {
                this.navBarFixed = false;
            }
        },
    },
};
</script>

<style>
.el-header {
    padding: 0px 0px;
    color: #333;
    text-align: center;
    line-height: 10px;
    width: 100%;
}
.el-footer {
    background-color: #b3c0d1;
    color: #333;
    text-align: center;
    line-height: 60px;
}
.el-main {
    background-color: #e9eef3;
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
.el-menu {
    padding-left: 30px;
    padding-right: 30px;
}
.img-back {
    background: black;
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
