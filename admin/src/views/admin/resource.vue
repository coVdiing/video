<template>
    <div>
        <el-row>
            <el-col :span="12">
                <div class="grid-content bg-purple">
                    <p>
                        <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                            <i class="ace-icon fa fa-edit green"></i>
                            保存资源
                        </button>
                        &nbsp;
                        <!-- PAGE CONTENT BEGINS -->
                        <button v-on:click="list()" class="btn btn-white btn-default btn-round">
                            <i class="ace-icon fa fa-times red2"></i>
                            获取资源JSON
                        </button>
                    </p>
                    <p>
                        <el-input
                                type="textarea"
                                :autosize="{ minRows: 2, maxRows: 4}"
                                placeholder="请输入内容"
                                v-model="resourceJson">
                        </el-input>
                    </p>
                </div>
            </el-col>
            <el-col :span="12">
                <div class="grid-content bg-purple-light">
                    <div>
                        <el-tree :data="resourceTree" :props="defaultProps"></el-tree>
                    </div>
                </div>
            </el-col>
        </el-row>


        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>id</th>
                <th>名称</th>
                <th>页面</th>
                <th>请求</th>
                <th>父id</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <!--eslint-disable-next-line-->
            <tr v-for="resource in resources">
                <td>{{resource.id}}</td>
                <td>{{resource.name}}</td>
                <td>{{resource.page}}</td>
                <td>{{resource.request}}</td>
                <td>{{resource.parent}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-info" v-on:click="edit(resource)">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-danger" v-on:click="del(resource.id)">
                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                        </button>
                    </div>

                    <div class="hidden-md hidden-lg">
                        <div class="inline pos-rel">
                            <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown"
                                    data-position="auto">
                                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                            </button>

                            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                <li>
                                    <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
                                                                    <span class="blue">
                                                                        <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                                                    </span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                                                    <span class="green">
                                                                        <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                                    </span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                                                    <span class="red">
                                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                                    </span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </td>

            </tr>
            </tbody>
        </table>

        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

        <div class="modal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">新增资源</h5>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">id</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="resource.id"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="resource.name"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">页面</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="resource.page"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">请求</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="resource.request"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">父id</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="resource.parent"/>
                            </div>
                        </div>
                        <br>
                        <br>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" v-on:click="save()">保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


</template>

<script>
    import Pagination from '../../components/pagination';
    import {alertSuccess, alertWarn} from '../../../public/static/js/toast.js';
    import {showConfirm} from '../../../public/static/js/confirm.js';
    import {require, length} from "../../../public/static/js/validator";

    export default {
        components: {Pagination},
        name: 'business-resource',
        data: function () {
            return {
                resources: [],
                resource: {},
                resourceJson: "",
                resourceTree: [{
                    label: '系统管理',
                    children: [{
                        label: '用户管理',
                        children: [{
                            label: '保存'
                        }, {
                            label: '删除'
                        }, {
                            label: '重置密码'
                        }]
                    }, {
                        label: '资源管理',
                        children: [{
                            label: '保存/显示',
                            children: []
                        }]
                    }, {
                        label: '角色管理',
                        children: [{
                            label: '角色/权限管理',
                            children: []
                        }]
                    }]
                }],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                }
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
                showConfirm("删除资源列表，操作不可恢复", function () {
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/resource/delete/' + id).then((response) => {
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
            edit(resource) {
                let _this = this;
                $(".modal").modal("show");
                _this.resource = $.extend({}, resource);
                console.log(_this.resource)
            },
            /**
             * 新增
             */
            add() {
                let _this = this;
                _this.resource = {}
                console.log('新增资源列表')
                $(".modal").modal("show");
            },
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/list', {
                    page: page,
                    pageSize: _this.$refs.pagination.size,
                }).then((response) => {
                        Loading.hide();
                        console.log("查询章列表结果:", response);
                        _this.resources = response.data.content.list;
                        _this.$refs.pagination.render(page, response.data.content.total);
                    }
                )
            },
            /**
             * 新增资源
             */
            save() {
                console.log("新增资源")
                let _this = this;
                console.log("name:" + _this.resource.name + ",id:" + _this.resource.courseId);
                //保存校验 TO DO
                if (!require(_this.resource.id, 'id')) {
                    return;
                }
                if (!require(_this.resource.name, '名称')) {
                    return;
                }
                if (!length(_this.resource.name, '名称', 1, 100)) {
                    return;
                }
                if (!length(_this.resource.page, '页面', 1, 50)) {
                    return;
                }
                if (!length(_this.resource.request, '请求', 1, 200)) {
                    return;
                }

                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/save', {
                    id: _this.resource.id,
                    name: _this.resource.name,
                    page: _this.resource.page,
                    request: _this.resource.request,
                    parent: _this.resource.parent,
                }).then((response) => {
                        if (response.data.success) {
                            alertSuccess("保存成功");
                            $(".modal").modal("hide");
                            _this.list(1);
                        } else {
                            alertWarn(response.data.message);
                        }
                    }
                );
            }
        }
    }
</script>