<template>
    <div class="main-container">
        <div class="main-content">
            <div class="row">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="login-container">
                        <div class="center">
                            <h1>
                                <span class="red">MengB</span>
                                <span class="white" id="id-text2">TV</span>
                                <i class="ace-icon fa fa-leaf white"></i>
                            </h1>
                            <!--                            <h4 class="blue" id="id-company-text">vi</h4>-->
                        </div>

                        <div class="space-6"></div>

                        <div class="position-relative">
                            <div id="login-box" class="login-box visible widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header blue lighter bigger">
                                            <i class="ace-icon fa fa-coffee"></i>
                                            请输入用户名密码
                                        </h4>

                                        <div class="space-6"></div>

                                        <form>
                                            <fieldset>
                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control"
                                                                   v-model="user.loginName" placeholder="用户名"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                                                </label>

                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control"
                                                                   v-model="user.password"
                                                                   placeholder="密码"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                                </label>

                                                <div class="space"></div>

                                                <div class="clearfix">
                                                    <label class="inline">
                                                        <input type="checkbox" class="ace"/>
                                                        <span class="lbl">记住我</span>
                                                    </label>

                                                    <button type="button"
                                                            class="width-35 pull-right btn btn-sm btn-primary"
                                                            @click="login()">
                                                        <i class="ace-icon fa fa-key"></i>
                                                        <span class="bigger-110">登录</span>
                                                    </button>
                                                </div>

                                                <div class="space-4"></div>
                                            </fieldset>
                                        </form>


                                        <div class="space-6"></div>


                                    </div><!-- /.widget-main -->


                                </div><!-- /.widget-body -->
                            </div><!-- /.login-box -->

                        </div><!-- /.position-relative -->


                    </div>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.main-content -->
    </div><!-- /.main-container -->
</template>
<script>
    import {length, require} from "../../public/static/js/validator";
    import {alertWarn} from "../../public/static/js/toast";

    export default {
        name: 'app',
        mounted() {
            $('body').removeClass('no-skin');
            $('body').attr('class', 'login-layout light-login');
        },
        data: function () {
            return {
                user: {},
                loginUser:{}
            }
        },
        methods: {
            login() {
                let _this = this;
                // 登录之前先校验信息是否为空
                if (!require(_this.user.loginName, '登录名')) {
                    return;
                }
                if (!length(_this.user.loginName, '登录名', 1, 50)) {
                    return;
                }
                if (!require(_this.user.password, '密码')) {
                    return;
                }
                _this.user.password = hex_md5(_this.user.password + KEY);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/login', {
                    loginName: _this.user.loginName,
                    password: _this.user.password
                }).then((resp) => {
                    debugger;
                    if (resp.data.success) {
                        _this.loginUser = resp.data.content;
                        Tool.setLoginUser(_this.loginUser);
                        _this.$router.push("/welcome");
                    } else {
                        alertWarn(resp.data.message);
                    }
                });
            }
        }
    }
</script>

