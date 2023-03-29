<template>
  <div class="app-container">
    <!-- 查询条件开始 -->
    <el-row :gutter="12" style="margin-bottom:5px">
      <el-col :span="24">
        <el-card>
          <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
            <el-form-item label="科室名称" prop="deptId">
              <el-select
                  v-model="queryParams.deptId"
                  placeholder="请选择科室名称"
                  clearable
                  size="small"
                  style="width:180px"
              >
                <el-option
                    v-for="d in deptOptions"
                    :key="d.deptId"
                    :label="d.deptName"
                    :value="d.deptId"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="医生名称" prop="userId" :formatter="userFormatter">
              <el-select
                  v-model="queryParams.userId"
                  placeholder="请选择医生名称"
                  clearable
                  size="small"
                  style="width:180px"
              >
                <el-option
                    v-for="d in userOptions"
                    :key="d.userId"
                    :label="d.userName"
                    :value="d.userId"
                />
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
              <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
            <el-form-item style="float:right">
              <el-button type="primary" icon="el-icon-plus" size="small" @click="upWeek()">上一周</el-button>
              <el-button type="success" icon="el-icon-s-operation" size="small" @click="currentWeek()">当前周</el-button>
              <el-button type="primary" icon="el-icon-plus" size="small" @click="nextWeek()">下一周</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
    <!-- 查询条件结束 -->
    <!-- 时间范围开始 -->
    <el-row :gutter="12" style="margin-bottom:5px">
      <el-col :span="24">
        <el-card>
          <div style="text-align:center">
            <span>{{ schedulingData.startTimeThisWeek }}(周一)</span>-<span>{{ schedulingData.endTimeThisWeek }}(周日)</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!-- 时间范围结束 -->

    <!-- 排班表格开始 -->
    <el-table :data="tableData" border :span-method="spanMethod">
      <el-table-column prop="userId" label="医生" align="center" :formatter="userFormatter"/>
      <el-table-column prop="deptId" label="科室" align="center" :formatter="deptFormatter"/>
      <el-table-column prop="subsectionType" label="时间/日期" align="center" :formatter="subsectionTypeFormatter"/>
      <el-table-column prop="schedulingType[0]" :label="labelNames[0]" align="center"
                       :formatter="schedulingTypeDay1Formatter"/>
      <el-table-column prop="schedulingType[1]" :label="labelNames[1]" align="center"
                       :formatter="schedulingTypeDay2Formatter"/>
      <el-table-column prop="schedulingType[2]" :label="labelNames[2]" align="center"
                       :formatter="schedulingTypeDay3Formatter"/>
      <el-table-column prop="schedulingType[3]" :label="labelNames[3]" align="center"
                       :formatter="schedulingTypeDay4Formatter"/>
      <el-table-column prop="schedulingType[4]" :label="labelNames[4]" align="center"
                       :formatter="schedulingTypeDay5Formatter"/>
      <el-table-column prop="schedulingType[5]" :label="labelNames[5]" align="center"
                       :formatter="schedulingTypeDay6Formatter"/>
      <el-table-column prop="schedulingType[6]" :label="labelNames[6]" align="center"
                       :formatter="schedulingTypeDay7Formatter"/>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="text" icon="el-icon-edit" size="mini" @click="editScheduling(scope.row.userId)">编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 排班表格结束 -->

    <!-- 修改的弹出层开始 -->
    <el-dialog
        :title="title"
        :visible.sync="open"
        width="1200px"
        center
        append-to-body
    >
      <el-table :data="editData" border>
        <el-table-column prop="subsectionType" label="时间/日期" align="center" />
        <el-table-column prop="schedulingType[0]" :label="labelNames[0]" align="center">
          <template slot-scope="scope">
            <el-select
                v-model="scope.row.schedulingType[0]"
                clearable
                size="small"
            >
              <el-option
                  v-for="d in schedulingTypeOptions"
                  :key="d.dictValue"
                  :label="d.dictLabel"
                  :value="d.dictValue"
              />
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="schedulingType[1]" :label="labelNames[0]" align="center">
          <template slot-scope="scope">
            <el-select
                v-model="scope.row.schedulingType[1]"
                clearable
                size="small"
            >
              <el-option
                  v-for="d in schedulingTypeOptions"
                  :key="d.dictValue"
                  :label="d.dictLabel"
                  :value="d.dictValue"
              />
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="schedulingType[2]" :label="labelNames[0]" align="center">
          <template slot-scope="scope">
            <el-select
                v-model="scope.row.schedulingType[2]"
                clearable
                size="small"
            >
              <el-option
                  v-for="d in schedulingTypeOptions"
                  :key="d.dictValue"
                  :label="d.dictLabel"
                  :value="d.dictValue"
              />
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="schedulingType[3]" :label="labelNames[0]" align="center">
          <template slot-scope="scope">
            <el-select
                v-model="scope.row.schedulingType[3]"
                clearable
                size="small"
            >
              <el-option
                  v-for="d in schedulingTypeOptions"
                  :key="d.dictValue"
                  :label="d.dictLabel"
                  :value="d.dictValue"
              />
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="schedulingType[4]" :label="labelNames[0]" align="center">
          <template slot-scope="scope">
            <el-select
                v-model="scope.row.schedulingType[4]"
                clearable
                size="small"
            >
              <el-option
                  v-for="d in schedulingTypeOptions"
                  :key="d.dictValue"
                  :label="d.dictLabel"
                  :value="d.dictValue"
              />
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="schedulingType[5]" :label="labelNames[0]" align="center">
          <template slot-scope="scope">
            <el-select
                v-model="scope.row.schedulingType[5]"
                clearable
                size="small"
            >
              <el-option
                  v-for="d in schedulingTypeOptions"
                  :key="d.dictValue"
                  :label="d.dictLabel"
                  :value="d.dictValue"
              />
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="schedulingType[6]" :label="labelNames[0]" align="center">
          <template slot-scope="scope">
            <el-select
                v-model="scope.row.schedulingType[6]"
                clearable
                size="small"
            >
              <el-option
                  v-for="d in schedulingTypeOptions"
                  :key="d.dictValue"
                  :label="d.dictLabel"
                  :value="d.dictValue"
              />
            </el-select>
          </template>
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleSubmit">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </span>
    </el-dialog>
    <!-- 修改的弹出层结束 -->
  </div>
</template>
<script>

export default {
  data() {
    return {
      // 科室数据
      deptOptions: [],
      // 用户数据
      userOptions: [],
      // 排班时间段字典
      subsectionTypeOptions: [],
      // 排班类型字典
      schedulingTypeOptions: [],
      // 查询条件
      queryParams: {
        deptId: undefined,
        userId: undefined,
        queryDate: undefined
      },
      schedulingData: {
        startTimeThisWeek: undefined,
        endTimeThisWeek: undefined
      },
      // 排班表的数据   应该从后台查询
      tableData: [],
      // 表头数据
      labelNames: ['', '', '', '', '', '', ''],
      // 弹出层的开启
      open: false,
      // 弹出层的标题
      title: '',
      // 弹出层里面的表格数据
      editData: []
    }
  },
  computed: {
    groupNum() { // 获取医生列表数组
      return new Set(this.tableData.map(o => o.userId))
    }
  },
  created() {
    //查询所有科室
    this.queryDept();
    // 查询医生名称
    this.queryUser();
    // 查询排班数据  返回值要填充schedulingData tableData labelNames
    this.listScheduling();
        //查询查询排班类型字典信息
    this.queryRegistrationType();
//查询查询医生排班时间字典信息
    this.queryRegistrationPeriod();
  },
  methods: {
    // 查询所有用户
    listScheduling() {
      // json后台接收的方式必须为post方式
      this.$axios.post("/doctor/scheduling/queryScheduling", this.queryParams).then(result => {
        this.tableData = result.data.t.tableDate;
        this.schedulingData.startTimeThisWeek = result.data.t.beginDate;
        this.schedulingData.endTimeThisWeek = result.data.t.endDate;
        this.labelNames = result.data.t.labelNames;
      })
    },
// 部门的解析   翻译科室
    deptFormatter(row) {
      let v = row.deptId;
      for (let i = 0; i < this.deptOptions.length; i++) {
        if (this.deptOptions[i].deptId == v) {
          return this.deptOptions[i].deptName;
        }
      }
      return "";
    },
    //查询所有部门
    queryDept() {
      this.$axios.get("/system/api/dept/list").then(result => {
        this.deptOptions = result.data.t;
      })
    },
    // 翻译用户
    queryUser() {
      this.$axios.post("/doctor/scheduling/queryUserName").then(result => {
        this.userOptions = result.data.t;
      })
    },
    // 用户的解析
    userFormatter(row) {
      let v = row.userId;
      for (let i = 0; i < this.userOptions.length; i++) {
        if (this.userOptions[i].userId == v) {
          return this.userOptions[i].userName;
        }
      }
      return "";
    },
    //查询排班里的排班类型字典 门诊 或 急诊
    queryRegistrationType(){
      this.$axios.get("/system/api/dict/data/findByType/his_scheduling_type").then(result=>{
        this.schedulingTypeOptions=result.data.t;
      })
    },
    // 解析类型
    // //把挂号类型翻译
    // typeFormatter(row) {
    //   let v=row.schedulingType;
    //   return this.formatDict2(this.schedulingTypeOptions,v);
    // },
    //查询排班里的时间字典  上午、下午、晚上
    queryRegistrationPeriod(){
      this.$axios.get("/system/api/dict/data/findByType/his_subsection_type").then(result=>{
        this.subsectionTypeOptions=result.data.t;
      })
    },
    // 翻译排班时段
    subsectionTypeFormatter(row) {
      return this.formatDict2(this.subsectionTypeOptions, row.subsectionType)
    },
    // 翻译周一排班类型
    schedulingTypeDay1Formatter(row) {
      return this.formatDict2(this.schedulingTypeOptions, row.schedulingType[0])
    },
    // 翻译周一排班类型
    schedulingTypeDay2Formatter(row) {
      return this.formatDict2(this.schedulingTypeOptions, row.schedulingType[1])
    },
    // 翻译周二排班类型
    schedulingTypeDay3Formatter(row) {
      return this.formatDict2(this.schedulingTypeOptions, row.schedulingType[2])
    },
    // 翻译周三排班类型
    schedulingTypeDay4Formatter(row) {
      return this.formatDict2(this.schedulingTypeOptions, row.schedulingType[3])
    },
    // 翻译周四排班类型
    schedulingTypeDay5Formatter(row) {
      return this.formatDict2(this.schedulingTypeOptions, row.schedulingType[4])
    },
    // 翻译周五排班类型
    schedulingTypeDay6Formatter(row) {
      return this.formatDict2(this.schedulingTypeOptions, row.schedulingType[5])
    },
    // 翻译周六排班类型
    schedulingTypeDay7Formatter(row) {
      return this.formatDict2(this.schedulingTypeOptions, row.schedulingType[6])
    },
    // 查询
    handleQuery() {

      this.listScheduling()
    },

    // 重置
    resetQuery() {
      // this.resetForm('queryForm');
      this.queryParams={};
      this.listScheduling();

    },
    // 上一周
    upWeek() {
      this.queryParams.queryDate = this.schedulingData.startTimeThisWeek
      this.listScheduling()
    },
    // 当前周
    currentWeek() {
      this.queryParams.queryDate = undefined
      this.listScheduling()
    },
    // 下一周
    nextWeek() {
      this.queryParams.queryDate = this.schedulingData.endTimeThisWeek
      this.listScheduling()
    },


    // 合并的代码
    spanMethod(data) { // 对于表格数据进行分组合并操作，UI组件回调函数
      const {row, rowIndex, columnIndex} = data
      if (columnIndex < 2 || columnIndex > 9) { // 医生合并展示区
        const len = this.userGroup(row.userId)
        const lenName = this.userIdLen(row.userId)
        if (rowIndex === lenName) { // 某医生首位部门行
          return {
            rowspan: len,
            colspan: 1
          }
        } else {
          return { // 某医生非首位部门行
            rowspan: 0,
            colspan: 0
          }
        }
      } else { // 排班信息展示区
        return {
          rowspan: 1,
          colspan: 1
        }
      }
    },
    userGroup(name) { // 根据医生名称获取医生数量
      return this.tableData.filter(o => o.userId === name).length
    },
    userIdLen(name) { // 根据医生名称获取该医生第一个部门在全量部门中的偏移位置
      const tmp = Array.from(this.groupNum)
      const index = tmp.indexOf(name) // 某医生在全医生中的偏移位置
      let len = 0
      for (let i = 0; i < index; i++) {
        len += this.userGroup(tmp[i])
      }
      return len
    },
    // 打开编辑页面
    editScheduling(userId) {
      this.editData = []
      this.open = true
      let name = ''
      this.userOptions.filter(item => {
        if (userId === item.userId) {
          name = item.userName
        }
      })
      this.title = '修改【' + name + '】的排班信息'
      this.tableData.filter(item => {
        if (item.userId === userId) {
          this.editData.push(item)
        }
      })
    },
    // 关闭
    cancel() {
      this.open = false
    },
    // 保存
    handleSubmit() {
      // const form = { beginDate: this.schedulingData.startTimeThisWeek, data: this.editData }

      // 保存
    }
  }

}
</script>
