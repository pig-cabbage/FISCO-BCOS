<template>
    <div class="login">
        <div class="slideShadow" v-show="showSlide">
            <transition>
                <div class="slideSty animated bounce">
                    <slide-verify
                            @success="onSuccess"
                            @fail="onFail"
                            :sliderText="text"
                            :w="350"
                            :h="175"
                            ref="slideDiv"
                    ></slide-verify>
                    <div class="iconBtn">
                        <i class="el-icon-circle-close" @click="showSlide = false"></i
                        ><i class="el-icon-refresh" @click="refresh"></i>
                    </div>
                </div>
            </transition>
        </div>
        <div class="loginBox">
            <h2 class="loginH2"><strong>FISCO-BCOS</strong> 积分链商城系统</h2>
            <div class="loginCon">
                <div class="titleDiv">
                    <h3>Sign up now</h3>
                    <p>Enter your username and password to log on:</p>
                    <i class="el-icon-key"></i>
                </div>
                <el-form ref="loginForm" :rules="rules" :model="ruleForm">
                    <el-form-item prop="user">
                        <el-input
                                placeholder="请输入账号"
                                prefix-icon="el-icon-user"
                                v-model="ruleForm.user"
                        ></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input
                                placeholder="请输入密码"
                                prefix-icon="el-icon-lock"
                                v-model="ruleForm.password"
                                show-password
                        ></el-input>
                    </el-form-item>
                    <el-form-item prop="status">
                        <el-select v-model="ruleForm.status" placeholde="请选择身份">
                            <el-option
                                    v-for="item in options"
                                    :label="item.label"
                                    :value="item.value"
                                    :key="item.value"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                    <el-button
                            type="primary"
                            class="loginBtn"
                            @click="loginZz('loginForm')"
                    >注册</el-button
                    >
                    <el-button
                            type="primary"
                            class="loginBtn1"
                            @click="loginYz('loginForm')"
                    >登录</el-button
                    >
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    import SlideVerify from '@/components/SlideVerify'
    export default {
        data() {
            return {
                notifyObj: null,
                text: '向右滑动',
                showSlide: false,
                ruleForm: {
                    user: '',
                    password: '',
                    status: ''
                },
                rules: {
                    user: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 3, max: 15, message: '长度在3到5个字符', trigger: 'blur' }
                    ],
                    password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
                },
                options: [{
                    value: '0',
                    label: '银行'
                }, {
                    value: '1',
                    label: '企业'
                }, {
                    value: '2',
                    label: '个人'
                }]
            }
        },
        mounted() {
            this.shopTip()
        },
        methods: {
            onSuccess() {
                this.showSlide = false
                this._login()
            },
            onFail() {
                this.$message.error('验证失败')
            },
            refresh() {
                this.$refs.slideDiv.reset()
            },
            loginYz(form) {
                this.$refs[form].validate(valid => {
                    if (valid) {
                        this.showSlide = true
                    } else {
                        return
                    }
                })
            },
            loginZz(form) {
                this.$refs[form].validate(valid => {
                    if (valid) {
                        this._register()
                    } else {
                        return
                    }
                })
            },
            _login() {
                //let rule=[this.ruleForm.user,this.ruleForm.password]
                this.$store.commit('user/SET_STATUS', this.ruleForm.status)
                if (this.ruleForm.status === '0') {
                    let rule={
                        account: this.ruleForm.user,
                        passWord: this.ruleForm.password
                    }
                    this.$store
                        .dispatch('user/_login', rule)
                        .then(res => {
                            if (!res) {
                                this.refresh()
                            } else {
                                this.$router.push(this.$route.query.redirect)
                                if (this.notifyObj) {
                                    this.notifyObj.close()
                                }
                                this.notifyObj = null
                            }
                        })
                        .catch(error => {
                            this.refresh()
                            this.$message.error(error)
                        })
                } else if (this.ruleForm.status==='1'){
                    let rule_business={
                        businessId:this.ruleForm.user,
                        passWord:this.ruleForm.password
                    }
                    this.$store
                        .dispatch('user/_login_business', rule_business)
                        .then(res => {
                            if (!res) {
                                this.refresh()
                            } else {
                                this.$router.push(this.$route.query.redirect)
                                if (this.notifyObj) {
                                    this.notifyObj.close()
                                }
                                this.notifyObj = null
                            }
                        })
                        .catch(error => {
                            this.refresh()
                            this.$message.error(error)
                        })
                } else{
                    let rule_client={
                        account:this.ruleForm.user,
                        password:this.ruleForm.password
                    }
                    this.$store
                        .dispatch('user/_login_client', rule_client)
                        .then(res => {
                            if (!res) {
                                this.refresh()
                            } else {
                                this.$router.push(this.$route.query.redirect)
                                if (this.notifyObj) {
                                    this.notifyObj.close()
                                }
                                this.notifyObj = null
                            }
                        })
                        .catch(error => {
                            this.refresh()
                            this.$message.error(error)
                        })
                }
            },
            _register() {
                //let rule=[this.ruleForm.user,this.ruleForm.password]
                this.$store.commit('user/SET_STATUS', this.ruleForm.status)
                if (this.ruleForm.status === '0') {
                    let rule={
                        account: this.ruleForm.user,
                        passWord: this.ruleForm.password
                    }
                    this.$store
                        .dispatch('user/_register', rule)
                        .then(res => {
                            if (!res) {
                                this.refresh()
                            } else {
                                this.$router.push(this.$route.query.redirect)
                                if (this.notifyObj) {
                                    this.notifyObj.close()
                                }
                                this.notifyObj = null
                            }
                        })
                        .catch(error => {
                            this.refresh()
                            this.$message.error(error)
                        })
                } else if (this.ruleForm.status==='1'){
                    let rule_business={
                        businessId:this.ruleForm.user,
                        passWord:this.ruleForm.password
                    }
                    this.$store
                        .dispatch('user/_register_business', rule_business)
                        .then(res => {
                            if (!res) {
                                this.refresh()
                            } else {
                                this.$router.push(this.$route.query.redirect)
                                if (this.notifyObj) {
                                    this.notifyObj.close()
                                }
                                this.notifyObj = null
                            }
                        })
                        .catch(error => {
                            this.refresh()
                            this.$message.error(error)
                        })
                } else{
                    let rule_client={
                        userAccount:this.ruleForm.user,
                        password:this.ruleForm.password
                    }
                    this.$store
                        .dispatch('user/_register_client', rule_client)
                        .then(res => {
                            if (!res) {
                                this.refresh()
                            } else {
                                this.$router.push(this.$route.query.redirect)
                                if (this.notifyObj) {
                                    this.notifyObj.close()
                                }
                                this.notifyObj = null
                            }
                        })
                        .catch(error => {
                            this.refresh()
                            this.$message.error(error)
                        })
                }
            },
            shopTip() {
                this.notifyObj = this.$notify({
                    title: '提示',
                    message:
                        '目前有三个登陆角色，银行、企业和普通客户，请根据自身身份权限注册登录',
                    duration: 0,
                    iconClass: 'el-icon-s-opportunity'
                })
            }
        },
        components: {
            SlideVerify
        }
    }
</script>
<style scoped lang="scss">
    .login {
        height: 100%;
        width: 100%;
        background: url(../../assets/pageBg/loginBg.jpg) no-repeat center center;
        background-size: 100% 100%;
        overflow: hidden;
    }
    .loginBox {
        height: 455px;
        width: 550px;
        margin: 0 auto;
        position: relative;
        top: 50%;
        margin-top: -287px;
    }
    .loginH2 {
        font-size: 38px;
        color: #fff;
        text-align: center;
    }
    .loginCon {
        margin-top: 30px;
        background: #eee;
        border-radius: 4px;
        .titleDiv {
            padding: 0 28px;
            background: #fff;
            position: relative;
            height: 120px;
            border-radius: 4px 4px 0 0;
            h3 {
                font-size: 22px;
                color: #555;
                font-weight: initial;
                padding-top: 23px;
            }
            p {
                font-size: 16px;
                color: #888;
                padding-top: 12px;
            }
            i {
                font-size: 65px;
                color: #ddd;
                position: absolute;
                right: 27px;
                top: 29px;
            }
        }
        .el-form {
            padding: 25px 25px 30px 25px;
            background: #eee;
            border-radius: 0 0 4px 4px;
        }
    }
    .loginBtn {
        width: 15%;
        background: #19b9e7;
        margin-left: 316px;
        float: left;
        margin-top: -67px;
        position: absolute;
        z-index: 900;
    }
    .loginBtn1 {
        width: 15%;
        background: #19b9e7;
        margin-left: 416px;
        float: left;
        margin-top: -67px;
        position: absolute;
        z-index: 900;
    }
    .slideShadow {
        position: fixed;
        z-index: 999;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.6);
    }
    .slideSty {
        position: absolute;
        width: 380px;
        height: 311px;
        background: #e8e8e8;
        border: 1px solid #dcdcdc;
        left: 50%;
        top: 50%;
        margin-left: -188px;
        margin-top: -176px;
        z-index: 99;
        border-radius: 5px;
    }
    .iconBtn {
        padding: 9px 0 0 19px;
        color: #5f5f5f;
        border-top: 1px solid #d8d8d8;
        margin-top: 17px;
        i {
            font-size: 22px;
            cursor: pointer;
        }
        i:last-child {
            margin-left: 7px;
        }
    }
</style>
<style>
    .slideSty .slide-verify {
        margin: 13px auto 0 auto;
        width: 350px !important;
    }
    .slideSty .slide-verify-slider {
        width: 100% !important;
    }
    .slideSty .slide-verify-refresh-icon {
        display: none;
    }
</style>
