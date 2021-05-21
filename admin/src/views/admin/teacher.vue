<template>
    <div>
        <p>
            <el-button @click="add()" type="primary" size="small" style="margin-left: 16px;">
                新增
            </el-button>
            &nbsp;
            <el-button v-on:click="list(1)" type="primary" size="small">
                刷新
            </el-button>


        </p>

        <el-table
                :data="teachers"
                style="width: 100%;margin-bottom: 20px">
            <el-table-column type="expand">
                <template #default="props">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="昵称">
                            <span>{{props.row.nickname}}</span>
                        </el-form-item>
                        <el-form-item label="头像">
                            <img v-show="!props.row.image" class="media-object" src="/static/image/avatar.png"/>
                            <img v-show="props.row.image" class="media-object"
                                 v-bind:src="imageUrlBase+props.row.image"/>
                        </el-form-item>
                        <el-form-item label="简介">
                            <span>{{ props.row.intro }}</span>
                        </el-form-item>
                        <el-form-item label="座右铭">
                            <span>{{ props.row.motto }}</span>
                        </el-form-item>
                        <el-form-item label="加入时间">
                            <span>{{ props.row.gmtCreate}}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column
                    label="讲师 ID"
                    prop="id">
            </el-table-column>
            <el-table-column
                    label="讲师名称"
                    prop="name">
            </el-table-column>
            <el-table-column
                    label="讲师职位"
                    prop="position">
            </el-table-column>
            <el-table-column label="操作">
                <template #default="props">
                    <div class="hidden-sm hidden-xs btn-group">
                        <el-button type="primary" icon="el-icon-edit" v-on:click="edit(props.row)"></el-button>
                        <el-button type="danger" icon="el-icon-delete" v-on:click="del(props.row.id)"></el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <div style="text-align: center">
            <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
        </div>
        <el-drawer
                :title="title"
                :visible.sync="drawer"
                :direction="direction"
                :before-close="handleClose">
            <el-container>
                <el-main>
                    <el-form :model="teacher" label-width="80px">
                        <el-form-item label="姓名">
                            <el-input v-model="teacher.name"></el-input>
                        </el-form-item>
                        <el-form-item label="昵称">
                            <el-input v-model="teacher.nickname"></el-input>
                        </el-form-item>
                        <el-form-item label="头像"  v-show="!teacher.id">
                            <el-upload
                                    class="upload-demo"
                                    ref="upload"
                                    :action="actionUrl"
                                    :on-preview="handlePreview"
                                    :on-remove="handleRemove"
                                    :on-success="(res, file)=> { return uploadSuccess(res, file)}"
                                    :file-list="fileList"
                                    :auto-upload="false"
                                   >
                                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">
                                    上传到服务器
                                </el-button>
                                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
                            </el-upload>

                            <!-- 不能编辑头像，暂时注释掉 -->
<!--                            <el-avatar v-show="teacher.id && teacher.image" shape="square" :size="100" :fit="fit"-->
<!--                                       :src="imageUrlBase+teacher.image"></el-avatar>-->
<!--                            <el-avatar v-show="teacher.id && !teacher.image" shape="square" :size="100" :fit="fit"-->
<!--                                       src="/static/image/avatar.png"></el-avatar>-->

                        </el-form-item>
                        <el-form-item label="职位">
                            <el-input v-model="teacher.position"/>
                        </el-form-item>
                        <el-form-item label="座右铭">
                            <el-input v-model="teacher.motto"/>
                        </el-form-item>
                        <el-form-item label="简介">
                            <el-input v-model="teacher.intro"/>
                        </el-form-item>
                    </el-form>
                </el-main>
                <el-footer>
                    <div class="form-group" style="text-align: center">
                        <el-button type="info" @click="drawer=false">关闭</el-button>
                        <el-button type="primary" @click="save()">保存</el-button>
                    </div>
                </el-footer>
            </el-container>
        </el-drawer>

    </div>


</template>

<script>
    import Pagination from '../../components/pagination';
    import {alertSuccess, alertWarn} from '../../../public/static/js/toast.js';
    import {showConfirm} from '../../../public/static/js/confirm.js';
    import {require, length} from "../../../public/static/js/validator";

    export default {
        components: {Pagination},
        name: 'business-teacher',
        data: function () {
            return {
                teachers: [],
                teacher: {},
                title: "",
                fileList: [],
                drawer: false,
                direction: 'ltr',
                imageUrlBase: process.env.VUE_APP_SERVER + '/business/',
                actionUrl: process.env.VUE_APP_SERVER + '/business/file/upload'
            }
        },
        mounted() {
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.$parent.activeSidebar("business-teacher-sidebar");
            _this.list(1);
        },
        methods: {
            /**
             * 删除
             */
            del(id) {
                let _this = this;
                showConfirm("删除列表，操作不可恢复", function () {
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/teacher/delete/' + id).then((response) => {
                            console.log("删除结果:", response);
                            _this.list(1);
                        }
                    )
                    alertSuccess("已删除");
                });
            },
            /**
             * 编辑
             */
            edit(teacher) {
                let _this = this;
                _this.title = "修改讲师";
                _this.drawer = true;
                _this.teacher = $.extend({}, teacher);
            },
            /**
             * 新增
             */
            add() {
                let _this = this;
                _this.drawer = true;
                _this.title = "新增讲师";
                _this.teacher = {}
                console.log('新增列表')
            },
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/teacher/list', {
                    page: page,
                    pageSize: _this.$refs.pagination.size,
                }).then((response) => {
                        Loading.hide();
                        console.log("查询章列表结果:", response);
                        _this.teachers = response.data.content.list;
                        _this.$refs.pagination.render(page, response.data.content.total);
                    }
                )
            },
            /**
             * 新增
             */
            save() {
                console.log("新增")
                let _this = this;
                console.log("name:" + _this.teacher.name + ",id:" + _this.teacher.courseId);
                //保存校验 TO DO
                if (!require(_this.teacher.name, '姓名')) {
                    return;
                }
                if (!length(_this.teacher.name, '姓名', 1, 50)) {
                    return;
                }
                if (!length(_this.teacher.nickname, '昵称', 1, 50)) {
                    return;
                }
                if (!length(_this.teacher.position, '职位', 1, 50)) {
                    return;
                }
                if (!length(_this.teacher.motto, '座右铭', 1, 50)) {
                    return;
                }
                if (!length(_this.teacher.intro, '简介', 1, 500)) {
                    return;
                }

                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/teacher/save', {
                    id: _this.teacher.id,
                    name: _this.teacher.name,
                    nickname: _this.teacher.nickname,
                    image: _this.teacher.image,
                    position: _this.teacher.position,
                    motto: _this.teacher.motto,
                    intro: _this.teacher.intro,
                    gmtCreate: _this.teacher.gmtCreate,
                    gmtModified: _this.teacher.gmtModified,
                }).then((response) => {
                        if (response.data.success) {
                            alertSuccess("保存成功");
                            _this.drawer = false;
                            _this.list(1);
                        } else {
                            alertWarn(response.data.message);
                        }
                    }
                );
            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {
                    });
            },
            /**
             * 上传图片
             */
            uploadSuccess(resp, file) {
                let _this = this;
                _this.teacher.image = resp.content;
            },
            submitUpload() {
                this.$refs.upload.submit();
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            imageUrl(url) {
                let _this = this;
                return _this.imageUrlBase + url;
            }

        }
    }
</script>

<style>
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
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