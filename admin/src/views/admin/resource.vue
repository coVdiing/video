<template>
    <div>
        <el-row>
            <el-col :span="10" style="margin-right: 50px">
                <div class="grid-content bg-purple">
                    <p>
                        <button v-on:click="saveByJson()" class="btn btn-white btn-default btn-round">
                            <i class="ace-icon fa fa-edit green"></i>
                            保存资源
                        </button>
                        &nbsp;
                        <!-- PAGE CONTENT BEGINS -->
                        <button v-on:click="getResourceString()" class="btn btn-white btn-default btn-round">
                            <i class="ace-icon fa fa-times red2"></i>
                            获取资源JSON
                        </button>
                    </p>
                    <p>
                        <el-input
                                type="textarea"
                                autosize
                                placeholder="请输入内容"
                                v-model="resourceContent">
                        </el-input>
                    </p>
                </div>
            </el-col>
            <el-col :span="10">
                <div class="grid-content bg-purple-light">
                    <div>
                        <el-tree :data="resourceTree" :props="defaultProps"></el-tree>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>


</template>

<script>
    import {alertSuccess, alertWarn} from '../../../public/static/js/toast.js';

    export default {
        name: 'business-resource',
        data: function () {
            return {
                resources: [],
                resource: {},
                resourceJson: {},
                resourceContent: "",
                resourceTree: [],
                defaultProps: {
                    children: 'children',
                    label: 'name'
                }
            }
        },
        mounted() {
            let _this = this;
            _this.getResourceTree();
            debugger
        },
        methods: {
            /**
             * 获取资源树
             */
            getResourceTree() {
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/resource/resource-tree').then((response) => {
                    console.log("response:" + JSON.stringify(response));
                        if (response.data.success) {
                            _this.resourceTree = response.data.content;
                            console.log("tree:" + _this.resourceTree[0]);
                            _this.resourceJson = _this.resourceTree[0];
                        } else {
                            alertWarn(response.data.message);
                        }
                    }
                );
            },
            /**
             * 获取资源树对应的json字符串
             */
            getResourceString() {
                let _this = this;
                _this.resourceContent = JSON.stringify(_this.resourceJson);
            },
            /**
             * 通过json保存数据
             */
            saveByJson() {
                let _this = this;
                let resourceObj = JSON.parse(_this.resourceContent);
                console.log("saveByJson,arg:" + resourceObj);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/save-tree', {
                    id: resourceObj.id,
                    name: resourceObj.name,
                    page: resourceObj.page,
                    request: resourceObj.request,
                    parent: resourceObj.parent,
                    children: resourceObj.children
                }).then((response) => {
                        if (response.data.success) {
                            alertSuccess("保存成功");
                            _this.getResourceTree();
                        } else {
                            alertWarn(response.data.message);
                        }
                    }
                );
            }
        },
    }
</script>