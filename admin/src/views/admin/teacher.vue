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
                    <th>姓名</th>
                    <th>昵称</th>
                    <th>头像</th>
                    <th>职位</th>
                    <th>座右铭</th>
                    <th>简介</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <!--eslint-disable-next-line-->
            <tr v-for="teacher in teachers">
                    <td>{{teacher.id}}</td>
                    <td>{{teacher.name}}</td>
                    <td>{{teacher.nickname}}</td>
                    <td>{{teacher.image}}</td>
                    <td>{{teacher.position}}</td>
                    <td>{{teacher.motto}}</td>
                    <td>{{teacher.intro}}</td>
                    <td>{{teacher.gmtCreate}}</td>
                    <td>{{teacher.gmtModified}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-info" v-on:click="edit(teacher)">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-danger" v-on:click="del(teacher.id)">
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
                        <h5 class="modal-title">新增</h5>
                    </div>
                    <div class="modal-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">id</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="teacher.id" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="teacher.name" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">昵称</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="teacher.nickname" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">头像</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="teacher.image" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">职位</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="teacher.position" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">座右铭</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="teacher.motto" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">简介</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="teacher.intro" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">创建时间</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="teacher.gmtCreate" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">修改时间</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="teacher.gmtModified" />
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
        name: 'business-teacher',
        data: function () {
            return {
                teachers: [],
            teacher:
            {
            }
        }
        },
        mounted() {
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.$parent.activeSidebar("business-teacher-sidebar");
            _this.list();
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
                $(".modal").modal("show");
                _this.teacher = $.extend({}, teacher);
                console.log(_this.teacher)
            },
            /**
             * 新增
             */
            add() {
                let _this = this;
                _this.teacher = {}
                console.log('新增列表')
                $(".modal").modal("show");
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
                    if (!require(_this.teacher.id, 'id')){
                        return;
                    }
                    if (!require(_this.teacher.name, '姓名')){
                        return;
                    }
                    if (!length(_this.teacher.name, '姓名',1,50)) {
                        return;
                    }
                    if (!length(_this.teacher.nickname, '昵称',1,50)) {
                        return;
                    }
                    if (!length(_this.teacher.image, '头像',1,100)) {
                        return;
                    }
                    if (!length(_this.teacher.position, '职位',1,50)) {
                        return;
                    }
                    if (!length(_this.teacher.motto, '座右铭',1,50)) {
                        return;
                    }
                    if (!length(_this.teacher.intro, '简介',1,500)) {
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