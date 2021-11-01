<template>
    <div></div>
</template>
<script>
import Cookies from "js-cookie";
import { login } from "@/api/blog/user";
import { encrypt, decrypt } from "@/utils/jsencrypt";
export default {
    data() {
        return {
            userId: null,
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
            }
        },
    },
};
</script>
