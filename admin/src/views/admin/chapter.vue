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
                <th>ID</th>
                <th>名称</th>
                <th>课程</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <!--eslint-disable-next-line-->
            <tr v-for="chapter in chapters">
                <td>{{chapter.id}}</td>
                <td>{{chapter.name}}</td>
                <td>{{chapter.courseId}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-info" v-on:click="edit(chapter)">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-danger" v-on:click="del(chapter.id)">
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
        <!--        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>-->
        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

        <div class="modal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">新增章节</h5>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="chapter.name" placeholder="名称"/>
                            </div>
                        </div>
                        <br>
                        <br>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">课程ID</label>
                            <div class="col-sm-10">
                                <input class="form-control" v-model="chapter.courseId" placeholder="课程ID"/>
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

    export default {
        components: {Pagination},
        name: 'chapter',
        data: function () {
            return {
                chapters: [],
                chapter: {}
            }
        },
        mounted() {
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.$parent.activeSidebar("business-chapter-sidebar");
            _this.list();
        },
        methods: {
            /**
             * 删除
             */
            del(id) {
                let _this = this;
                _this.$ajax.delete('http://127.0.0.1:9000/business/admin/chapter/delete/' + id).then((response) => {
                        console.log("删除结果:", response);
                        _this.list(1);
                    }
                )
            },
            /**
             * 编辑
             */
            edit(chapter) {
                let _this = this;
                $(".modal").modal("show");
                _this.chapter = $.extend({}, chapter);
                console.log(_this.chapter)
            },
            /**
             * 新增
             */
            add() {
                let _this = this;
                _this.chapter = {}
                console.log('新增大章列表')
                $(".modal").modal("show");
            },
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/list', {
                    page: page,
                    pageSize: _this.$refs.pagination.size,
                }).then((response) => {
                        console.log("查询章列表结果:", response);
                        _this.chapters = response.data.content.list;
                        _this.$refs.pagination.render(page, response.data.content.total);
                    }
                )
            },
            /**
             * 新增章节
             */
            save() {
                console.log("新增章节")
                let _this = this;
                console.log("name:" + _this.chapter.name + ",id:" + _this.chapter.courseId);
                if (_this.chapter.name == undefined || _this.chapter.courseId == undefined) {
                    alert("不能为空!")
                    return;
                }
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/save', {
                    name: _this.chapter.name,
                    courseId: _this.chapter.courseId,
                    id: _this.chapter.id
                }).then((response) => {
                        console.log("保存:", response);
                        _this.list(1)
                        $(".modal").modal("hide");
                    }
                );
            }
        }
    }
</script>