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
        <div class="alert alert-warning" id="warn" role="alert" hidden="hidden">
            A simple warning alert—check it out!
        </div>
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                                    <th>id</th>
                    <th>父id</th>
                    <th>名称</th>
                    <th>排序</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <!--eslint-disable-next-line-->
            <tr v-for="category in categorys">
                    <td>{{category.id}}</td>
                    <td>{{category.parent}}</td>
                    <td>{{category.name}}</td>
                    <td>{{category.sort}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-info" v-on:click="edit(category)">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-danger" v-on:click="del(category.id)">
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
                        <h5 class="modal-title">新增商品分类 </h5>
                    </div>
                    <div class="modal-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">id</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="category.id" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">父id</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="category.parent" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="category.name" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">排序</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="category.sort" />
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
        name: 'business-category',
        data: function () {
            return {
                categorys: [],
            category:
            {
            }
        }
        },
        mounted() {
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.$parent.activeSidebar("business-category-sidebar");
            _this.list();
        },
        methods: {
            /**
             * 删除
             */
            del(id) {
                let _this = this;
                showConfirm("删除商品分类 列表，操作不可恢复", function () {
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/category/delete/' + id).then((response) => {
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
            edit(category) {
                let _this = this;
                $(".modal").modal("show");
                _this.category = $.extend({}, category);
                console.log(_this.category)
            },
            /**
             * 新增
             */
            add() {
                let _this = this;
                _this.category = {}
                console.log('新增商品分类 列表')
                $(".modal").modal("show");
            },
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/list', {
                    page: page,
                    pageSize: _this.$refs.pagination.size,
                }).then((response) => {
                        Loading.hide();
                        console.log("查询章列表结果:", response);
                        _this.categorys = response.data.content.list;
                        _this.$refs.pagination.render(page, response.data.content.total);
                    }
                )
            },
            /**
             * 新增商品分类 
             */
            save() {
                console.log("新增商品分类 ")
                let _this = this;
                console.log("name:" + _this.category.name + ",id:" + _this.category.courseId);
                //保存校验 TO DO
                    if (!require(_this.category.id, 'id')){
                        return;
                    }
                    if (!require(_this.category.parent, '父id')){
                        return;
                    }
                    if (!require(_this.category.name, '名称')){
                        return;
                    }
                    if (!length(_this.category.name, '名称',1,50)) {
                        return;
                    }

                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/save', {
                    id: _this.category.id,
                    parent: _this.category.parent,
                    name: _this.category.name,
                    sort: _this.category.sort,
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
            },
            /**
             * 去除字符串两边的空格
             * @param str
             * @returns {*}
             */
            trim(str) {
                return str.replace(/(^\s*)|(\s*$)/g, "");
            }
        }
    }
</script>