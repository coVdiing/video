<template>
    <div>
        <h4 class="lighter">
            <router-link to="/business/course" class="pink">{{course.name}}</router-link><i class="ace-icon fa fa-angle-double-right"></i>
            <router-link to="/business/chapter" class="pink">{{chapter.name}}</router-link>
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
            <router-link to="/business/chapter" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-arrow-left"></i>
                返回大章
            </router-link>
        </p>
        <div class="alert alert-warning" id="warn" role="alert" hidden="hidden">
            A simple warning alert—check it out!
        </div>
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                    <th>标题</th>
                    <th>课程</th>
                    <th>大章</th>
                    <th>视频</th>
                    <th>时长</th>
                    <th>收费</th>
                    <th>顺序</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <!--eslint-disable-next-line-->
            <tr v-for="section in sections">
                    <td>{{section.title}}</td>
                    <td>{{course.name}}</td>
                    <td>{{chapter.name}}</td>
                    <td>{{section.video}}</td>
                    <td>{{section.time}}</td>
                    <td>{{showCharge(section.charge)}}</td>
                    <td>{{section.sort}}</td>
                    <td>{{section.gmtCreate}}</td>
                    <td>{{section.gmtModified}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-info" v-on:click="edit(section)">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-danger" v-on:click="del(section.id)">
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
                        <h5 class="modal-title">新增小节</h5>
                    </div>
                    <div class="modal-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标题</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="section.title" />
                                </div>
                            </div>

                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">视频</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="section.video" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">时长</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="section.time" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">收费</label>
                                <div class="col-sm-10">
                                   <select v-model="section.charge" class="form-control">
                                       <option v-for="ele in CHARGE" v-bind:value="ele.key">{{ele.value}}</option>
                                   </select>
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">顺序</label>
                                <div class="col-sm-10">
                                    <el-select  v-model="section.sort" >
                                        <el-option v-for="item in sortArr"
                                                   :value="item" :key="item">
                                        </el-option>
                                    </el-select>
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">创建时间</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="section.gmtCreate" />
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">修改时间</label>
                                <div class="col-sm-10">
                                    <input class="form-control" v-model="section.gmtModified" />
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
    import {showCharge} from "../../../public/static/js/common";

    export default {
        components: {Pagination},
        name: 'business-section',
        data: function () {
            return {
                sections: [],
                section: {},
                CHARGE:CHARGE,
                chapter:{},
                course:{},
                sortArr:[]
        }
        },
        mounted() {
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.course = SessionStorage.get("course");
            _this.chapter = SessionStorage.get("chapter");
            _this.list();
        },
        methods: {
            /**
             * 删除
             */
            del(id) {
                let _this = this;
                showConfirm("删除小节列表，操作不可恢复", function () {
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/section/delete/' + id).then((response) => {
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
            edit(section) {
                let _this = this;
                $(".modal").modal("show");
                _this.section = $.extend({}, section);
                console.log(_this.section)
            },
            /**
             * 新增
             */
            add() {
                let _this = this;
                _this.section = {}
                console.log('新增小节列表')
                $(".modal").modal("show");
            },
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/list', {
                    page: page,
                    pageSize: _this.$refs.pagination.size,
                    chapterId: _this.chapter.id,
                    courseId: _this.course.id
                }).then((response) => {
                        Loading.hide();
                        console.log("查询章列表结果:", response);
                        _this.sections = response.data.content.list;
                        _this.$refs.pagination.render(page, response.data.content.total);
                        _this.sortArr = [];
                        for(let i = 1; i <= response.data.content.total;i++) {
                            _this.sortArr.push(i);
                        }
                    }
                )
            },
            /**
             * 新增小节
             */
            save() {
                let _this = this;
                //保存校验 TO DO
                    if (!require(_this.section.title, '标题')){
                        return;
                    }
                    if (!length(_this.section.title, '标题',1,50)) {
                        return;
                    }
                    if (!length(_this.section.video, '视频',1,200)) {
                        return;
                    }
                _this.section.chapterId = _this.chapter.id;
                _this.section.courseId = _this.course.id;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/save', {
                    id: _this.section.id,
                    title: _this.section.title,
                    courseId: _this.section.courseId,
                    chapterId: _this.section.chapterId,
                    video: _this.section.video,
                    time: _this.section.time,
                    charge: _this.section.charge,
                    sort: _this.section.sort,
                    gmtCreate: _this.section.gmtCreate,
                    gmtModified: _this.section.gmtModified,
                }).then((response) => {
                        if (response.data.success) {
                            alertSuccess("保存成功");
                            $(".modal").modal("hide");
                            _this.list(1);
                        } else {
                            alertWarn(response.data.message);
                            $(".modal").modal("hide");
                            _this.list(1);
                        }
                    }
                );
            },
            showCharge(charge) {
               return showCharge(charge);
            }
        }
    }
</script>