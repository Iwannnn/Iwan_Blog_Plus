<template>
    <div class="app-container">
        <el-form
            :model="queryParams"
            ref="queryForm"
            :inline="true"
            v-show="showSearch"
            label-width="68px"
        >
            <el-form-item label="用户id" prop="userId">
                <el-input
                    v-model="queryParams.userId"
                    placeholder="请输入用户id"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="文章id" prop="articleId">
                <el-input
                    v-model="queryParams.articleId"
                    placeholder="请输入文章id"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="是否取消了点赞" prop="isDelete">
                <el-input
                    v-model="queryParams.isDelete"
                    placeholder="请输入是否取消了点赞"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="评论id" prop="commentId">
                <el-input
                    v-model="queryParams.commentId"
                    placeholder="请输入评论id"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="备注" prop="temark">
                <el-input
                    v-model="queryParams.temark"
                    placeholder="请输入备注"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item>
                <el-button
                    type="primary"
                    icon="el-icon-search"
                    size="mini"
                    @click="handleQuery"
                    >搜索</el-button
                >
                <el-button
                    icon="el-icon-refresh"
                    size="mini"
                    @click="resetQuery"
                    >重置</el-button
                >
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button
                    type="primary"
                    plain
                    icon="el-icon-plus"
                    size="mini"
                    @click="handleAdd"
                    v-hasPermi="['blog:like:add']"
                    >新增</el-button
                >
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    plain
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['blog:like:edit']"
                    >修改</el-button
                >
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    plain
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['blog:like:remove']"
                    >删除</el-button
                >
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    plain
                    icon="el-icon-download"
                    size="mini"
                    :loading="exportLoading"
                    @click="handleExport"
                    v-hasPermi="['blog:like:export']"
                    >导出</el-button
                >
            </el-col>
            <right-toolbar
                :showSearch.sync="showSearch"
                @queryTable="getList"
            ></right-toolbar>
        </el-row>

        <el-table
            v-loading="loading"
            :data="likeList"
            @selection-change="handleSelectionChange"
        >
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="用户点赞关联" align="center" prop="id" />
            <el-table-column label="用户id" align="center" prop="userId" />
            <el-table-column label="文章id" align="center" prop="articleId" />
            <el-table-column
                label="是否取消了点赞"
                align="center"
                prop="isDelete"
            />
            <el-table-column label="评论id" align="center" prop="commentId" />
            <el-table-column label="备注" align="center" prop="temark" />
            <el-table-column
                label="操作"
                align="center"
                class-name="small-padding fixed-width"
            >
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['blog:like:edit']"
                        >修改</el-button
                    >
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['blog:like:remove']"
                        >删除</el-button
                    >
                </template>
            </el-table-column>
        </el-table>

        <pagination
            v-show="total > 0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
        />

        <!-- 添加或修改用户点赞对话框 -->
        <el-dialog
            :title="title"
            :visible.sync="open"
            width="500px"
            append-to-body
        >
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="用户id" prop="userId">
                    <el-input
                        v-model="form.userId"
                        placeholder="请输入用户id"
                    />
                </el-form-item>
                <el-form-item label="文章id" prop="articleId">
                    <el-input
                        v-model="form.articleId"
                        placeholder="请输入文章id"
                    />
                    <el-select
                        v-model="form.articleId"
                        placeholder="请选择文章"
                    >
                        <el-option
                            v-for="item in articleList"
                            :key="item.articleId"
                            :label="item.tittle"
                            :value="item.articleId"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="评论id" prop="commentId">
                    <el-select
                        v-model="form.commentId"
                        placeholder="请选择文章"
                    >
                        <el-option
                            v-for="item in commentList"
                            :key="item.commentId"
                            :label="item.content"
                            :value="item.commentId"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="是否取消了点赞" prop="isDelete">
                    <el-input
                        v-model="form.isDelete"
                        placeholder="请输入是否取消了点赞"
                    />
                </el-form-item>
                <el-form-item label="备注" prop="temark">
                    <el-input v-model="form.temark" placeholder="请输入备注" />
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
import {
    listLike,
    getLike,
    delLike,
    addLike,
    updateLike,
    exportLike,
} from "@/api/blog/like";

import { getUserId } from "@/api/blog/user.js";
import { getCommentId } from "@/api/blog/comment.js";
import { getArticleId } from "@/api/blog/article.js";
export default {
    name: "Like",
    data() {
        return {
            // 遮罩层
            loading: true,
            // 导出遮罩层
            exportLoading: false,
            // 选中数组
            ids: [],
            // 非单个禁用
            single: true,
            // 非多个禁用
            multiple: true,
            // 显示搜索条件
            showSearch: true,
            // 总条数
            total: 0,
            // 用户点赞表格数据
            likeList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                userId: null,
                articleId: null,
                isDelete: null,
                commentId: null,
                temark: null,
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {},
            // 用户列表
            userList: [],
            // 评论列表
            commentList: [],
            // 文章列表
            articleList: [],
        };
    },
    created() {
        this.getList();
        this.getArticleList();
        this.getUserList();
        this.getCommentList();
    },
    methods: {
        /** 查询用户点赞列表 */
        getList() {
            this.loading = true;
            listLike(this.queryParams).then((response) => {
                this.likeList = response.rows;
                this.total = response.total;
                this.loading = false;
            });
        },
        // 取消按钮
        cancel() {
            this.open = false;
            this.reset();
        },
        // 表单重置
        reset() {
            this.form = {
                id: null,
                userId: null,
                articleId: null,
                isDelete: null,
                commentId: null,
                createTime: null,
                updateTime: null,
                temark: null,
            };
            this.resetForm("form");
        },
        /** 搜索按钮操作 */
        handleQuery() {
            this.queryParams.pageNum = 1;
            this.getList();
        },
        /** 重置按钮操作 */
        resetQuery() {
            this.resetForm("queryForm");
            this.handleQuery();
        },
        // 多选框选中数据
        handleSelectionChange(selection) {
            this.ids = selection.map((item) => item.id);
            this.single = selection.length !== 1;
            this.multiple = !selection.length;
        },
        /** 新增按钮操作 */
        handleAdd() {
            this.reset();
            this.open = true;
            this.title = "添加用户点赞";
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const id = row.id || this.ids;
            getLike(id).then((response) => {
                this.form = response.data;
                this.open = true;
                this.title = "修改用户点赞";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate((valid) => {
                if (valid) {
                    if (this.form.id != null) {
                        updateLike(this.form).then((response) => {
                            this.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        addLike(this.form).then((response) => {
                            this.msgSuccess("新增成功");
                            this.open = false;
                            this.getList();
                        });
                    }
                }
            });
        },
        /** 删除按钮操作 */
        handleDelete(row) {
            const ids = row.id || this.ids;
            this.$confirm(
                '是否确认删除用户点赞编号为"' + ids + '"的数据项?',
                "警告",
                {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                }
            )
                .then(function () {
                    return delLike(ids);
                })
                .then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                })
                .catch(() => {});
        },
        /** 导出按钮操作 */
        handleExport() {
            const queryParams = this.queryParams;
            this.$confirm("是否确认导出所有用户点赞数据项?", "警告", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
            })
                .then(() => {
                    this.exportLoading = true;
                    return exportLike(queryParams);
                })
                .then((response) => {
                    this.download(response.msg);
                    this.exportLoading = false;
                })
                .catch(() => {});
        },
        getUserList() {
            getUserId().then((res) => {
                this.userList = res.data;
            });
        },
        getCommentList() {
            getCommentId().then((res) => {
                this.commentList = res.data;
            });
        },
        getArticleList() {
            getArticleId().then((res) => {
                this.articleList = res.data;
            });
        },
    },
};
</script>
