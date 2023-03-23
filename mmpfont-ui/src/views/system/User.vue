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
            style="width:240px"
        >
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
        <el-button type="primary" icon="el-icon-search" size="mini" @click="initUser" >搜索</el-button>
        <el-button type="primary" icon="el-icon-refresh" size="mini" >重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 表格工具按钮开始 -->
    <el-row :gutter="10" style="margin-bottom: 8px;">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" >删除</el-button>
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
      <el-table-column label="是否排班" align="center" prop="schedulingFlag" :formatter="(row)=>dictFormat(row,row.schedulingFlag,'his_subsection_type')" />
      <el-table-column label="级别" prop="userRank" align="center"  :formatter="(row)=>dictFormat(row,row.userRank,'sys_user_level')"/>
      <el-table-column label="背景" prop="background" align="center" :formatter="(row)=>dictFormat(row,row.background,'sys_user_background')"/>
      <el-table-column label="状态" prop="status" align="center" :formatter="(row)=>dictFormat(row,row.status,'sys_normal_disable')"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="200" />
      <el-table-column label="操作" align="center" width="250">
        <template slot-scope="scope">
          <el-button type="text" icon="el-icon-edit" size="mini">修改</el-button>
          <el-button  type="text" icon="el-icon-delete" size="mini" >删除</el-button>
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

  </div>
</template>

<script>
export default {
  name: "User",
  data(){
    return {
      //分配角色对话框的显示和隐藏
      dialogVisible:false,
      //所有角色
      roleTableList:[],
      //当前用户具有的角色
      userRoles:[],
      queryParams:{},
      //所有的部门信息
      deptInfo:[],
      //所有员工的信息
      tableData:[],
      //所有字典项
      dictList:[],
      //总条数
      total: 0,
      pageSize: 5,
      currentPage:1,
      multipleSelection:[],
      //分配角色的用户的id
      userId:0,
    }
  },
  created() {
    //加载所有部门
    this.initDept();
    //加载员工信息
    this.initUser();
    this.getDict();

  },
  methods:{
    //确认分配角色
    confirmRole(){
      //传递哪些参数？userId  roleIds
      let roleIds=[];
      this.multipleSelection.forEach(item=>{
        roleIds.push(item.roleId);
      })
      this.$axios.post("/system/api/role/confirmRole?roleIds="+roleIds+"&userId="+this.userId).then(result=>{
        this.dialogVisible=false;
        this.$message.success("分配角色成功")
      })
    },

    //分配角色弹出
    async fenRole(row){
      this.userId=row.userId;
      //打开弹出框
      this.dialogVisible=true;
      //查询所有角色
      //当前用户具有的角色
      await this.$axios.get("system/api/role/getByuserId/"+row.userId).then(result=>{
        this.roleTableList=result.data.t.roles;
        this.userRoles=result.data.t.userRoles;
      });
      //创建一个空数组用来存放默认数据
      let list = []
      //遍历表格的数据，再遍历需要在表格中反显的数据，两者的id一致
      this.roleTableList.forEach((item) => {
        this.userRoles.forEach(val => {
          if (val.roleId=== item.roleId) {
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
    toggleSelection(){

    },
    handleSelectionChange(v){
      this.multipleSelection=v;
    },
    //字典解析
    dictFormat(row,column,dictType){
      let r='';
      for (let i = 0; i < this.dictList.length; i++) {
        if (this.dictList[i].dictType===dictType){
          if (this.dictList[i].dictValue === column) {
            r= this.dictList[i].dictLabel;
            break;
          }
        }
      }
      return r;
    },
    //初始化字典
    getDict(){
      this.$axios.get('/system/api/dict/data/getall').then(res=> {
            this.dictList = res.data.t })
    },
    initDept(){
      this.$axios.get("/system/api/dept/list").then(result=>{
        this.deptInfo=result.data.t;
      })
    },
    initUser(){
      this.$axios.post("/system/api/user/searchUser/"+this.currentPage+"/"+this.pageSize,this.queryParams).then(result=>{
        this.tableData=result.data.t.records
        this.total=result.data.t.total
      })
    },
    //部门
    deptFormat(row){
      let v=row.deptId;
      let r=''
      for(let i=0;i<this.deptInfo.length;i++){
        if(this.deptInfo[i].deptId===v){
          r= this.deptInfo[i].deptName;
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
  }


}
</script>

<style scoped>

</style>
