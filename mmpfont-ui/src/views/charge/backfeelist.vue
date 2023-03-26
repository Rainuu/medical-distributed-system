<template>
  <div>
    <!-- 工具栏 -->
    <div style="height: 40px; line-height: 40px; background-color: whitesmoke">
      <el-form :inline="true" :model="formInline" style="float:left;padding-left: 20px;" >
        <el-form-item label="患者姓名">
          <el-input v-model="formInline.patientName" placeholder="请输入患者姓名"></el-input>
        </el-form-item>
        <el-form-item label="挂号单号" style="padding-left: 60px;">
          <el-input v-model="formInline.regId" placeholder="请输入挂号单号"></el-input>
        </el-form-item>
        <el-form-item style="padding-left: 60px;">
          <el-button type="primary" @click="initUser"  icon="el-icon-search">搜索</el-button> &nbsp;&nbsp; |&nbsp;
          <el-button type="primary" @click="empty" icon="el-icon-refresh">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <br>
    <!-- 页面表格 -->
    <div>
      <el-table :data="tableData" border style="width: 100%" max-height="330px">
        <el-table-column fixed prop="backId" label="退费订单号"  min-width="50px" align="center"></el-table-column>
        <el-table-column prop="regId" label="挂号单号"  min-width="50px" align="center"></el-table-column>
        <el-table-column prop="patientName" label="患者姓名"  width="100px" align="center"></el-table-column>
        <el-table-column prop="backAmount" label="总金额"  width="100px" align="center"></el-table-column>
        <el-table-column prop="backType" label="退费类型"  width="100px" align="center" :formatter="(row)=>this.dictFormat(row,row.backType,'his_pay_type_status')"></el-table-column>
        <el-table-column prop="backStatus" label="订单状态"  width="100px" align="center" :formatter="(row)=>this.dictFormat(row,row.backStatus,'his_order_details_status')"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"  min-width="50px" align="center"></el-table-column>
        <el-table-column fixed="right" label="操作" min-width="60px" align="center">
          <template slot-scope="scope">
            <el-button  type="text" size="small" icon="el-icon-view" @click="initdetail(scope.row)">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>

    </div>
    <!--    查看详情-->
    <div>
      <el-dialog :title="title" :visible.sync="dialogVisible" width="60%">
        <el-table :data="tableDetail" border style="width: 100%" >
          <el-table-column fixed prop="itemId" label="详情ID"  min-width="150px" align="center"></el-table-column>
          <el-table-column prop="coId" label="处方ID"  min-width="150px" align="center"></el-table-column>
          <el-table-column prop="itemName" label="名称"  width="80px" align="center"></el-table-column>
          <el-table-column prop="itemPrice" label="价格"  width="80px" align="center"></el-table-column>
          <el-table-column prop="itemNum" label="数量"  width="60px" align="center"></el-table-column>
          <el-table-column prop="itemAmount" label="小计"  width="60px" align="center"></el-table-column>
          <el-table-column label="类型" align="center" prop="itemType" width="60px" >
            <template slot-scope="scope">
              {{ scope.row.itemType==='0'?'药品':'检查' }}
            </template>
          </el-table-column>
          <el-table-column prop="status" label="状态"  min-width="50px" align="center" :formatter="(row)=>this.dictFormat(row,row.status,'his_backfee_status')"></el-table-column>
        </el-table>
        <el-button @click="stop">关闭</el-button>
      </el-dialog>
    </div>

    <!-- 分页控件开始 -->
    <div class="block">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="curr"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <!-- 分页控件结束 -->


  </div>
</template>

<script>
export default {
  data() {
    return {
      //总条数
      total:0,
      curr:1,
      size:5,
      formInline: {},
      tableData: [],
      dialogVisible:false,
      title:"",
      tableDetail:[],
      dictList:[]
    }
  },
  methods: {
    //字典解析
    dictFormat(row, column, dictType){
      return this.formatDict( this.dictList,column, dictType)
    },
    //调用字典方法
    getDict() {
      this.$axios.get('charge/api/hisOrderBackfee/dictionary').then(res => {
        this.dictList = res.data.t
      })
    },
    //关闭弹出框功能
    stop(){
      this.dialogVisible=false
    },
    initdetail(row){
      //打开弹出框
      this.dialogVisible=true;
      this.title="查询【"+row.patientName+"】支付订单详情";
      this.$axios.get("charge/api/hisOrderBackfee/detail/"+row.backId).then(result=>{
        this.tableDetail=result.data.t;
      })
    },
    //重置方法
    empty(){
      this.formInline={},
      this.initUser();
    },
    //分页查询所有数据
    initUser(){
      this.$axios.post("charge/api/hisOrderBackfee/list/"+this.curr+"/"+this.size,this.formInline).then(result=>{
        this.tableData=result.data.t.records;
        this.total=result.data.t.total;
      })
    },
    handleClick(row) {
      console.log(row);
    },
    // 改变每页显示多少条数据
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`);
      this.size = val
      this.initUser();
    },
    // 当前第几页
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`);
      this.curr = val
      this.initUser();
    },
  },
  created() {
    //在页面初始化加载表格数据
    this.initUser();
    //在页面初始化加载字典
    this.getDict();
  }
}
</script>