<template>
    <div>

        <div class="row col-md-5">
            <p>
                <button v-on:click="addLevelOne()" class="btn btn-white btn-default btn-round">
                    <i class="ace-icon fa fa-edit green"></i>
                    新增
                </button>
                &nbsp;
                <!-- PAGE CONTENT BEGINS -->
                <button v-on:click="refresh()" class="btn btn-white btn-default btn-round">
                    <i class="ace-icon fa fa-times red2"></i>
                    刷新
                </button>
            </p>
            <table id="simple-table" class="table  table-bordered table-hover">
                <thead>
                <tr>
                    <th>id</th>
                    <th>名称</th>
                    <th>排序</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
                <!--eslint-disable-next-line-->
                <tr v-for="category in categorys" v-bind:class="{active:category.id===activeId}"
                    v-on:click="showChildren(category)">
                    <td>{{category.id}}</td>
                    <td>{{category.name}}</td>
                    <td>{{category.sort}}</td>
                    <td>
                        <div class="hidden-sm hidden-xs btn-group">
                            <button class="btn btn-xs btn-info" v-on:click="edit(category)">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                            </button>

                            <button class="btn btn-xs btn-danger" v-on:click="del(category.id,1)">
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
        </div>
        <div class="row col-md-2"></div>
        <div class="row col-md-5">
            <p>
                <button v-on:click="addLevelTwo()" class="btn btn-white btn-default btn-round">
                    <i class="ace-icon fa fa-edit green"></i>
                    新增二级
                </button>
                &nbsp;
            </p>
            <table v-show="childrenCategory.length" id="children-table" class="table  table-bordered table-hover">
                <thead>
                <tr>
                    <th>id</th>
                    <th>名称</th>
                    <th>排序</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
                <!--eslint-disable-next-line-->
                <tr v-for="category in childrenCategory">
                    <td>{{category.id}}</td>
                    <td>{{category.name}}</td>
                    <td>{{category.sort}}</td>
                    <td>
                        <div class="hidden-sm hidden-xs btn-group">
                            <button class="btn btn-xs btn-info" v-on:click="edit(category)">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                            </button>

                            <button class="btn btn-xs btn-danger" v-on:click="del(category.id,2)">
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
        </div>

        <div class="modal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">新增商品分类 </h5>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="category.name"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">排序</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="category.sort"/>
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
    import {alertSuccess, alertWarn} from '../../../public/static/js/toast.js';
    import {showConfirm} from '../../../public/static/js/confirm.js';
    import {length, require} from "../../../public/static/js/validator";

    export default {
        name: 'business-category',
        data: function () {
            return {
                categorys: [],
                category: {},
                childrenCategory: [],
                parentId: {}
            }
        },
        mounted() {
            let _this = this;
            _this.all();
        },
        methods: {
            /**
             * 删除
             */
            del(id, type) {
                let _this = this;
                showConfirm("删除商品分类 列表，操作不可恢复", function () {
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/category/delete/' + id).then((response) => {
                            console.log("删除结果:", response);
                            _this.all();
                            if (type == 1) {
                                _this.activeId = null;
                                _this.childrenCategory = [];
                            } else {
                                setTimeout(function () {
                                    $("tr.active").trigger("click");
                                }, 200)
                            }
                            alertSuccess("已删除");
                        }
                    )
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
             * 新增一级分类
             */
            addLevelOne() {
                let _this = this;
                _this.category = {}
                _this.category.parent = "00000000";
                $(".modal").modal("show");
            },
            /**
             * 新增二级分类
             */
            addLevelTwo() {
                let _this = this;
                _this.category = {}
                _this.category.parent = _this.parentId;
                $(".modal").modal("show");
            },
            /**
             * 列表查询
             */
            all() {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/all').then((response) => {
                        Loading.hide();
                        console.log('data:' + response.data.content)
                        _this.categorys = response.data.content;
                    }
                )
            },
            /**
             * 新增商品分类
             */
            save() {
                console.log("新增商品分类 ")
                let _this = this;
                //保存校验 TO DO
                if (!require(_this.category.parent, '父id')) {
                    return;
                }
                if (!require(_this.category.name, '名称')) {
                    return;
                }
                if (!length(_this.category.name, '名称', 1, 50)) {
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
                            _this.all();
                        } else {
                            alertWarn(response.data.message);
                        }
                    }
                );
                setTimeout(function () {
                    $("tr.active").trigger("click");
                }, 200)
            },
            /**
             * 展示二级菜单，并且关联的一级菜单变色
             * @param category
             */
            showChildren(category) {
                let _this = this;
                _this.childrenCategory = category.children;
                _this.activeId = category.id;
                _this.parentId = category.id;
            },
            /**
             * 刷新
             */
            refresh() {
                let _this = this;
                _this.activeId = null;
                _this.all();
            }
        }
    }
</script>

<style scoped>
    .active {
        background-color: #5e13b4;
        color: #0099ff;
    }
</style>
