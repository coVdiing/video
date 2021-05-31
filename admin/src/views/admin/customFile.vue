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