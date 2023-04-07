<template>
  <div>
    <!-- 查询条件开始 -->
    <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
      <el-form-item label="所属部门" prop="deptId">
        <el-select
            v-model="queryParams.deptId"
            placeholder="请选择部门"
            clearable
            size="small"
            style="width:240px"
        >
          <el-option
              v-for="dict in deptInfo"
              :key="dict.deptId"
              :label="dict.deptName"
              :value="dict.deptId"
          />
        </el-select></el-form-item>
      <el-form-item label="用户姓名" prop="userName">
        <el-input
            v-model="queryParams.userName"
            placeholder="请输入用户姓名"
            clearable
            size="small"
            style="width:240px"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
            v-model="queryParams.phone"
            placeholder="请输入手机号"
            clearable
            size="small"
            style="width:240px"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
            v-model="queryParams.status"
            placeholder="可用状态"
            clearable
            size="small"
            style="width:240px">
          <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
            v-model="queryParams.dateRange"
            size="small"
            style="width:240px"
            value-format="yyyy-MM-dd"
            type="daterange"
            range-separator="-"
            start-placeholde="开始日期"
            end-placeholde="结束日期"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="mohu" >搜索</el-button>
        <el-button type="primary" icon="el-icon-refresh" size="mini" @click="chongzi">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 表格工具按钮开始 -->
    <el-row :gutter="10" style="margin-bottom: 8px;">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="adduserVisible=true,judge=true">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="piliangdel">批量删除</el-button>
      </el-col>
    </el-row>
    <!-- 表格工具按钮结束 -->
    <!-- 数据表格开始 -->
    <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="用户姓名" align="center" prop="userName" />
      <el-table-column label="部门" align="center" prop="deptId" :formatter="deptFormat"/>
      <el-table-column label="手机号码【登陆身份】" width="180" align="center" prop="phone" />
      <el-table-column label="性别" align="center" prop="sex" :formatter="(row)=>dictFormat(row,row.sex,'sys_user_sex')" />
      <el-table-column label="年龄" align="center" prop="age" />
      <el-table-column label="是否排班" align="center" prop="schedulingFlag" :formatter="(row)=>dictFormat(row,row.schedulingFlag,'his_system_yesno')" />
      <el-table-column label="级别" prop="userRank" align="center"  :formatter="(row)=>dictFormat(row,row.userRank,'sys_user_level')"/>
      <el-table-column label="背景" prop="background" align="center" :formatter="(row)=>dictFormat(row,row.background,'sys_user_background')"/>
      <el-table-column label="状态" prop="status" align="center" :formatter="(row)=>dictFormat(row,row.status,'sys_normal_disable')"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="200" />
      <el-table-column label="操作" align="center" width="250">
        <template slot-scope="scope">
          <el-button type="text" icon="el-icon-edit" size="mini" @click="update(scope.row)">修改</el-button>
          <el-button  type="text" icon="el-icon-delete" size="mini" @click="deleuser(scope.row.userId)" >删除</el-button>
          <el-button  type="text" icon="el-icon-thumb" size="mini" @click="fenRole(scope.row)">分配角色</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 数据表格结束 -->
    <!-- 分页控件开始 -->
    <el-pagination
        v-show="total>0"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20, 30]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
    <!-- 分页控件结束 -->
    <!--分配角色的对话框-->

    <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="40%"

    >
      <el-table
          ref="roleListTable"
          border
          :data="roleTableList"
          @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="角色ID" align="center" prop="roleId" />
        <el-table-column label="角色名称" align="center" prop="roleName" />
        <el-table-column label="权限编码" align="center" prop="roleCode" />
        <el-table-column label="备注" align="center" prop="remark" />
        <el-table-column label="创建时间" align="center" prop="createTime" />
      </el-table>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="confirmRole">确 定</el-button>
          </span>
    </el-dialog>
    <el-dialog
        title="提示"
        :visible.sync="adduserVisible"
        width="50%"
        :before-close="handleClose">
      <el-form :model="userForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

        <el-row>
          <el-col :span="12">
            <el-form-item label="姓名" prop="userName">
              <el-input v-model="userForm.userName" style="width:240px" placeholder="请输入用户名称" clearable size="small" />
            </el-form-item></el-col>
          <el-col :span="12">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="userForm.phone" style="width:240px" placeholder="请输入手机号，作为登陆凭证" clearable size="small" />
            </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="所属科室" prop="deptId">
              <el-select
                  v-model="userForm.deptId"
                  placeholder="所属科室"
                  clearable
                  size="small"
                  style="width:240px"
              >
                <el-option
                    v-for="d in deptInfo"
                    :key="d.deptId"
                    :label="d.deptName"
                    :value="d.deptId"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="级别" prop="userRank">
              <el-select
                  v-model="userForm.userRank"
                  placeholder="用户级别"
                  clearable
                  size="small"
                  style="width:240px"
              >
                <el-option
                    v-for="d in dictList.filter((n)=>{return n.dictType==='sys_user_level'})"
                    :key="d.dictValue"
                    :label="d.dictLabel"
                    :value="d.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="年龄" prop="age">
              <el-input-number v-model="userForm.age" clearable size="small" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="背景" prop="background">
              <el-select
                  v-model="userForm.background"
                  placeholder="学历"
                  clearable
                  size="small"
                  style="width:240px"
              >
                <el-option
                    v-for="d in dictList.filter((n)=>{return n.dictType==='sys_user_background'})"
                    :key="d.dictValue"
                    :label="d.dictLabel"
                    :value="d.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="性别" prop="sex">
              <el-radio-group v-model="userForm.sex">
                <el-radio
                    v-for="d in  dictList.filter((n)=>{return n.dictType==='sys_user_sex'})"
                    :key="d.dictValue"
                    :label="d.dictValue"
                    :value="d.dictValue"
                >{{ d.dictLabel }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="userForm.status">
                <el-radio
                    v-for="dict in  dictList.filter((n)=>{return n.dictType==='sys_normal_disable'})"
                    :key="dict.dictValue"
                    :label="dict.dictValue"
                    :value="dict.dictValue"
                >{{ dict.dictLabel }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="是否参与排班" prop="schedulingFlag">
                <el-radio v-model="userForm.schedulingFlag" label="0">是</el-radio>
                <el-radio v-model="userForm.schedulingFlag" label="1">否</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="userForm.email" style="width:240px" placeholder="请输入用户邮箱" clearable size="small" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="擅长" prop="strong">
              <el-input v-model="userForm.strong" type="textarea" placeholder="请输入用户擅长" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="荣誉" prop="honor">
              <el-input v-model="userForm.honor" type="textarea" placeholder="请输入用户相关荣誉" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="简介" prop="introduction">
              <el-input v-model="userForm.introduction" type="textarea" placeholder="请输入用户简介" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="上传头像" prop="file">
          <el-upload
              class="avatar-uploader"
              action="#"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :on-change="fileSubmit"
              :before-upload="beforeAvatarUpload">
            <img v-if="this.userForm.picture" :src="this.userForm.picture" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-form-item>
          <el-button @click="adduserVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
  </span>
    </el-dialog>
  </div>
</template>

<script>
import qs from 'qs'
export default {
  name: "User",
  data() {
    /**
     * 校验手机号
     */
    const validatePhone = (rule, value, callback) => {
      const regExp = /^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$/
      if (!regExp.test(value)) {
        callback(new Error('请输入正确的手机号'))
      } else {
        callback()
      }
    }
    return {
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      //添加用户对话框显示和隐藏
      adduserVisible: false,
      //分配角色对话框的显示和隐藏
      dialogVisible: false,
      //所有角色
      roleTableList: [],
      //当前用户具有的角色
      userRoles: [],
      queryParams: {},
      //所有的部门信息
      deptInfo: [],
      //所有员工的信息
      tableData: [],
      //所有字典项
      dictList: [],
      //总条数
      total: 0,
      pageSize: 5,
      currentPage: 1,
     //控制列表显示
      judge:true,
      //用户表单
      userForm: {
        picture:'',
        deptId: '',
        userName: '',
        password:'',
        userType: '',
        sex: '',
        age: '',
        background: '',
        phone: '',
        email: '',
        strong: '',
        honor: '',
        introduction: '',
        userRank: '',
        status: '',
        schedulingFlag: '',
      },
      statusOptions:[],
      multipleSelection: [],
      //分配角色的用户的id
      userId: 0,
      //校验规则
      rules: {
        userName: [
          {required: true, message: '请输入活动名称', trigger: 'blur'},
          {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        phone:[
          {
            required: true,
            message: '请填写手机号码',
            trigger: 'blur',
          }, {
            validator: validatePhone,
            trigger: 'change',
          }
        ]

      }
    }

  },
  created() {
    //加载所有部门
    this.initDept();
    //加载员工信息
    this.initUser();
    this.statusDict();
    this.getDict();

  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/system/api/user/saveandsupdate",qs.stringify(this.userForm)).then(res=>{
            if (res.data.t){
              this.$message.success("添加成功")
              this.adduserVisible=false;
              this.userForm={};
              this.initUser()
            }else {
              this.$message.error("添加失败")
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    chongzi(){
      this.queryParams={}
    },
    //确认分配角色
    confirmRole() {
      //传递哪些参数？userId  roleIds
      let roleIds = [];
      this.multipleSelection.forEach(item => {
        roleIds.push(item.roleId);
      })
      this.$axios.post("/system/api/role/confirmRole?roleIds=" + roleIds + "&userId=" + this.userId).then(result => {
        this.dialogVisible = false;
        this.$message.success("分配角色成功")
      })
    },
    update(row){
      this.judge=false
      this.adduserVisible=true
      this.userForm=JSON.parse(JSON.stringify(row))
    },
    //删除角色
    deleuser(id){
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        this.$axios.delete('/system/api/user/'+id).then(res=>{
          if (res.data.t){
            this.initUser()
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
          }else {
            this.$message({
              type: 'error',
              message: '删除失败!'
            });
          }
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    //模糊查询
    mohu(){
      this.currentPage=1
      this.initUser()
    },
    //分配角色弹出
    async fenRole(row) {
      this.userId = row.userId;
      //打开弹出框
      this.dialogVisible = true;
      //查询所有角色
      //当前用户具有的角色
      await this.$axios.get("system/api/role/getByuserId/" + row.userId).then(result => {
        this.roleTableList = result.data.t.roles;
        this.userRoles = result.data.t.userRoles;
      });
      //创建一个空数组用来存放默认数据
      let list = []
      //遍历表格的数据，再遍历需要在表格中反显的数据，两者的id一致
      this.roleTableList.forEach((item) => {
        this.userRoles.forEach(val => {
          if (val.roleId === item.roleId) {
            // 把判断出来的默认表格数据push到创建的数组中
            list.push(item)
          }
        })
      })
      console.log(list)
      //再遍历数组，将数据放入方法中
      list.forEach((row) => {
        this.$refs.roleListTable.toggleRowSelection(row, true)
      })

    },
    toggleSelection() {

    },
    handleSelectionChange(v) {
      this.multipleSelection = v;
      this.multiple=!v.length
    },
    piliangdel(){
      for(let i=0;i<this.multipleSelection.length;i++){
        this.$axios.delete('/system/api/user/'+this.multipleSelection[i].userId)
      }
      this.mohu()
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            this.userForm={}
            done();
          })
          .catch(_ => {
          });
    },
  statusDict(){
    this.$axios.get("/system/api/dict/data/findByType/sys_normal_disable").then(result=>{
      this.statusOptions=result.data.t;
    })
  },
  //字典解析
    dictFormat(row, column, dictType){
      return this.formatDict( this.dictList,column, dictType)
    },

  //初始化字典
  getDict() {
    this.$axios.get('/system/api/dict/data/getall').then(res => {
      this.dictList = res.data.t
    })
  },
  //获取全部部门
  initDept() {
    this.$axios.get("/system/api/dept/list").then(result => {
      this.deptInfo = result.data.t;
    })
  },
  initUser() {
    this.$axios.post("/system/api/user/searchUser/" + this.currentPage + "/" + this.pageSize, this.queryParams).then(result => {
      this.tableData = result.data.t.records
      this.total = result.data.t.total
    })
  },
  //部门
  deptFormat(row) {
    let v = row.deptId;
    let r = ''
    for (let i = 0; i < this.deptInfo.length; i++) {
      if (this.deptInfo[i].deptId === v) {
        r = this.deptInfo[i].deptName;
      }
    }
    return r;
  },
  // 分页pageSize变化时触发
  handleSizeChange(val) {
    this.pageSize = val
    // 重新查询
    this.initUser()
  },
  // 点击上一页  下一页，跳转到哪一页面时触发
  handleCurrentChange(val) {
    this.currentPage = val
    // 重新查询
    this.initUser()
  },
    fileSubmit(file){
      this.files = file.raw;
              // FormData 对象
        var formData = new FormData();
           // 文件对象
        formData.append('file', this.files);
          let config = {
               headers: {
                'Content-Type': 'multipart/form-data'
            },
          methods: 'post'
        }
        this.$axios.post('system/api/oss/upload',formData,config).then(res=>{
          if (res.data.t===undefined){
            this.$message.error("上传失败")
          }else {
            this.userForm.picture=res.data.t
          }
        })
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    }
  }
  }

</script>

<style scoped>

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
