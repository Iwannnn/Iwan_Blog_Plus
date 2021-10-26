<template>
    <div>
        <div v-for="comment in commentList" :key="comment.key">
            <el-card class="comment" v-if="comment.parentId == parentId">
                {{ comment.content }}
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
export default {
    name: "Comment",
    components: {
        Comment,
    },
    data() {
        return {
            list: [],
            child: false,
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
    created() {
        console.log(this.commentList);
    },
};
</script>
