<template>
    <div class="app-container">
        <el-form
            :model="queryParams"
            ref="queryForm"
            :inline="true"
            v-show="showSearch"
            label-width="68px"
        >
            <el-form-item label="用户Id" prop="userId">
                <el-input
                    v-model="queryParams.userId"
                    placeholder="请输入博主"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="文章标题" prop="tittle">
                <el-input
                    v-model="queryParams.tittle"
                    placeholder="请输入文章标题"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="音乐id" prop="musicId">
                <el-input
                    v-model="queryParams.musicId"
                    placeholder="请输入音乐id"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="分类id" prop="categoryId">
                <el-input
                    v-model="queryParams.categoryId"
                    placeholder="请输入分类id"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="概要" prop="summary">
                <el-input
                    v-model="queryParams.summary"
                    placeholder="请输入概要"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="浏览量" prop="pageviews">
                <el-input
                    v-model="queryParams.pageviews"
                    placeholder="请输入浏览量"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="点赞数" prop="likes">
                <el-input
                    v-model="queryParams.likes"
                    placeholder="请输入点赞数"
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
                    v-hasPermi="['blog:article:add']"
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
                    v-hasPermi="['blog:article:edit']"
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
                    v-hasPermi="['blog:article:remove']"
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
                    v-hasPermi="['blog:article:export']"
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
            :data="articleList"
            @selection-change="handleSelectionChange"
        >
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="文章id" align="center" prop="articleId" />
            <el-table-column label="博主" align="center" prop="userId" />
            <el-table-column label="文章标题" align="center" prop="tittle" />
            <el-table-column label="文章内容" align="center" prop="content" />
            <el-table-column label="音乐id" align="center" prop="musicId" />
            <el-table-column label="分类id" align="center" prop="categoryId" />
            <el-table-column label="头像链接" align="center" prop="avator" />
            <el-table-column label="概要" align="center" prop="summary" />
            <el-table-column label="浏览量" align="center" prop="pageviews" />
            <el-table-column label="点赞数" align="center" prop="likes" />
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
                        v-hasPermi="['blog:article:edit']"
                        >修改</el-button
                    >
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['blog:article:remove']"
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

        <!-- 添加或修改博客文章对话框 -->
        <el-dialog
            :title="title"
            :visible.sync="open"
            width="800px"
            append-to-body
        >
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="博主" prop="userId">
                    <el-input v-model="form.userId" placeholder="请输入博主" />
                </el-form-item>
                <el-form-item label="文章标题" prop="tittle">
                    <el-input
                        v-model="form.tittle"
                        placeholder="请输入文章标题"
                    />
                </el-form-item>
                <el-form-item label="文章内容">
                    <editor v-model="form.content" :min-height="192" />
                </el-form-item>
                <el-form-item label="音乐id" prop="musicId">
                    <el-input
                        v-model="form.musicId"
                        placeholder="请输入音乐id"
                    />
                </el-form-item>
                <el-form-item label="分类id" prop="categoryId">
                    <el-input
                        v-model="form.categoryId"
                        placeholder="请输入分类id"
                    />
                </el-form-item>
                <el-form-item label="标题图" prop="avator">
                    <imageUpload
                        @getImageUrl="getImageUrl"
                        :dialogImageUrl="'http://cdn.iwannnn.cn/chrome_1fBpAefY6q.png'"
                    />
                </el-form-item>
                <el-form-item label="概要" prop="summary">
                    <el-input v-model="form.summary" placeholder="请输入概要" />
                </el-form-item>
                <el-form-item label="浏览量" prop="pageviews">
                    <el-input
                        v-model="form.pageviews"
                        placeholder="请输入浏览量"
                    />
                </el-form-item>
                <el-form-item label="点赞数" prop="likes">
                    <el-input v-model="form.likes" placeholder="请输入点赞数" />
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
    listArticle,
    getArticle,
    delArticle,
    addArticle,
    updateArticle,
    exportArticle,
} from "@/api/blog/article";
import importTable from "../../tool/gen/importTable.vue";
import imageUpload from "@/components/ImageUpload";

export default {
    components: { importTable, imageUpload },
    name: "Article",
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
            // 博客文章表格数据
            articleList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                userId: null,
                tittle: null,
                content: null,
                musicId: null,
                categoryId: null,
                avator: null,
                summary: null,
                pageviews: null,
                likes: null,
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
        /** 查询博客文章列表 */
        getList() {
            this.loading = true;
            listArticle(this.queryParams).then((response) => {
                this.articleList = response.rows;
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
                articleId: null,
                userId: null,
                tittle: null,
                content: null,
                musicId: null,
                categoryId: null,
                avator: null,
                summary: null,
                pageviews: null,
                likes: null,
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
            this.ids = selection.map((item) => item.articleId);
            this.single = selection.length !== 1;
            this.multiple = !selection.length;
        },
        /** 新增按钮操作 */
        handleAdd() {
            this.reset();
            this.open = true;
            this.title = "添加博客文章";
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const articleId = row.articleId || this.ids;
            getArticle(articleId).then((response) => {
                this.form = response.data;
                this.open = true;
                this.title = "修改博客文章";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate((valid) => {
                if (valid) {
                    if (this.form.articleId != null) {
                        updateArticle(this.form).then((response) => {
                            this.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        addArticle(this.form).then((response) => {
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
            const articleIds = row.articleId || this.ids;
            this.$confirm(
                '是否确认删除博客文章编号为"' + articleIds + '"的数据项?',
                "警告",
                {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                }
            )
                .then(function () {
                    return delArticle(articleIds);
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
            this.$confirm("是否确认导出所有博客文章数据项?", "警告", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
            })
                .then(() => {
                    this.exportLoading = true;
                    return exportArticle(queryParams);
                })
                .then((response) => {
                    this.download(response.msg);
                    this.exportLoading = false;
                })
                .catch(() => {});
        },
        getImageUrl(imageUrl) {
            this.form.avator = imageUrl;
            console.log(this, this.form.avator);
        },
    },
};
</script>
<style scoped>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #000000;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>
