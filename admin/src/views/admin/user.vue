<template>
    <div>
        <p>
            <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit green"></i>
                新增
            </button>
            &nbsp;
            <!-- PAGE CONTENT BEGINS -->
            <button v-on:click="list()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-times red2"></i>
                刷新
            </button>
        </p>

        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>登录名</th>
                <th>昵称</th>
                <th>密码</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <!--eslint-disable-next-line-->
            <tr v-for="user in users">
                <td>{{user.loginName}}</td>
                <td>{{user.name}}</td>
                <td>{{user.password}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-info" v-on:click="updatePassword(user)">
                            <i class="ace-icon fa fa-key bigger-120"></i>
                        </button>
                        <button class="btn btn-xs btn-info" v-on:click="edit(user)">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-danger" v-on:click="del(user.id)">
                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                        </button>
                    </div>

                </td>

            </tr>
            </tbody>
        </table><br>

        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

        <el-dialog :title="title" :visible.sync="dialogFormVisible" width="30%">
            <el-form :model="user">
                <el-form-item label="登录名" :label-width="formLabelWidth" >
                    <el-input v-model="user.loginName"  :disabled="isDisable"></el-input>
                </el-form-item>

                <el-form-item label="昵称" :label-width="formLabelWidth" style="text-align: center">
                    <el-input v-model="user.name" ></el-input>
                </el-form-item>

                <el-form-item v-show="showAble" label="密码" :label-width="formLabelWidth" style="text-align: center">
                    <el-input type="password" v-model="user.password" ></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="修改密码" :visible.sync="passwordFormVisible" width="30%">
            <el-form :model="user">
                <el-form-item label="登录名" :label-width="formLabelWidth" >
                    <el-input v-model="user.loginName"  :disabled="isDisable"></el-input>
                </el-form-item>

                <el-form-item  label="密码" :label-width="formLabelWidth" style="text-align: center">
                    <el-input type="password" v-model="user.password" ></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="passwordFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="savePassword">确 定</el-button>
            </div>
        </el-dialog>

    </div>


</template>

<script>
    import Pagination from '../../components/pagination';
    import {alertSuccess, alertWarn} from '../../../public/static/js/toast.js';
    import {showConfirm} from '../../../public/static/js/confirm.js';
    import {require, length} from "../../../public/static/js/validator";

    export default {
        components: {Pagination},
        name: 'business-user',
        data: function () {
            return {
                users: [],
                user: {},
                title: "",
                isDisable: false,
                dialogFormVisible: false,
                showAble: true,
                formLabelWidth: '120px',
                passwordFormVisible: false
            }
        },
        mounted() {
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.list();
        },
        methods: {
            /**
             * 删除
             */
            del(id) {
                let _this = this;
                showlConfirm("删除列表，操作不可恢复", function () {
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/user/delete/' + id).then((response) => {
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
            edit(user) {
                let _this = this;
                console.log("编辑:"+user.name );
                _this.user = $.extend({}, user);
                _this.isDisable = true;
                _this.dialogFormVisible = true;
                _this.title = "编辑用户";
                _this.showAble = false;
            },
            /**
             * 新增
             */
            add() {
                let _this = this;
                _this.user = {}
                _this.isDisable = false;
                _this.dialogFormVisible = true;
                _this.title = "新增用户";
                _this.showAble = true;
            },
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/list', {
                    page: page,
                    pageSize: _this.$refs.pagination.size,
                }).then((response) => {
                        Loading.hide();
                        console.log("查询章列表结果:", response);
                        _this.users = response.data.content.list;
                        _this.$refs.pagination.render(page, response.data.content.total);
                    }
                )
            },
            /**
             * 新增
             */
            save() {
                let _this = this;
                //保存校验 TO DO
                if (!require(_this.user.loginName, '登录名')) {
                    return;
                }
                if (!length(_this.user.loginName, '登录名', 1, 50)) {
                    return;
                }
                if (!require(_this.user.name, '昵称')) {
                    return;
                }
                if (!length(_this.user.name, '昵称', 1, 50)) {
                    return;
                }
                if (!require(_this.user.password, '密码')) {
                    return;
                }
                _this.user.password = hex_md5(_this.user.password + KEY);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/save', {
                    id: _this.user.id,
                    loginName: _this.user.loginName,
                    name: _this.user.name,
                    password: _this.user.password,
                }).then((response) => {
                        if (response.data.success) {
                            alertSuccess("保存成功");
                            _this.list(1);
                        } else {
                            alertWarn(response.data.message);
                        }
                        _this.dialogFormVisible = false
                    }
                );
            },
            /**
             * 弹出更新密码模态框
             */
            updatePassword(user) {
                let _this = this;
                _this.passwordFormVisible = true;
                _this.user = $.extend({}, user);
                _this.user.password = "";
            },
            /**
             * 更新密码
             */
            savePassword() {
                let _this = this;
                // 密码md5加密
                _this.user.password = hex_md5(_this.user.password + KEY);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/save-password', {
                    id: _this.user.id,
                    password: _this.user.password
                }).then((resp) => {
                    if (resp.data.success) {
                        alertSuccess("修改成功");
                        _this.list(1);
                    } else {
                        alertWarn(resp.data.message);
                    }
                    _this.passwordFormVisible = false;
                });
            }
        }
    }
</script>

<style>
    .el-form-item  label {
        text-align: center;
    }
</style>