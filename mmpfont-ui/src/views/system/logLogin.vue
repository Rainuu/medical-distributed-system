<template>
  <div class="app-container">
    <!-- 查询条件开始 -->
    <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
      <el-form-item label="用户名称" prop="userName">
        <el-input
            v-model="queryParams.userName"
            placeholder="请输入用户名称"
            clearable
            size="small"
            style="width:200px"
        />
      </el-form-item>
      <el-form-item label="用户账号" prop="loginAccount">
        <el-input
            v-model="queryParams.loginAccount"
            placeholder="请输入用户账号"
            clearable
            size="small"
            style="width:200px"
        />
      </el-form-item>
      <el-form-item label="IP地址" prop="ipAddr">
        <el-input
            v-model="queryParams.ipAddr"
            placeholder="请输入IP地址"
            clearable
            size="small"
            style="width:200px"
        />
      </el-form-item>
      <el-form-item label="登陆状态" prop="loginStatus">
        <el-select
            v-model="queryParams.loginStatus"
            placeholder="请选择登陆状态"
            clearable
            size="small"
            style="width:200px"
        >
          <el-option
              v-for="dict in dictList.filter((n)=>{return n.dictType==='sys_common_status'})"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="登陆类型" prop="loginType">
        <el-select
            v-model="queryParams.loginType"
            placeholder="请选择登陆类型"
            clearable
            size="small"
            style="width:200px"
        >
          <el-option
              v-for="dict in dictList.filter((n)=>{return n.dictType==='sys_user_type'})"
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
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button type="normal" icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 查询条件结束 -->
    <!-- 表头按钮开始 -->
    <el-row :gutter="10" style="margin-bottom: 8px;">
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="pilDelete">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" icon="el-icon-thumb" size="mini" @click="handleClearInfo">清空</el-button>
      </el-col>
    </el-row>
    <!-- 表头按钮结束 -->
    <!-- 数据表格开始 -->
    <el-table v-loading="loading" border :data="LoginInfoTableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日志ID" align="center" prop="infoId" />
      <el-table-column label="用户姓名" align="center" prop="userName" />
      <el-table-column label="登陆帐号" align="center" prop="loginAccount" />
      <el-table-column label="IP" width="180" align="center" prop="ipAddr" />
      <el-table-column label="登陆地址" align="center" prop="loginLocation" />
      <el-table-column label="浏览器" align="center" prop="browser" />
      <el-table-column label="操作系统" align="center" prop="os" />
      <el-table-column label="登陆状态" prop="loginStatus" align="center" :formatter="(row)=>dictFormat(row,row.status,'sys_common_status')"  />
      <el-table-column label="用户类型" prop="loginType" align="center" :formatter="(row)=>dictFormat(row,row.status,'sys_user_type')" />
      <el-table-column label="登陆时间" align="center" prop="loginTime" width="200" />
      <el-table-column label="操作" align="center" width="100">
        <template slot-scope="scope">
          <el-button v-if="scope.row.userId!=1" type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 数据表格结束 -->

    <!-- 分页控件开始 -->
    <el-pagination
        v-show="this.queryParams.total>0"
        :current-page="queryParams.current"
        :page-sizes="[5,10,20,30]"
        :page-size="queryParams.size"
        layout="total,sizes,prev,pager,next,jumper"
        :total="queryParams.total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
    <!-- 分页控件结束 -->
  </div>
</template>
<script>
import qs from 'qs'
export default {
  // 声明数据
  data() {
    return {
      // 遮罩层
      loading: false,
      // 选中多条
      multiple: true,
      // 选中数组
      ids: [],
      // 用户数据数据
      LoginInfoTableList: [],

      dictList:[],
      // 查询参数
      queryParams: {
        current:1,
        size:5,
        total:0,
        loginAccount: undefined,
        userName: undefined,
        ipAddr:undefined,
        loginStatus: undefined,
        loginType: undefined,
        // 时间
        dateRange: [],
      }
    }
  },
  // 初始化
  created() {
    this.getDict()
    // 做查询
    this.getloginInfoList()
  },
  // 自定义方法
  methods: {
    // 查询操作日志
    getloginInfoList() {
      this.loading = true
      this.$axios.post("system/api/loginInfo/getAll",qs.stringify(this.queryParams)).then(res => {
        this.LoginInfoTableList = res.data.t.records
        this.queryParams.total = res.data.t.total
        this.loading = false
      })
    },
    // 条件查询
    handleQuery() {
      this.getloginInfoList()
    },
    // 重置查询条件
    resetQuery() {
      this.resetForm('queryForm')
      this.getloginInfoList()
    },
    // 数据表格的多选择框选择时触发
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.infoId)
      this.multiple = !selection.length
    },
    // 分页pageSize变化时触发
    handleSizeChange(val) {
      this.queryParams.size = val
      // 重新查询
      this.getloginInfoList()
    },
    // 点击上一页  下一页，跳转到哪一页面时触发
    handleCurrentChange(val) {
      this.queryParams.current = val
      // 重新查询
      this.getloginInfoList()
    },
    // 删除
    pilDelete() {
      const operIds = this.ids
      this.$confirm('此操作将永久删除操作日志数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        for(var i=0;i<operIds.length;i++){
          this.$axios.delete('system/api/loginInfo/'+operIds[i]).then(res => {
          })
        }
        this.getloginInfoList()// 全查询
      }).catch(() => {
        this.$message.error('删除已取消')
        this.loading = false
      })
    },
    // 删除
    handleDelete(row) {
      const operIds = row.operId
      this.$confirm('此操作将永久删除操作日志数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loading = true
        this.$axios.delete('system/api/loginInfo/'+operIds).then(res => {
          this.loading = false
          this.$message.success('删除成功')
          this.getloginInfoList()// 全查询
        })
      }).catch(() => {
        this.$message.error('删除已取消')
        this.loading = false
      })
    },
    // 清空
    handleClearInfo() {
      this.$confirm('此操作将永久清空操作日志数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loading = true
        this.$axios.get('system/api/loginInfo/clean').then(res => {
          this.loading = false
          this.$message.success('清空成功')
          this.getloginInfoList()// 全查询
        })
      }).catch(() => {
        this.$message.error('清空已取消')
        this.loading = false
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
  }
}
</script>
<style scoped>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
