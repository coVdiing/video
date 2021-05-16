<template>
    <div>
        <el-tree
                :data="data"
                show-checkbox
                node-key="id"
                :props="defaultProps">
        </el-tree>
        <el-button v-on:click="creatEditor()">富文本编辑</el-button>
        <div class="modal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">课程内容</h5>
                    </div>
                    <div class="modal-body">


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
    import E from 'wangeditor';

    export default {
        data() {
            return {
                data: [
                    {
                        "id": "00000100",
                        "parent": "00000000",
                        "name": "前端技术",
                        "sort": 100,
                        "children": [
                            {
                                "id": "00000101",
                                "parent": "00000100",
                                "name": "html/css",
                                "sort": 101,
                                "children": []
                            },
                            {
                                "id": "00000102",
                                "parent": "00000100",
                                "name": "javascript",
                                "sort": 102,
                                "children": []
                            },
                            {
                                "id": "00000103",
                                "parent": "00000100",
                                "name": "vue.js",
                                "sort": 103,
                                "children": []
                            },
                            {
                                "id": "00000104",
                                "parent": "00000100",
                                "name": "react.js",
                                "sort": 104,
                                "children": []
                            },
                            {
                                "id": "00000105",
                                "parent": "00000100",
                                "name": "angular",
                                "sort": 105,
                                "children": []
                            },
                            {
                                "id": "00000106",
                                "parent": "00000100",
                                "name": "node.js",
                                "sort": 106,
                                "children": []
                            },
                            {
                                "id": "00000107",
                                "parent": "00000100",
                                "name": "jquery",
                                "sort": 107,
                                "children": []
                            },
                            {
                                "id": "00000108",
                                "parent": "00000100",
                                "name": "小程序",
                                "sort": 108,
                                "children": []
                            }
                        ]
                    },
                    {
                        "id": "00000200",
                        "parent": "00000000",
                        "name": "后端技术",
                        "sort": 200,
                        "children": [
                            {
                                "id": "00000201",
                                "parent": "00000200",
                                "name": "java",
                                "sort": 201,
                                "children": []
                            },
                            {
                                "id": "00000202",
                                "parent": "00000200",
                                "name": "springboot",
                                "sort": 202,
                                "children": []
                            },
                            {
                                "id": "00000203",
                                "parent": "00000200",
                                "name": "spring cloud",
                                "sort": 203,
                                "children": []
                            },
                            {
                                "id": "00000204",
                                "parent": "00000200",
                                "name": "ssm",
                                "sort": 204,
                                "children": []
                            },
                            {
                                "id": "00000205",
                                "parent": "00000200",
                                "name": "python",
                                "sort": 205,
                                "children": []
                            },
                            {
                                "id": "00000206",
                                "parent": "00000200",
                                "name": "爬虫",
                                "sort": 206,
                                "children": []
                            }
                        ]
                    },
                    {
                        "id": "00000300",
                        "parent": "00000000",
                        "name": "移动开发",
                        "sort": 300,
                        "children": [
                            {
                                "id": "00000301",
                                "parent": "00000300",
                                "name": "android",
                                "sort": 301,
                                "children": []
                            },
                            {
                                "id": "00000302",
                                "parent": "00000300",
                                "name": "ios",
                                "sort": 302,
                                "children": []
                            },
                            {
                                "id": "00000303",
                                "parent": "00000300",
                                "name": "react native",
                                "sort": 303,
                                "children": []
                            },
                            {
                                "id": "00000304",
                                "parent": "00000300",
                                "name": "ionic",
                                "sort": 304,
                                "children": []
                            }
                        ]
                    },
                    {
                        "id": "00000400",
                        "parent": "00000000",
                        "name": "前沿技术",
                        "sort": 400,
                        "children": [
                            {
                                "id": "00000401",
                                "parent": "00000400",
                                "name": "微服务",
                                "sort": 401,
                                "children": []
                            },
                            {
                                "id": "00000402",
                                "parent": "00000400",
                                "name": "区块链",
                                "sort": 402,
                                "children": []
                            },
                            {
                                "id": "00000403",
                                "parent": "00000400",
                                "name": "机器学习",
                                "sort": 403,
                                "children": []
                            },
                            {
                                "id": "00000404",
                                "parent": "00000400",
                                "name": "深度学习",
                                "sort": 404,
                                "children": []
                            },
                            {
                                "id": "00000405",
                                "parent": "00000400",
                                "name": "数据分析&挖掘",
                                "sort": 405,
                                "children": []
                            }
                        ]
                    },
                    {
                        "id": "00000500",
                        "parent": "00000000",
                        "name": "云计算&大数据",
                        "sort": 500,
                        "children": [
                            {
                                "id": "00000501",
                                "parent": "00000500",
                                "name": "大数据",
                                "sort": 501,
                                "children": []
                            },
                            {
                                "id": "00000502",
                                "parent": "00000500",
                                "name": "hadoop",
                                "sort": 502,
                                "children": []
                            },
                            {
                                "id": "00000503",
                                "parent": "00000500",
                                "name": "spark",
                                "sort": 503,
                                "children": []
                            },
                            {
                                "id": "00000504",
                                "parent": "00000500",
                                "name": "hbase",
                                "sort": 504,
                                "children": []
                            },
                            {
                                "id": "00000505",
                                "parent": "00000500",
                                "name": "阿里云",
                                "sort": 505,
                                "children": []
                            },
                            {
                                "id": "00000506",
                                "parent": "00000500",
                                "name": "docker",
                                "sort": 506,
                                "children": []
                            },
                            {
                                "id": "00000507",
                                "parent": "00000500",
                                "name": "kubernetes",
                                "sort": 507,
                                "children": []
                            }
                        ]
                    },
                    {
                        "id": "00000600",
                        "parent": "00000000",
                        "name": "运维&测试",
                        "sort": 600,
                        "children": [
                            {
                                "id": "00000601",
                                "parent": "00000600",
                                "name": "运维",
                                "sort": 601,
                                "children": []
                            },
                            {
                                "id": "00000602",
                                "parent": "00000600",
                                "name": "自动化运维",
                                "sort": 602,
                                "children": []
                            },
                            {
                                "id": "00000603",
                                "parent": "00000600",
                                "name": "中间件",
                                "sort": 603,
                                "children": []
                            },
                            {
                                "id": "00000604",
                                "parent": "00000600",
                                "name": "linux",
                                "sort": 604,
                                "children": []
                            },
                            {
                                "id": "00000605",
                                "parent": "00000600",
                                "name": "测试",
                                "sort": 605,
                                "children": []
                            },
                            {
                                "id": "00000606",
                                "parent": "00000600",
                                "name": "自动化测试",
                                "sort": 606,
                                "children": []
                            }
                        ]
                    },
                    {
                        "id": "00000700",
                        "parent": "00000000",
                        "name": "数据库",
                        "sort": 700,
                        "children": [
                            {
                                "id": "00000701",
                                "parent": "00000700",
                                "name": "mysql",
                                "sort": 701,
                                "children": []
                            },
                            {
                                "id": "00000702",
                                "parent": "00000700",
                                "name": "redis",
                                "sort": 702,
                                "children": []
                            },
                            {
                                "id": "00000703",
                                "parent": "00000700",
                                "name": "mongodb",
                                "sort": 703,
                                "children": []
                            }
                        ]
                    }
                ],
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                editor: {}
            };
        },
        mounted() {
            // 初始化富文本编辑器
            this.editor = new E('.modal-body');
            // 设置编辑区域高度为 500px
            this.editor.config.height = 400
            this.editor.create();
            this.editor.config.uploadImgShowBase64 = true;
            this.editor.config.showFullScreen = true
        },
        methods: {
            creatEditor() {
                $(".modal").modal("show");
            },
            save() {
                console.log("save:");
                let _this = this;
                let story = _this.editor.txt.html();
                $(".modal").modal("hide");
                console.log('story:' + story);
                // 清空内容
                _this.editor.txt.clear();
            }
        }
    };
</script>