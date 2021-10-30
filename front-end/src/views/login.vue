<template>
    <div class="login">
        <el-form
            ref="blogLoginForm"
            :model="blogLoginForm"
            :rules="loginRules"
            class="login-form"
        >
            <h3 class="title">登录</h3>
            <el-form-item prop="blogAccount">
                <el-input
                    v-model="blogLoginForm.blogAccount"
                    type="text"
                    auto-complete="off"
                    placeholder="账号"
                >
                    <svg-icon
                        slot="prefix"
                        icon-class="user"
                        class="el-input__icon input-icon"
                    />
                </el-input>
            </el-form-item>
            <el-form-item prop="blogPassword">
                <el-input
                    v-model="blogLoginForm.blogPassword"
                    type="password"
                    auto-complete="off"
                    placeholder="密码"
                    @keyup.enter.native="handleLogin"
                >
                    <svg-icon
                        slot="prefix"
                        icon-class="password"
                        class="el-input__icon input-icon"
                    />
                </el-input>
            </el-form-item>
            <el-checkbox
                v-model="blogLoginForm.blogRememberMe"
                style="margin: 0px 0px 25px 0px"
                >记住密码</el-checkbox
            >
            <el-form-item style="width: 100%">
                <el-button
                    :loading="loading"
                    size="medium"
                    type="primary"
                    style="width: 100%"
                    @click.native.prevent="handleLogin"
                >
                    <span v-if="!loading">登 录</span>
                    <span v-else>登 录 中...</span>
                </el-button>
                <div style="float: right" v-if="register">
                    <router-link class="link-type" :to="'/register'"
                        >立即注册</router-link
                    >
                </div>
            </el-form-item>
        </el-form>
        <!--  底部  -->
        <div class="el-login-footer">
            <span>copyright iwannnn.cn</span>
        </div>
    </div>
</template>

<script>
import Cookies from "js-cookie";
import { login } from "@/api/blog/user";
import { encrypt, decrypt } from "@/utils/jsencrypt";

export default {
    name: "Login",
    data() {
        return {
            codeUrl: "",
            cookiePassword: "",
            blogLoginForm: {
                blogAccount: "",
                blogPassword: "",
                blogRememberMe: false,
            },
            loginRules: {
                blogAccount: [
                    {
                        required: true,
                        trigger: "blur",
                        message: "请输入您的账号",
                    },
                ],
                blogPassword: [
                    {
                        required: true,
                        trigger: "blur",
                        message: "请输入您的密码",
                    },
                ],
            },
            loading: false,
            // 注册开关
            register: true,
            redirect: undefined,
        };
    },
    watch: {
        $route: {
            handler: function (route) {
                this.redirect = route.query && route.query.redirect;
            },
            immediate: true,
        },
    },
    created() {
        this.getCookie();
    },
    methods: {
        getCookie() {
            const blogAccount = Cookies.get("blogAccount");
            const blogPassword = Cookies.get("blogPassword");
            const blogRememberMe = Cookies.get("blogRememberMe");
            this.blogLoginForm = {
                blogAccount:
                    blogAccount === undefined
                        ? this.blogLoginForm.blogAccount
                        : blogAccount,
                blogPassword:
                    blogPassword === undefined
                        ? this.blogLoginForm.blogPassword
                        : decrypt(blogPassword),
                blogRememberMe:
                    blogRememberMe === undefined
                        ? false
                        : Boolean(blogRememberMe),
            };
        },
        handleLogin() {
            this.$refs.blogLoginForm.validate((valid) => {
                if (valid) {
                    this.loading = true;
                    if (this.blogLoginForm.blogRememberMe) {
                        Cookies.set(
                            "blogAccount",
                            this.blogLoginForm.blogAccount,
                            {
                                expires: 30,
                            }
                        );
                        Cookies.set(
                            "blogPassword",
                            encrypt(this.blogLoginForm.blogPassword),
                            { expires: 30 }
                        );
                        Cookies.set(
                            "blogRememberMe",
                            this.blogLoginForm.blogRememberMe,
                            {
                                expires: 30,
                            }
                        );
                    } else {
                        Cookies.remove("blogAccount");
                        Cookies.remove("blogPassword");
                        Cookies.remove("blogRememberMe");
                    }
                    login(
                        this.blogLoginForm.blogAccount,
                        this.blogLoginForm.blogPassword
                    ).then((res) => {
                        console.log(res);
                        if (res.msg == "操作成功") {
                            this.$message({
                                showClose: true,
                                message: "登录成功",
                                type: "success",
                            });
                        } else if (res.msg == "密码错误") {
                            this.$message({
                                showClose: true,
                                message: "密码错误",
                                type: "error",
                            });
                        } else if (res.msg == "账号不存在") {
                            this.$message({
                                showClose: true,
                                message: "账号不存在",
                                type: "error",
                            });
                        }
                    });
                }
            });
        },
    },
};
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background-image: url("../assets/background/jk.png");
    background-size: cover;
}
.title {
    margin: 0px auto 30px auto;
    text-align: center;
    color: #707070;
}

.login-form {
    border-radius: 6px;
    background: #ffffff;
    width: 400px;
    padding: 25px 25px 5px 25px;
    .el-input {
        height: 38px;
        input {
            height: 38px;
        }
    }
    .input-icon {
        height: 39px;
        width: 14px;
        margin-left: 2px;
    }
}
.login-tip {
    font-size: 13px;
    text-align: center;
    color: #bfbfbf;
}
.login-code {
    width: 33%;
    height: 38px;
    float: right;
    img {
        cursor: pointer;
        vertical-align: middle;
    }
}
.el-login-footer {
    height: 40px;
    line-height: 40px;
    position: fixed;
    bottom: 0;
    width: 100%;
    text-align: center;
    color: #fff;
    font-family: Arial;
    font-size: 12px;
    letter-spacing: 1px;
}
.login-code-img {
    height: 38px;
}
</style>