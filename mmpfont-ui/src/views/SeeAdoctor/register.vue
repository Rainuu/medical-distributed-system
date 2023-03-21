<template>
  <div>
    <div>
      <el-form :inline="true" :model="patientInfo" class="demo-form-inline">
        <el-form-item label="所属部门">
          <el-select v-model="patientInfo.region" placeholder="请选择部门">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="患者姓名">
          <el-input v-model="patientInfo.username" placeholder="患者姓名"></el-input>
        </el-form-item>
        <el-form-item label="挂号类型">
          <el-select v-model="patientInfo.region" placeholder="请选择类型">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="挂号时段">
          <el-select v-model="patientInfo.region" placeholder="请选择部门">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="挂号状态">
          <el-select v-model="patientInfo.region" placeholder="请选择部门">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="挂号时间">
          <div class="block">
            <el-date-picker
                v-model="value3"
                type="datetime"
                placeholder="选择日期时间"
                default-time="12:00:00">
            </el-date-picker>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
          <el-button type="primary" @click="onSubmit" icon="el-icon-refresh">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
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
            label="患者姓名"
            width="110">
        </el-table-column>
        <el-table-column
            prop="name"
            label="挂号科室"
            width="120">
        </el-table-column>
        <el-table-column
            prop="province"
            label="接诊医生"
            width="120">
        </el-table-column>
        <el-table-column
            prop="city"
            label="挂号费用"
            width="120">
        </el-table-column>
        <el-table-column
            prop="address"
            label="流水编号"
            width="300">
        </el-table-column>
        <el-table-column
            prop="zip"
            label="状态"
            width="120">
        </el-table-column>
        <el-table-column
            prop="zip"
            label="就诊日期"
            width="120">
        </el-table-column>
        <el-table-column
            prop="zip"
            label="挂号类型"
            width="120">
        </el-table-column>
        <el-table-column
            prop="zip"
            label="挂号时段"
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
      multipleSelection:[]
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
    }
  }
}
</script>

<style scoped>

</style>