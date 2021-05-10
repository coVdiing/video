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

        <div class="row">
            <div v-for="course in courses" class="col-md-3">
                <div class="thumbnail search-thumbnail">
                    <img v-show="!course.image" class="media-object" src="/static/image/demo-course.jpg"/>
                    <img v-show="course.image" class="media-object" v-bind:src="course.image"/>
                    <div class="caption">
                        <div class="clearfix">
                            <span class="pull-right label label-primary info-label">
                                {{showCourseLevel(course.level)}}
                            </span>
                            <span class="pull-right label label-primary info-label">
                                {{showCharge(course.charge)}}
                            </span>
                            <span class="pull-right label label-primary info-label">
                                {{showCourseStatus(course.status)}}
                            </span>
                        </div>

                        <h3 class="search-title">
                            <a href="#" class="blue">{{course.name}}</a>
                        </h3>
                        <p>
                            <span class="blue bolder bigger-150">{{course.price}}&nbsp;<i class="fa fa-rmb"></i></span>
                        </p>
                        <p>{{course.summary}}</p>
                        <p>
                            <span class="badge badge-info">{{course.id}}</span>
                            <span class="badge badge-info">排序:{{course.sort}}</span>
                            <span class="badge badge-info">时长:{{course.time}}</span>
                        </p>
                        <p>
                            <button class="btn btn-white btn-xs btn-info" v-on:click="toChapter(course)">
                                大章
                            </button>
                            <button class="btn btn-white btn-xs btn-info" v-on:click="edit(course)">
                                编辑
                            </button>
                            <button class="btn btn-white btn-xs btn-danger" v-on:click="del(course.id)">
                                删除
                            </button>
                        </p>
                    </div>
                </div>
            </div>
        </div>

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
                                <input class="form-control" v-model="course.id"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="course.name"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">概述</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="course.summary"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">时长</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="course.time"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">价格(元)</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="course.price"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">封面</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="course.image"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">级别</label>
                            <div class="col-sm-10">
                                <select v-model="course.level" class="form-control">
                                    <option v-for="ele in COURSE_LEVEL" v-bind:value="ele.key">{{ele.value}}</option>
                                </select>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">收费</label>
                            <div class="col-sm-10">
                                <select v-model="course.charge" class="form-control">
                                    <option v-for="ele in CHARGE" v-bind:value="ele.key">{{ele.value}}</option>
                                </select>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">状态</label>
                            <div class="col-sm-10">
                                <select v-model="course.status" class="form-control">
                                    <option v-for="ele in COURSE_STATUS" v-bind:value="ele.key">{{ele.value}}</option>
                                </select>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">报名数</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="course.enroll"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">顺序</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="course.sort"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">创建时间</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="course.gmtCreate"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">修改时间</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="course.gmtModified"/>
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
    import {showCharge, showCourseLevel, showCourseStatus} from "../../../public/static/js/common";

    export default {
        components: {Pagination},
        name: 'business-course',
        data: function () {
            return {
                courses: [],
                course: {},
                CHARGE: CHARGE,
                COURSE_LEVEL: COURSE_LEVEL,
                COURSE_STATUS: COURSE_STATUS,
            }
        },
        mounted() {
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.$parent.activeSidebar("business-course-sidebar");
            _this.list();
        },
        methods: {
            /**
             * 删除
             */
            del(id) {
                let _this = this;
                showConfirm("删除列表，操作不可恢复", function () {
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/course/delete/' + id).then((response) => {
                            console.log("删除结果:", response);
                            _this.list(1);
                        }
                    )
                    alertSuccess("已删除");
                });
            },
            /**
             * 点击跳转到对应大章
             */
            toChapter(course) {
                let _this = this;
                SessionStorage.set("course", course);
                _this.$router.push("/business/chapter");
            },
            /**
             * 编辑
             */
            edit(course) {
                let _this = this;
                $(".modal").modal("show");
                _this.course = $.extend({}, course);
            },
            /**
             * 新增
             */
            add() {
                let _this = this;
                _this.course = {}
                console.log('新增列表')
                $(".modal").modal("show");
            },
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/list', {
                    page: page,
                    pageSize: _this.$refs.pagination.size,
                }).then((response) => {
                        Loading.hide();
                        console.log("查询章列表结果:", response);
                        _this.courses = response.data.content.list;
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
                console.log("name:" + _this.course.name + ",id:" + _this.course.courseId);
                //保存校验 TO DO
                if (!require(_this.course.name, '名称')) {
                    return;
                }
                if (!length(_this.course.name, '名称', 1, 50)) {
                    return;
                }
                if (!length(_this.course.summary, '概述', 1, 2000)) {
                    return;
                }
                // if (!length(_this.course.image, '封面', 1, 100)) {
                //     return;
                // }

                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/save', {
                    id: _this.course.id,
                    name: _this.course.name,
                    summary: _this.course.summary,
                    time: _this.course.time,
                    price: _this.course.price,
                    image: _this.course.image,
                    level: _this.course.level,
                    charge: _this.course.charge,
                    status: _this.course.status,
                    enroll: _this.course.enroll,
                    sort: _this.course.sort,
                    gmtCreate: _this.course.gmtCreate,
                    gmtModified: _this.course.gmtModified,
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
            showCharge(charge) {
                return showCharge(charge);
            },
            showCourseLevel(level) {
                return showCourseLevel(level);
            },
            showCourseStatus(status) {
                return showCourseStatus(status);
            }

        }
    }
</script>

<style scoped>
    .caption h3 {
        font-size: 20px;
    }
</style>