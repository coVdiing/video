<template>
    <div>
        <div class="alert alert-warning" id="warn" role="alert" hidden="hidden">
            A simple warning alert—check it out!
        </div>
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                                    <th>id</th>
                    <th>相对路径</th>
                    <th>文件名</th>
                    <th>后缀</th>
                    <th>大小</th>
                    <th>用途</th>
                    <th>是否绑定数据</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <!--eslint-disable-next-line-->
            <tr v-for="customFile in customFiles">
                    <td>{{customFile.id}}</td>
                    <td>{{customFile.path}}</td>
                    <td>{{customFile.name}}</td>
                    <td>{{customFile.suffix}}</td>
                    <td>{{customFile.size}}</td>
                    <td>{{customFile.type}}</td>
                    <td>{{customFile.bind}}</td>
                    <td>{{customFile.gmtCreate}}</td>
                    <td>{{customFile.gmtModified}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-info" v-on:click="edit(customFile)">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-danger" v-on:click="del(customFile.id)">
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

    </div>


</template>

<script>
    import Pagination from '../../components/pagination';
    import {alertSuccess, alertWarn} from '../../../public/static/js/toast.js';
    import {showConfirm} from '../../../public/static/js/confirm.js';
    import {require, length} from "../../../public/static/js/validator";

    export default {
        components: {Pagination},
        name: 'business-customFile',
        data: function () {
            return {
                customFiles: [],
            customFile:
            {
            }
        }
        },
        mounted() {
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.$parent.activeSidebar("business-customFile-sidebar");
            _this.list();
        },
        methods: {
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/custom-file/list', {
                    page: page,
                    pageSize: _this.$refs.pagination.size,
                }).then((response) => {
                        Loading.hide();
                        _this.customFiles = response.data.content.list;
                        _this.$refs.pagination.render(page, response.data.content.total);
                    }
                )
            }
        }
    }
</script>