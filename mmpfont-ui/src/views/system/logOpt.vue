<template>
  <div class="app-container">
    <!-- 查询条件开始 -->
    <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
      <el-form-item label="系统模块" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入系统模块"
          clearable
          size="small"
          style="width:200px"
        />
      </el-form-item>
      <el-form-item label="操作人员" prop="operName">
        <el-input
          v-model="queryParams.operName"
          placeholder="请输入操作人员"
          clearable
          size="small"
          style="width:200px"
        />
      </el-form-item>
      <el-form-item label="操作类型" prop="businessType">
        <el-select
          v-model="queryParams.businessType"
          placeholder="请选择类型"
          clearable
          size="small"
          style="width:200px"
        >
          <el-option
            v-for="dict in dictList.filter((n)=>{return n.dictType==='sys_oper_type'})"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择状态"
          clearable
          size="small"
          style="width:200px"
        >
          <el-option
            v-for="dict in dictList.filter((n)=>{return n.dictType==='sys_normal_disable'})"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="this.queryParams.dateRange"
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
    <el-table v-loading="loading" border :data="operLogTableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="操作模块">
              <span>{{ props.row.title }}</span>
            </el-form-item>
            <el-form-item label="登陆信息">
              <span>{{ props.row.operName }} // {{ props.row.operIp }} // {{ props.row.operLocation }}</span>
            </el-form-item>
            <el-form-item label="请求地址">
              <span>{{ props.row.operUrl }}</span>
            </el-form-item>
            <el-form-item label="操作方法">
              <span>{{ props.row.requestMethod }}</span>
            </el-form-item>
            <el-form-item label="请求参数">
              <span>{{ props.row.operParam }}</span>
            </el-form-item>
            <el-form-item label="返回参数">
              <span>{{ props.row.jsonResult }}</span>
            </el-form-item>
            <el-form-item label="操作状态">
              <span>{{ props.row.status==0?'成功':'失败' }}</span>
            </el-form-item>
            <el-form-item label="操作时间">
              <span>{{ props.row.operTime }}</span>
            </el-form-item>
            <el-form-item label="异常信息">
              <span>{{ props.row.errorMsg }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="日志ID" align="center" prop="operId" />
      <el-table-column label="系统模块" align="center" prop="title" />
      <el-table-column label="操作类型" align="center" prop="businessType" :formatter="(row)=>dictFormat(row,row.businessType,'sys_oper_type')" />
      <el-table-column label="请求方式" width="180" align="center" prop="requestMethod" />
      <el-table-column label="操作人员" align="center" prop="operName" />
      <el-table-column label="主机" align="center" prop="operIp" />
      <el-table-column label="操作地点" align="center" prop="operLocation" />
      <el-table-column label="操作状态" prop="status" align="center" :formatter="(row)=>dictFormat(row,row.status,'sys_common_status')" />
      <el-table-column label="操作时间" align="center" prop="operTime" width="200" />
      <el-table-column label="操作" align="center" width="100">
        <template slot-scope="scope">
          <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)">删除</el-button>
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
      operLogTableList: [],
      // 状态数据字典
      statusOptions: [],
      // 登陆类型数据字典
      businessTypeOptions: [],
      dictList:[],
      // 查询参数
      queryParams: {
        current:1,
        size:5,
        total:0,
        title: undefined,
        operName: undefined,
        status: undefined,
        businessType: undefined,
        // 时间
        dateRange: [],
      }
    }
  },
  // 初始化
  created() {
    this.getDict()
    // 做查询
    this.getOperLogList()
  },
  // 自定义方法
  methods: {
    // 查询操作日志
    getOperLogList() {
      this.loading = true
      this.$axios.post("system/api/operLog/getAll",qs.stringify(this.queryParams)).then(res => {
        this.operLogTableList = res.data.t.records
        this.queryParams.total = res.data.t.total
        this.loading = false
      })
    },
    // 条件查询
    handleQuery() {
      this.getOperLogList()
    },
    // 重置查询条件
    resetQuery() {
      this.resetForm('queryForm')
      this.getOperLogList()
    },
    // 数据表格的多选择框选择时触发
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.operId)
      this.multiple = !selection.length
    },
    // 分页pageSize变化时触发
    handleSizeChange(val) {
      this.queryParams.size = val
      // 重新查询
      this.getOperLogList()
    },
    // 点击上一页  下一页，跳转到哪一页面时触发
    handleCurrentChange(val) {
      this.queryParams.current = val
      // 重新查询
      this.getOperLogList()
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
          this.$axios.delete('system/api/operLog/'+operIds[i]).then(res => {
          })
        }
        this.getOperLogList()// 全查询
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
        this.$axios.delete('system/api/operLog/'+operIds).then(res => {
          this.loading = false
          this.$message.success('删除成功')
          this.getOperLogList()// 全查询
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
       this.$axios.get('system/api/operLog/clean').then(res => {
          this.loading = false
          this.$message.success('清空成功')
          this.getOperLogList()// 全查询
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
