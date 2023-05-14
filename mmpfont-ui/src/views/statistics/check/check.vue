<template>
  <div class="app-container">
    <div style="color:red;text-align:center">*注意默认只查询当天的统计数据，如果要查询其它的，请选择范围查询</div>
    <br>
    <el-tabs v-model="activeName" :stretch="true" @tab-click="handleClick">
      <el-tab-pane label="检查项目列表" name="check">
        <!-- 查询条件开始 -->
        <el-form ref="queryCheckFrom" :model="queryCheckParams" :inline="true" label-width="68px">
          <el-form-item label="项目名称" prop="checkItemId">
            <el-select
              v-model="queryCheckParams.checkItemId"
              placeholder="请选择项目名称"
              clearable
              size="small"
              style="width:180px"
            >
              <el-option
                v-for="d in checkItemOptions"
                :key="d.checkItemId"
                :label="d.checkItemName"
                :value="d.checkItemId"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="患者名称" prop="patientName">
            <el-input
              v-model="queryCheckParams.patientName"
              placeholder="请输入患者名称"
              clearable
              size="small"
              style="width:180px"
            />
          </el-form-item>
          <el-form-item label="交易时间">
            <el-date-picker
              v-model="dateRange"
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
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleCheckQuery">搜索</el-button>
            <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetCheckQuery">重置</el-button>
          </el-form-item>
        </el-form>
        <!-- 查询条件结束 -->
        <!-- 数据表格开始 -->
        <el-table v-loading="loading" border :data="checkList" show-summary>
          <el-table-column label="检查项目ID" prop="checkItemId" align="center" />
          <el-table-column label="检查项目名称" prop="checkItemName" align="center" />
          <el-table-column label="项目价格" prop="price" align="center" />
          <el-table-column label="患者姓名" prop="patientName" align="center" />
          <el-table-column label="检查状态" prop="resultStatus" align="center" :formatter="statusFormat" />
          <el-table-column label="创建时间" prop="createTime" align="center" />
          <el-table-column label="创建人" prop="createBy" align="center" />
        </el-table>
        <!-- 数据结束结束 -->

      </el-tab-pane>
      <el-tab-pane label="检查项目统计列表" name="checkStat">
        <!-- 查询条件开始 -->
        <el-form ref="queryCheckStatFrom" :model="queryCheckStatParams" :inline="true" label-width="68px">
          <el-form-item label="项目名称" prop="checkItemId">
            <el-select
              v-model="queryCheckStatParams.checkItemId"
              placeholder="请选择项目名称"
              clearable
              size="small"
              style="width:180px"
            >
              <el-option
                v-for="d in checkItemOptions"
                :key="d.checkItemId"
                :label="d.checkItemName"
                :value="d.checkItemId"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="交易时间">
            <el-date-picker
              v-model="dateRange"
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
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleCheckStatQuery">搜索</el-button>
            <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetCheckStatQuery">重置</el-button>
          </el-form-item>
        </el-form>
        <!-- 查询条件结束 -->
        <!-- 数据表格开始 -->
        <el-table v-loading="loading" border :data="checkStatList" show-summary>
          <el-table-column label="检查项目ID" prop="checkItemId" align="center" />
          <el-table-column label="检查项目名称" prop="checkItemName" align="center" />
          <el-table-column label="总金额" prop="sum" align="center" />
          <el-table-column label="检查次数" prop="count" align="center" />
        </el-table>
        <!-- 数据结束结束 -->
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import qs from "qs";
export default {
  data() {
    return {
      // 遮罩
      loading: false,
      // 被激活的tab名称
      activeName: 'check',
      // 检查项目总销售数据
      checkList: [],
      // 检查项目的销售数量数据
      checkStatList: [],
      // 日期范围
      dateRange: [],
      // 列表查询参数
      queryCheckParams: {
        patientName: undefined,
        checkItemId: undefined
      },
      // 数量统计查询参数
      queryCheckStatParams: {
        checkItemId: undefined
      },
      // 所有的检查项目
      checkItemOptions: [],
      // 检查状态字典数据
      resultStatusOptions: []
    }
  },
  created() {
    // 加载状态的字典数据
    this.initStatus()
    // 加载所有可用的检查项目
    this.selectAllCheckItem()
    // 默认查询销售列表
    this.listCheck()
  },
  methods: {
    // 点击tab事件
    handleClick() {
      console.log(this.activeName)
      if (this.activeName === 'check') {
        this.listCheck()
      } else {
        this.listCheckStat()
      }
    },
    // 查询销售列表
    listCheck() {
      this.loading = true
      //alert(this.queryCheckParams.checkItemId)
      var param={'checkItemId':this.queryCheckParams.checkItemId,'patientName':this.queryCheckParams.patientName,'dateRange1':this.dateRange[0],'dateRange2':this.dateRange[1]}
      this.$axios.post('/statistics/api/checkFeign/selectAll',qs.stringify(param)).then(res => {
        this.checkList = res.data.t
        this.loading = false
      }).catch(() => {
        this.msgError('查询失败')
        this.loading = false
      })
    },
    // 翻译检查单详情状态
    resultStatusFormatter(row) {
      this.$axios.get("/system/api/dict/data/findByType/his_check_result_status").then(result=>{
        this.statusOptions=result.data.t;
      })
    },
    // 销售列表查询
    handleCheckQuery() {
      this.listCheck()
    },
    // 重置销售列表查询
    resetCheckQuery() {
      this.resetForm('queryCheckFrom')
      this.dateRange = []
      this.listCheck()
    },
    // 查询销售列表
    listCheckStat() {
      this.loading = true
      //alert(this.queryCheckStatParams.checkItemId)
      var param={'checkItemId':this.queryCheckStatParams.checkItemId,'dateRange1':this.dateRange[0],'dateRange2':this.dateRange[1]}
      this.$axios.post('/statistics/api/checkFeign/selectAllTwo',qs.stringify(param)).then(res => {
        this.checkStatList = res.data.t
        this.loading = false
      }).catch(() => {
        this.msgError('查询失败')
        this.loading = false
      })
    },
    // 销售列表查询
    handleCheckStatQuery() {
      this.listCheckStat()
    },
    // 重置销售列表查询
    resetCheckStatQuery() {
      this.resetForm('queryCheckStatFrom')
      this.dateRange = []
      this.listCheckStat()
    },
    // 加载所有可用的检查项目
    selectAllCheckItem(){
      this.$axios.get("/check/api/checkItem/selectAllCheckItem").then(r=>{
        this.checkItemOptions = r.data.t
        this.queryParams.checkItemIds = this.checkItemOptions.map(item => item.checkItemId)
      })
    },
    //状态字典的解析
    initStatus(){
      this.$axios.get("/system/api/dict/data/findByType/his_check_result_status").then(result=>{
        this.statusOptions=result.data.t;
      })
    },
    //status
    statusFormat(row){
      let v=row.resultStatus;
      return this.formatDict2(this.statusOptions,v);
    }
  }
}
</script>
