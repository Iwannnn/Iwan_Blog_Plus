<template>
    <div class="register">
        <el-form
            ref="registerForm"
            :model="registerForm"
            :rules="registerRules"
            class="register-form"
        >
            <h3 class="title">注册</h3>
            <el-form-item prop="account">
                <el-input
                    v-model="registerForm.account"
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
            <el-form-item prop="password">
                <el-input
                    v-model="registerForm.password"
                    type="password"
                    auto-complete="off"
                    placeholder="密码"
                >
                    <svg-icon
                        slot="prefix"
                        icon-class="password"
                        class="el-input__icon input-icon"
                    />
                </el-input>
            </el-form-item>
            <el-form-item prop="repassword">
                <el-input
                    v-model="registerForm.repassword"
                    type="password"
                    auto-complete="off"
                    placeholder="密码"
                >
                    <svg-icon
                        slot="prefix"
                        icon-class="password"
                        class="el-input__icon input-icon"
                    />
                </el-input>
            </el-form-item>
            <el-form-item prop="phone">
                <el-input
                    v-model="registerForm.phone"
                    type="text"
                    auto-complete="off"
                    placeholder="电话号码"
                >
                    <svg-icon
                        slot="prefix"
                        icon-class="phone"
                        class="el-input__icon input-icon"
                    />
                </el-input>
            </el-form-item>
            <el-form-item prop="email">
                <el-input
                    v-model="registerForm.email"
                    type="text"
                    auto-complete="off"
                    placeholder="邮箱"
                >
                    <svg-icon
                        slot="prefix"
                        icon-class="email"
                        class="el-input__icon input-icon"
                    />
                </el-input>
            </el-form-item>
            <el-form-item style="width: 100%">
                <el-button
                    :loading="loading"
                    size="medium"
                    type="primary"
                    style="width: 100%"
                    @click.native.prevent="handleRegister"
                >
                    <span v-if="!loading">注册</span>
                    <span v-else>注 册 中...</span>
                </el-button>
                <div style="float: right" v-if="login">
                    <router-link class="link-type" :to="'/login'">
                        立即登录
                    </router-link>
                </div>
            </el-form-item>
        </el-form>
        <!--  底部  -->
        <div class="el-register-footer">
            <span>copyright iwannnn.cn</span>
        </div>
    </div>
</template>

<script>
import { check, addUser } from "@/api/blog/user";
import { Message } from "element-ui";
export default {
    name: "Register",
    data() {
        var validatePassword = (rule, value, callback) => {
            if (value === "") {
                callback(new Error("请输入密码"));
            } else {
                if (this.registerForm.repassword !== "") {
                    this.$refs.registerForm.validateField("repassword");
                }
                callback();
            }
        };
        var validateRepassword = (rule, value, callback) => {
            if (value === "") {
                callback(new Error("请再次输入密码"));
            } else if (value !== this.registerForm.password) {
                callback(new Error("两次输入密码不一致!"));
            } else {
                callback();
            }
        };
        var validatePhone = (rule, value, callback) => {
            if (
                /^(13[0-9]|14[5-9]|15[012356789]|166|17[0-8]|18[0-9]|19[8-9])[0-9]{8}$/.test(
                    value
                ) == false &&
                value == ""
            ) {
                callback(new Error("请输入正确的手机号"));
            } else {
                callback();
            }
        };
        var checkAccount = (rule, value, callback) => {
            var query = {
                account: value,
            };
            check(query).then((res) => {
                if (res.msg === "exist") {
                    callback(new Error("该账号名已被使用"));
                } else {
                    callback();
                }
            });
        };
        var checkEmail = (rule, value, callback) => {
            var query = {
                email: value,
            };
            check(query).then((res) => {
                if (res.msg === "exist") {
                    callback(new Error("该邮箱已被使用"));
                } else {
                    callback();
                }
            });
        };
        var checkPhone = (rule, value, callback) => {
            var query = {
                phone: value,
            };
            check(query).then((res) => {
                if (res.msg === "exist") {
                    callback(new Error("该电话已被使用"));
                } else {
                    callback();
                }
            });
        };
        return {
            registerForm: {
                account: null,
                password: null,
                repassword: null,
                phone: null,
                email: null,
            },
            registerRules: {
                account: [
                    {
                        required: true,
                        trigger: "blur",
                        message: "请输入您的账号",
                    },
                    {
                        validator: checkAccount,
                        trigger: "blur",
                    },
                ],
                password: [
                    {
                        required: true,
                        validator: validatePassword,
                        trigger: "blur",
                    },
                ],

                repassword: [
                    {
                        required: true,
                        validator: validateRepassword,
                        trigger: "blur",
                    },
                ],
                phone: [
                    {
                        validator: validatePhone,
                        trigger: ["change"],
                    },
                    {
                        validator: checkPhone,
                        trigger: "blur",
                    },
                ],
                email: [
                    {
                        type: "email",
                        message: "请输入正确的邮箱地址",
                        trigger: "blur",
                    },
                    {
                        validator: checkEmail,
                        trigger: "blur",
                    },
                ],
            },
            loading: false,
            // 注册开关
            login: true,
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
    created() {},
    methods: {
        handleRegister() {
            this.$refs.registerForm.validate((valid) => {
                if (valid) {
                    addUser(this.registerForm).then((res) => {
                        if (res.msg == "操作成功") {
                            this.$message({
                                showClose: true,
                                message: "注册成功",
                                type: "success",
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
.register {
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

.register-form {
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
.register-tip {
    font-size: 13px;
    text-align: center;
    color: #bfbfbf;
}
.register-code {
    width: 33%;
    height: 38px;
    float: right;
    img {
        cursor: pointer;
        vertical-align: middle;
    }
}
.el-register-footer {
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
.register-code-img {
    height: 38px;
}
</style>
