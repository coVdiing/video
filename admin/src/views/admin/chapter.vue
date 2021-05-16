<template>
    <div>
        <h4 class="lighter">
            <router-link to="/business/course" class="pink">{{course.name}}</router-link>
        </h4>
        <hr>
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
            <router-link to="/business/course" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-arrow-left"></i>
                返回课程
            </router-link>
        </p>
        <div class="alert alert-warning" id="warn" role="alert" hidden="hidden">
            A simple warning alert—check it out!
        </div>
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>名称</th>
                <th>课程</th>
                <th>排序</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <!--eslint-disable-next-line-->
            <tr v-for="chapter in chapters">
                <td>{{chapter.name}}</td>
                <td>{{course.name}}</td>
                <td>{{chapter.sort}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-white btn-xs btn-info" v-on:click="toSection(chapter)">
                            小节
                        </button>
                        <button class="btn btn-xs btn-info" v-on:click="edit(chapter)">
                            编辑
                        </button>

                        <button class="btn btn-xs btn-danger" v-on:click="del(chapter.id)">
                            删除
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
                        <h5 class="modal-title">{{title}}</h5>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">课程</label>
                            <p class="col-sm-10">{{course.name}}</p>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="chapter.name" placeholder="名称"/>
                            </div>
                        </div>
                        <br>
                        <br>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">排序</label>
                            <el-select class="col-sm-10" v-model="chapter.sort">
                                <el-option v-for="item in sortArr"
                                           :key="item"
                                           :label="item"
                                           :value="item"></el-option>
                            </el-select>
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
    import course from "./course";

    export default {
        components: {Pagination},
        name: 'chapter',
        data: function () {
            return {
                chapters: [],
                chapter: {},
                course: {},
                title: "",
                sortArr: []
            }
        },
        mounted() {
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.$parent.activeSidebar("business-course-sidebar");
            _this.course = SessionStorage.get("course") || {}
            if (Tool.isEmpty(course)) {
                _this.$router.push("/welcome");
            }
            _this.list();
        },
        methods: {
            /**
             * 删除
             */
            del(id) {
                let _this = this;
                showConfirm("删除大章列表，操作不可恢复", function () {
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/chapter/delete/' + id).then((response) => {
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
            edit(chapter) {
                let _this = this;
                $(".modal").modal("show");
                _this.title = "修改大章";
                _this.chapter = $.extend({}, chapter);
                console.log(_this.chapter)
            },
            /**
             * 新增
             */
            add() {
                let _this = this;
                _this.chapter = {};
                _this.title = "新增大章";
                $(".modal").modal("show");
            },
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/list', {
                    page: page,
                    pageSize: _this.$refs.pagination.size,
                    courseId: _this.course.id
                }).then((response) => {
                        Loading.hide();
                        console.log("查询章列表结果:", response);
                        _this.chapters = response.data.content.list;
                        _this.$refs.pagination.render(page, response.data.content.total);
                        _this.sortArr = [];
                        for (let i = 1; i <= response.data.content.total; i++) {
                            _this.sortArr.push(i);
                        }
                        console.log("sortArr:" + _this.sortArr);
                    }
                )
            },
            /**
             * 新增章节
             */
            save() {
                console.log("新增章节")
                let _this = this;
                if (!require(_this.chapter.name, "章节名") ||
                    !length(_this.chapter.name, "章节名", 2, 18)) {
                    return;
                }
                _this.chapter.courseId = _this.course.id;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/save', {
                    name: _this.chapter.name,
                    courseId: _this.chapter.courseId,
                    id: _this.chapter.id,
                    sort: _this.chapter.sort
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
            toSection(chapter) {
                let _this = this;
                SessionStorage.set("chapter", chapter);
                _this.$router.push("/business/section");
            }
        }
    }
</script>