<template>
<div>
  <div>
  <el-form :inline="true" :model="patientInfo" class="demo-form-inline">
    <el-form-item label="患者姓名">
      <el-input v-model="patientInfo.username" placeholder="患者姓名"></el-input>
    </el-form-item>

      <el-form-item label="手机号">
        <el-input v-model="patientInfo.phone" placeholder="手机号"></el-input>
      </el-form-item>

        <el-form-item label="身份证号">
          <el-input v-model="patientInfo.IDCard" placeholder="身份证号"></el-input>
        </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
  </el-form>
  </div>
  <div style="float: left">
    <el-button type="primary" @click="dialogVisible = true">查看档案</el-button>
    <el-button type="primary" @click="dialogVisible = true">查看就诊病例</el-button>
  </div>
  <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose">
    <div style="height:500px">
      <el-scrollbar>

    <el-col :span="10" v-for="(item, index) in tableArr" :key="index">
      <div class="box">
        <div class="content1">{{ item.key }}</div>
        <div class="content2">{{ item.value == "" ? "待完善" : item.value }}</div>
      </div>
    </el-col>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
      </el-scrollbar>
    </div>
  </el-dialog>
  <div>
    <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        border
        style="width: 100%"
    @select-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          fixed
          prop="data"
          label="姓名"
          width="150">
      </el-table-column>
      <el-table-column
          prop="name"
          label="电话"
          width="120">
      </el-table-column>
      <el-table-column
          prop="province"
          label="身份证"
          width="120">
      </el-table-column>
      <el-table-column
          prop="city"
          label="出生年月"
          width="120">
      </el-table-column>
      <el-table-column
          prop="address"
          label="年龄"
          width="300">
      </el-table-column>
      <el-table-column
          prop="zip"
          label="性别"
          width="120">
      </el-table-column>
      <el-table-column
          prop="zip"
          label="信息状态"
          width="120">
      </el-table-column>
      <el-table-column
          prop="zip"
          label="创建时间"
          width="120">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage4"
          :page-sizes="[100, 200, 300, 400]"
          :page-size="100"
          layout="total, sizes, prev, pager, next, jumper"
          :total="400">
      </el-pagination>
    </div>
  </div>
</div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false,
      currentPage4: 4,
      patientInfo: {
        username: '',
        phone: '',
        IDCard:''
      },
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-04',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1517 弄',
        zip: 200333
      }, {
        date: '2016-05-01',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1519 弄',
        zip: 200333
      }, {
        date: '2016-05-03',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1516 弄',
        zip: 200333
      }],
      multipleSelection:[],
      tableArr:[
        {
          key: "姓名",
          value: "孙悟空",
        },
        {
          key: "年龄",
          value: 500,
        },
        {
          key: "身高",
          value: "山一样高",
        },
        {
          key: "性别",
          value: "男",
        },
        {
          key: "住址",
          value: "花果山水帘洞",
        },
        {
          key: "学历",
          value: "天庭弼马温学历",
        },
        {
          key: "能力",
          value: "强",
        },
        {
          key: "外号",
          value: "齐天大圣",
        },
      ]
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    },
    handleClick(row) {
      console.log(row);
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    }
  }
}
</script>

<style scoped>

</style>