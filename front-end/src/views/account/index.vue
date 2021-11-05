<template>
    <div>
        <el-row :gutter="10">
            <el-col :xs="24" :sm="16" :md="16" :lg="16" :xl="16">
                <el-card class="box-card">
                    <div slot="header" class="head">
                        <span>个人信息</span>
                        <el-button
                            style="float: right; padding: 3px 0"
                            type="text"
                            @click="modify"
                        >
                            修改信息
                        </el-button>
                    </div>
                    <img class="avatar" :src="userInfo.avatar" />
                    <div class="info">账号 : {{ userInfo.account }}</div>
                    <div class="info">昵称 : {{ userInfo.nickname }}</div>
                    <div class="info">电话 : {{ userInfo.phone }}</div>
                    <div class="info">邮箱 : {{ userInfo.email }}</div>
                    <div class="info">生日 : {{ userInfo.birthday }}</div>
                    <div class="info">介绍 : {{ userInfo.introduction }}</div>
                </el-card>
            </el-col>
        </el-row>
        <el-dialog :title="修改信息" :visible.sync="open" append-to-body>
            <el-form ref="form" :model="userInfo" label-width="80px">
                <el-form-item label="昵称" prop="nickname">
                    <el-input
                        v-model="userInfo.nickname"
                        placeholder="请输入昵称"
                    />
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input
                        v-model="userInfo.phone"
                        placeholder="请输入电话"
                    />
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input
                        v-model="userInfo.email"
                        placeholder="请输入邮箱"
                    />
                </el-form-item>
                <el-form-item label="头像" prop="avatar">
                    <imageUpload
                        @input="getImageUrl"
                        :dialogImageUrl="'userInfo.avatar'"
                    />
                </el-form-item>
                <el-form-item label="生日" prop="birthday">
                    <el-input
                        v-model="userInfo.birthday"
                        placeholder="请输入生日"
                    />
                </el-form-item>
                <el-form-item label="简介" prop="introduction">
                    <el-input
                        v-model="userInfo.introduction"
                        placeholder="请输入简介"
                    />
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import Cookies from "js-cookie";
import { login, getUser, updateUser } from "@/api/blog/user";
export default {
    data() {
        return {
            open: false,
            userId: null,
            userInfo: null,
        };
    },
    created() {
        this.getUserInfo();
    },
    methods: {
        getUserInfo() {
            this.userId = Cookies.get("userId");
            if (this.userId == undefined) {
                this.$router.push({
                    path: "/login",
                    query: {
                        next: "/account",
                    },
                });
                this.$message({
                    showClose: true,
                    message: "请先登录账号",
                    type: "warning",
                });
            } else {
                getUser(this.userId).then((res) => {
                    console.log(res);
                    this.userInfo = res.data;
                });
            }
        },
        submitForm() {
            updateUser(this.userInfo).then((res) => {
                this.msgSuccess("修改成功");
                this.open = false;
                getUser(this.userId).then((res) => {
                    console.log(res);
                    this.userInfo = res.data;
                });
            });
        },
        cancel() {
            this.open = false;
        },
        modify() {
            this.open = true;
        },
        getImageUrl(imageUrl) {
            this.userInfo.avatar = imageUrl;
        },
    },
};
</script>
<style scoped>
.head {
    text-align: left;
}
.box-card {
    line-height: 10px;
}
.avatar {
    width: 100px;
    height: 100px;
    margin-top: 10px;
}
.info {
    margin: 20px;
    font-size: 20px;
}
</style>
