<template>
  <el-dialog title="修改密码" :visible.sync="visible" :append-to-body="true">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="100px">
      <el-form-item label="账号">
        <span>{{ userName }}</span>
      </el-form-item>
      <el-form-item label="原密码" prop="password">
        <el-input type="password" v-model="dataForm.password" placeholder="原密码" onblur="this.check()"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input type="password" v-model="dataForm.newPassword" placeholder="新密码"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input type="password" v-model="dataForm.confirmPassword" placeholder="确认密码"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
            <el-button @click="visible = false">取消</el-button>
            <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
        </span>
  </el-dialog>
</template>

<script>
import qs from 'qs'
export default {
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.dataForm.newPassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    var checkPassWord=(rule, value, callback)=>{
      if(!this.check()){
        callback(new Error('密码不正确'));
      }else {
        callback();
      }
    };
    return {
      userName: '',
      visible: true,
      checkResult:false,
      dataForm: {
        password: '',
        newPassword: '',
        confirmPassword: ''
      },
      dataRule: {
        password: [{
          required: true,
          message: '原密码不能为空',
          trigger: 'blur'
        },
          {validator:checkPassWord,trigger: 'blur'}
        ],
        newPassword: [
            {required: true, message: '新密码不能为空', trigger: 'blur'},
          { validator: validatePass2, trigger: 'blur' }
        ],
        confirmPassword: [{
          required: true,
          message: '确认密码不能为空',
          trigger: 'blur'
        }]
      }
    }
  },
  created() {
    this.userName=this.$route.params && this.$route.params.userName
  },
  methods: {
    //原密码校验
   async check(){
       await this.$axios.post('system/api/user/check/'+this.dataForm.password).then(res=>{
        this.checkResult=res.data.t
        return res.data.t
      })
    },
    // 初始化
    init() {
      this.visible = true
      // 初始化清空表单内容
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
      })
    },
    // 表单提交，回到登录界面
    dataFormSubmit() {
     this.$axios.post('system/api/user/upPassword/'+this.dataForm.confirmPassword).then(res=>{
       if(res.data.t){
         this.$router.push('/login')
       }
     })
    }
  }
}
</script>