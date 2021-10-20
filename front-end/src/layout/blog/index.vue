<template>
    <el-container>
        <el-header :height="'auto'">
            <div
                v-if="this.$route.path == '/index'"
                style="background: black; height: auto"
            >
                <img :src="require('@/assets/background/asuka.png')" />
            </div>
            <div
                :class="navBarFixed == true ? 'navBarWrap' : ''"
                style="width: 100%"
            >
                <el-menu mode="horizontal">
                    <el-menu-item index="1">处理中心</el-menu-item>
                    <el-submenu index="2">
                        <template slot="title">我的工作台</template>
                        <el-menu-item index="2-1">选项1</el-menu-item>
                        <el-menu-item index="2-2">选项2</el-menu-item>
                        <el-menu-item index="2-3">选项3</el-menu-item>
                        <el-submenu index="2-4">
                            <template slot="title">选项4</template>
                            <el-menu-item index="2-4-1">选项1</el-menu-item>
                            <el-menu-item index="2-4-2">选项2</el-menu-item>
                            <el-menu-item index="2-4-3">选项3</el-menu-item>
                        </el-submenu>
                    </el-submenu>
                    <el-menu-item index="3" disabled>消息中心</el-menu-item>
                    <el-menu-item index="4">
                        <a href="https://www.ele.me" target="_blank"
                            >订单管理
                        </a>
                    </el-menu-item>
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
    name: "Layout",
    data() {
        return {
            navBarFixed: false,
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
            //  当滚动超过 50 时，实现吸顶效果
            if (scrollTop > 719) {
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

.navBarWrap {
    position: fixed;
    top: 0;
    z-index: 999;
}
.el-image {
    z-index: 100;
}
</style>
