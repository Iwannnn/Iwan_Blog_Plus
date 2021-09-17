<template>
    <div class="app-container">
        <el-form
            :model="queryParams"
            ref="queryForm"
            :inline="true"
            v-show="showSearch"
            label-width="100px"
        >
            <el-form-item label="博客id" prop="blogId">
                <el-input
                    v-model="queryParams.blogId"
                    placeholder="请输入博客id"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="父级评论" prop="parentId">
                <el-input
                    v-model="queryParams.parentId"
                    placeholder="请输入父级评论"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="被评论用户id" prop="becommentUserId">
                <el-input
                    v-model="queryParams.becommentUserId"
                    placeholder="请输入被评论用户id"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="被评论用户名" prop="becommentUserName">
                <el-input
                    v-model="queryParams.becommentUserName"
                    placeholder="请输入被评论用户名"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="评论用户id" prop="commentUserId">
                <el-input
                    v-model="queryParams.commentUserId"
                    placeholder="请输入评论用户id"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="评论用户名" prop="commentUserName">
                <el-input
                    v-model="queryParams.commentUserName"
                    placeholder="请输入评论用户名"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="评论点赞数" prop="likes">
                <el-input
                    v-model="queryParams.likes"
                    placeholder="请输入评论点赞数"
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
                    v-hasPermi="['blog:comment:add']"
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
                    v-hasPermi="['blog:comment:edit']"
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
                    v-hasPermi="['blog:comment:remove']"
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
                    v-hasPermi="['blog:comment:export']"
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
            :data="commentList"
            @selection-change="handleSelectionChange"
        >
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="评论id" align="center" prop="commentId" />
            <el-table-column label="博客id" align="center" prop="blogId" />
            <el-table-column label="父级评论" align="center" prop="parentId" />
            <el-table-column
                label="被评论用户id"
                align="center"
                prop="becommentUserId"
            />
            <el-table-column
                label="被评论用户名"
                align="center"
                prop="becommentUserName"
            />
            <el-table-column
                label="评论用户id"
                align="center"
                prop="commentUserId"
            />
            <el-table-column
                label="评论用户名"
                align="center"
                prop="commentUserName"
            />
            <el-table-column label="评论点赞数" align="center" prop="likes" />
            <el-table-column label="评论内容" align="center" prop="content" />
            <el-table-column label="备注" align="center" prop="remark" />
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
                        v-hasPermi="['blog:comment:edit']"
                        >修改</el-button
                    >
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['blog:comment:remove']"
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

        <!-- 添加或修改博客评论对话框 -->
        <el-dialog
            :title="title"
            :visible.sync="open"
            width="700px"
            append-to-body
        >
            <el-form
                ref="form"
                :model="form"
                :rules="rules"
                label-width="100px"
            >
                <el-form-item label="博客id" prop="blogId">
                    <el-input
                        v-model="form.blogId"
                        placeholder="请输入博客id"
                    />
                </el-form-item>
                <el-form-item label="父级评论" prop="parentId">
                    <el-input
                        v-model="form.parentId"
                        placeholder="请输入父级评论"
                    />
                </el-form-item>
                <el-form-item label="被评论用户id" prop="becommentUserId">
                    <el-input
                        v-model="form.becommentUserId"
                        placeholder="请输入被评论用户id"
                    />
                </el-form-item>
                <el-form-item label="被评论用户名" prop="becommentUserName">
                    <el-input
                        v-model="form.becommentUserName"
                        placeholder="请输入被评论用户名"
                    />
                </el-form-item>
                <el-form-item label="评论用户id" prop="commentUserId">
                    <el-input
                        v-model="form.commentUserId"
                        placeholder="请输入评论用户id"
                    />
                </el-form-item>
                <el-form-item label="评论用户名" prop="commentUserName">
                    <el-input
                        v-model="form.commentUserName"
                        placeholder="请输入评论用户名"
                    />
                </el-form-item>
                <el-form-item label="评论点赞数" prop="likes">
                    <el-input
                        v-model="form.likes"
                        placeholder="请输入评论点赞数"
                    />
                </el-form-item>
                <el-form-item label="评论内容">
                    <mavon-editor
                        v-model="form.content"
                        :ishljs="true"
                        ref="md"
                        @imgAdd="this.imgAdd"
                        @imgDel="this.imgDel"
                    />
                </el-form-item>
                <el-form-item label="备注" prop="remark">
                    <el-input v-model="form.remark" placeholder="请输入备注" />
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
    listComment,
    getComment,
    delComment,
    addComment,
    updateComment,
    exportComment,
} from "@/api/blog/comment";
import { addImg } from "@/api/tool/upload.js";
export default {
    name: "Comment",
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
            // 博客评论表格数据
            commentList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                blogId: null,
                parentId: null,
                becommentUserId: null,
                becommentUserName: null,
                commentUserId: null,
                commentUserName: null,
                likes: null,
                content: null,
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {},
        };
    },
    created() {
        this.getList();
    },
    methods: {
        /** 查询博客评论列表 */
        getList() {
            this.loading = true;
            listComment(this.queryParams).then((response) => {
                this.commentList = response.rows;
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
                commentId: null,
                blogId: null,
                parentId: null,
                becommentUserId: null,
                becommentUserName: null,
                commentUserId: null,
                commentUserName: null,
                likes: null,
                content: null,
                createTime: null,
                updateTime: null,
                remark: null,
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
            this.ids = selection.map((item) => item.commentId);
            this.single = selection.length !== 1;
            this.multiple = !selection.length;
        },
        /** 新增按钮操作 */
        handleAdd() {
            this.reset();
            this.open = true;
            this.title = "添加博客评论";
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const commentId = row.commentId || this.ids;
            getComment(commentId).then((response) => {
                this.form = response.data;
                this.open = true;
                this.title = "修改博客评论";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate((valid) => {
                if (valid) {
                    if (this.form.commentId != null) {
                        updateComment(this.form).then((response) => {
                            this.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        addComment(this.form).then((response) => {
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
            const commentIds = row.commentId || this.ids;
            this.$confirm(
                '是否确认删除博客评论编号为"' + commentIds + '"的数据项?',
                "警告",
                {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                }
            )
                .then(function () {
                    return delComment(commentIds);
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
            this.$confirm("是否确认导出所有博客评论数据项?", "警告", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
            })
                .then(() => {
                    this.exportLoading = true;
                    return exportComment(queryParams);
                })
                .then((response) => {
                    this.download(response.msg);
                    this.exportLoading = false;
                })
                .catch(() => {});
        },
        imgAdd(pos, $file) {
            var formdata = new FormData();
            formdata.append("file", $file);
            addImg(formdata).then((res) => {
                console.log(res);
                let _res = res.data;
                this.$refs.md.$img2Url(pos, _res);
            });
        },
        imgDel(pos) {
            delete this.img_file[pos];
        },
    },
};
</script>
