<template>
    <div>
        <div v-for="(comment, index) in commentList" :key="index">
            <el-card class="comment" v-if="comment.parentId == parentId">
                <div>
                    <img class="avatar" :src="comment.avatar" />
                    <span class="username">
                        {{ comment.username }}
                    </span>
                    <span class="username">
                        {{ comment.createTime }}
                    </span>
                    <span class="reply">
                        <el-button
                            v-if="!comment.reply"
                            type="text"
                            @click="reply(index)"
                        >
                            回复
                        </el-button>
                        <el-button
                            v-if="comment.reply"
                            type="text"
                            @click="reply(index)"
                        >
                            取消
                        </el-button>
                        <el-button
                            v-if="comment.reply"
                            type="text"
                            @click="confirmReply(index)"
                        >
                            确认
                        </el-button>
                    </span>
                </div>
                <div class="content" id="comment" v-html="comment.html" />
                <div v-if="comment.reply === true">
                    <mavon-editor
                        v-model="comment.replyContent"
                        ref="md"
                        placeholder="请输入回复内容..."
                        :boxShadow="false"
                        style="
                            z-index: 1;
                            border: 1px solid #d9d9d9;
                            height: 30vh;
                        "
                        :shortCut="false"
                        :toolbars="toolbars"
                        :defaultOpen="'edit'"
                    />
                </div>
                <Comment
                    :commentList.sync="commentList"
                    :parentId="comment.commentId"
                />
            </el-card>
        </div>
    </div>
</template>
<script>
import Comment from "@/components/Comments";
import Cookies from "js-cookie";
import { getUser } from "@/api/blog/user";
import { addComment } from "@/api/blog/comment";
import "@/assets/styles/github.css";
import Clipboard from "clipboard";
export default {
    name: "Comment",
    components: {
        Comment,
    },
    inject: ["reload"],
    data() {
        return {
            list: [],
            child: false,
            toolbars: {
                bold: true, // 粗体
                italic: true, // 斜体
                header: true, // 标题
                underline: true, // 下划线
                strikethrough: true, // 中划线
                mark: true, // 标记
                superscript: true, // 上角标
                subscript: true, // 下角标
                quote: true, // 引用
                ol: true, // 有序列表
                ul: true, // 无序列表
                link: true, // 链接
                imagelink: false, // 图片链接
                code: true, // code
                table: true, // 表格
                fullscreen: true, // 全屏编辑
                readmodel: true, // 沉浸式阅读
                htmlcode: true, // 展示html源码
                help: true, // 帮助
                /* 1.3.5 */
                undo: true, // 上一步
                redo: true, // 下一步
                trash: true, // 清空
                save: false, // 保存（触发events中的save事件）
                /* 1.4.2 */
                navigation: true, // 导航目录
                /* 2.1.8 */
                alignleft: true, // 左对齐
                aligncenter: true, // 居中
                alignright: true, // 右对齐
                /* 2.2.1 */
                subfield: true, // 单双栏模式
                preview: true, // 预览
            },
        };
    },
    props: {
        commentList: {
            type: Array,
            default: () => [],
        },
        nextList: {
            type: Array,
            default: () => [],
        },
        parentId: {
            type: Number,
            default: 0,
        },
    },
    methods: {
        reply(commentIndex) {
            var temp = this.commentList[commentIndex];
            temp.reply = !temp.reply;
            this.$set(this.commentList, commentIndex, temp);
        },
        confirmReply(commentIndex) {
            if (this.commentList[commentIndex].replyContent) {
                var comment = {
                    content: this.commentList[commentIndex].replyContent,
                    parentId: this.commentList[commentIndex].commentId,
                    articleId: this.commentList[commentIndex].articleId,
                    becommentUserId:
                        this.commentList[commentIndex].commentUserId,
                };
                var userId = Cookies.get("userId") ? Cookies.get("userId") : 0;
                if (userId !== 0) {
                    getUser(userId).then((res) => {
                        comment.commentUserId = userId;
                        comment.username = res.data.nickname
                            ? res.data.nickname
                            : res.data.account;
                        comment.avatar = res.data.avatar;
                        addComment(comment).then((res) => {
                            if (res.data.msg === "操作成功") {
                                this.$message({
                                    showClose: true,
                                    message: "回复内容不能为空",
                                    type: "warning",
                                });
                                this.reload();
                            }
                        });
                    });
                }
            } else {
                this.$message({
                    showClose: true,
                    message: "回复内容不能为空",
                    type: "warning",
                });
            }
        },
    },
};
</script>
<style>
.comment {
    line-height: 30px;
    text-align: left;
    margin-top: 10px;
}
.username {
    font-weight: 500;
    margin-left: 10px;
}
.avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
}
.reply {
    float: right;
}
.content {
    margin-left: 50px;
    margin-bottom: 5px;
    padding-left: 10px;
    border-radius: 5px;
    background: #f9f9f9;
}
</style>
