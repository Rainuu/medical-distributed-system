<template>
    <div class="revisePassword" style="border: skyblue 1px solid;border-radius:50px">
        <el-form ref="form" :model="form"  label-width="100px" :disabled="disabled">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="姓名" prop="userName">
                        <el-input v-model="form.userName" style="width:240px" placeholder="请输入用户名称" clearable size="small" />
                    </el-form-item></el-col>
                <el-col :span="12">
                    <el-form-item label="手机号" prop="phone">
                        <el-input v-model="form.phone"   style="width:240px" placeholder="请输入手机号，作为登陆凭证" clearable size="small" />
                        <span ref="checknulPhone" ></span>
                    </el-form-item></el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="所属科室" prop="deptId">
                        <el-input v-model="form.deptId" style="width:240px" placeholder="所属科室" clearable size="small" />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="级别" prop="userRank">
                        <el-input v-model="form.userRank" style="width:240px" placeholder="级别" clearable size="small" />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="年龄" prop="age">
                        <el-input v-model="form.age" style="width:240px" placeholder="背景" clearable size="small" />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="背景" prop="background">
                        <el-input v-model="form.background" style="width:240px" placeholder="背景" clearable size="small" />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="性别" prop="sex">
                        <el-input v-model="form.sex" style="width:240px" placeholder="性别" clearable size="small" />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="状态" prop="status">
                        <el-input v-model="form.status" style="width:240px" placeholder="性别" clearable size="small" />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="是否参与排班" prop="schedulingFlag">
                        <el-input v-model="form.schedulingFlag" style="width:240px" placeholder="排班" clearable size="small" />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="邮箱" prop="email">
                        <el-input v-model="form.email" style="width:240px" placeholder="请输入用户邮箱" clearable size="small" />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="22">
                    <el-form-item label="擅长" prop="strong">
                        <el-input v-model="form.strong" type="textarea" placeholder="请输入用户擅长" />
                    </el-form-item>
                </el-col>
                <el-col :span="22">
                    <el-form-item label="荣誉" prop="honor">
                        <el-input v-model="form.honor" type="textarea" placeholder="请输入用户相关荣誉" />
                    </el-form-item>
                </el-col>
                <el-col :span="22">
                    <el-form-item label="简介" prop="introduction" >
                        <el-input v-model="form.introduction" type="textarea" placeholder="请输入用户简介" />
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "PersonCenter",
        data(){
            return{
                disabled:true,
                form:{},
                dictList:[],
              deptOptions:[]
            }
        },
        created() {
             this.getDict();
             this.initDept();
          this.initUserInfo();
        },
        methods:{
            initUserInfo(){  //初始化用户信息
                this.$axios.get("/system/api/user/getInfo").then(result=>{
                    this.form = result.data.t;
                    this.UserFormatter()
                })
            },
          //数据匹配
          UserFormatter(){
            this.form.status=this.dictFormat(this.form.status,'sys_normal_disable')
            this.form.background=this.dictFormat(this.form.background,'sys_user_background')
            this.form.sex=this.dictFormat(this.form.sex,'sys_user_sex')
            this.form.userRank=this.dictFormat(this.form.userRank,'sys_user_level')
            this.form.schedulingFlag=this.dictFormat(this.form.schedulingFlag,'sys_yes_no')
          },
          initDept(){ //查询模糊条件中的部门信息
            this.$axios.get("/system/api/dept/list").then(result=>{
              this.deptOptions = result.data.t;
              let v = this.form.deptId;
              for (let i=0;i<this.deptOptions.length;i++){
                if (this.deptOptions[i].deptId===v){
                  this.form.deptId = this.deptOptions[i].deptName;
                  break;
                }
              }
              return "";
            })
          },
          //字典解析
          dictFormat( column, dictType){
            return this.formatDict( this.dictList,column, dictType)
          },
          //初始化字典
          getDict() {
            this.$axios.get('/system/api/dict/data/getall').then(res => {
              this.dictList = res.data.t
            })
          },
        }
    }
</script>

<style scoped>
    .revisePassword {
        max-width: 800px;
        margin: 0 auto;
        padding: 40px 20px;
    }
</style>
