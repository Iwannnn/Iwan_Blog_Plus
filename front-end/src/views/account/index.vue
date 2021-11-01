<template>
    <div>account</div>
</template>
<script>
import Cookies from "js-cookie";
import { login } from "@/api/blog/user";
import { encrypt, decrypt } from "@/utils/jsencrypt";
export default {
    data() {
        return {
            userInfo: null,
        };
    },
    created() {
        this.getUserInfo();
    },
    methods: {
        getUserInfo() {
            this.userInfo = Cookies.get("userInfo");
            console.log(this.userInfo);
            if (this.userInfo == undefined) {
                this.$router.push("login");
                this.$message({
                    showClose: true,
                    message: "请先登录账号",
                    type: "warning",
                });
            }
        },
    },
};
</script>
