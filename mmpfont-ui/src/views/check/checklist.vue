<!--检查结果查询-->
<template>
  <div>
    <!-- 查询条件开始 -->
    <el-card style="margin-bottom:3px">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
        <el-form-item label="检查项目" prop="checkItemIds" style="float: left">
          <el-checkbox v-model="checkAll" :indeterminate="isIndeterminate" @change="handleCheckAllChange" style="float: left">全选</el-checkbox>
          <div style="clear: both"/>
          <el-checkbox-group v-model="queryParams.checkItemIds" @change="handleCheckedItemChange">
            <el-checkbox
                v-for="d in checkItemOptions"
                :key="d.checkItemId"
                :label="d.checkItemId"
                :value="d.checkItemId"
            >{{ d.checkItemName }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="挂号单号" prop="regId">
          <el-input
              v-model="queryParams.regId"
              placeholder="请输入挂号单号"
              clearable
              size="small"
              style="width:380px"
          />
        </el-form-item>
        <el-form-item label="患者姓名" prop="patientName" style="clear:both;float: left">
          <el-input
              v-model="queryParams.patientName"
              placeholder="请输入患者姓名"
              clearable
              size="small"
              style="width:380px"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="query">搜索</el-button>
          <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
<div>
    <el-table
        :data="tableData"
        border
        style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="价格"><span>{{ props.row.price }}</span></el-form-item>
            <el-form-item label="创建时间"><span>{{ props.row.createTime}}</span></el-form-item>
            <el-form-item label="检查结果描述"><span>{{ props.row.resultMsg}}</span></el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
          prop="cocId"
          label="检查单位"
      >
      </el-table-column>
      <el-table-column
          prop="regId"
          label="挂号单号"
      >
      </el-table-column>
      <el-table-column
          prop="checkItemName"
          label="项目名称"
      >
      </el-table-column>
      <el-table-column
          prop="patientName"
          label="患者姓名"
      >
      </el-table-column>
      <el-table-column
          prop="resultStatus"
          label="检查状态"
      >
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
      >
        <template slot-scope="scope">
          <el-button type="text" :disabled="scope.row.resultStatus==='0'" icon="el-icon-view" size="mini" @click="handleViewResult(scope.row)">查看结果</el-button>
        </template>
      </el-table-column>
    </el-table>
</div>
    <!--分页-->
    <el-pagination align="left"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="page.current"
                   :page-sizes="[10, 20, 30, 40]"
                   :page-size="page.size"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="page.total">
    </el-pagination>
    <!-- 录入检查结果弹层开始 -->
    <el-dialog
        :title="title"
        :visible.sync="open"
        width="1000px"
        center
        append-to-body
    >
      <el-form ref="form" label-width="80px">
        <el-form-item label="检查结果" prop="resultMsg">
          {{ currentResult.resultMsg }}
        </el-form-item>
        <div v-for="(item,index) in currentResult.resultImg" :key="index">
          <img :src="item.url">
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
      </span>
    </el-dialog>
    <!-- 录入检查结果弹层结束 -->
  </div>

</template>

<script>

const cityOptions = ['乙肝五项', '血常规', 'CT', 'X光'];
export default {

  methods: {
    // 全选
    handleCheckAllChange(val) {
      this.queryParams.checkItemIds = val ? this.checkItemOptions.map(item => item.checkItemId) : []
      this.isIndeterminate = true
    },
    // 选择某一个项目
    handleCheckedItemChange(value) {
      const checkedCount = value.length
      this.checkAll = checkedCount === this.checkItemOptions.length
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.checkItemOptions.length
      this.queryAllCheckResultForPage()
    },
    handleSizeChange(val) {
      this.page.size = val;
      this.queryData();
    },
    handleCurrentChange(val) {
      this.page.current = val;
      this.queryData();
    },
    Check(row) {
      this.dialogVisible = true;
      this.form = {};
      console.log(row);
    },
    //查询
    queryData(){
      this.$axios.post("/check/api/checkResult/list/"+this.page.current+"/"+this.page.size,this.queryParams).then(r=>{
        this.tableData=r.data.t.records;
        this.page.total=r.data.t.total
      })
    },
    // 查看结果
    handleViewResult(row) {
      // 打开录入结果的弹出层
      this.open = true
      this.title = '查看【' + row.patientName + '】的检查结果'
      // 记录当前选中的详情ID
      this.currentResult.resultMsg = row.resultMsg
      if (row.resultImg !== '') {
        this.currentResult.resultImg = JSON.parse(row.resultImg)
      }
    },
    // 关闭
    cancel() {
      this.open = false
      this.currentResult = {
        resultMsg: undefined,
        resultImg: [1,2,3,4]
      }
    },
    //查询
    query(){
      this.queryData()
    },
    // 加载所有可用的检查项目
    selectAllCheckItem(){
      this.$axios.get("/check/api/checkItem/selectAllCheckItem").then(r=>{
        this.checkItemOptions = r.data.t
        this.queryParams.checkItemIds = this.checkItemOptions.map(item => item.checkItemId)
      })
    }
  },
  created() {
    this.selectAllCheckItem()
    this.queryData()
  },

  data() {
    return {
      // 查询参数
      queryParams: {
        regId: undefined,
        patientName: undefined,
        checkItemIds: [],
      },
      dialogVisible: false,
      input1: '',
      input2: '',
      checkAll: false,
      checkItemOptions: [],
      cities: cityOptions,
      isIndeterminate: true,
      //分页
      page: {
        total: 10,
        size: 10,
        current: 1
      },
      tableData: [],
      // 是否打开录入结果的弹出层
      open: false,
      // 弹出层的标题
      title: '',
      // 当前查看的数据
      currentResult: {
        resultMsg: undefined,
        resultImg: []
      }
    }
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