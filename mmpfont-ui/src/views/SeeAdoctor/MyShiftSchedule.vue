<template>
  <div>
    <!-- 工具栏 -->
    <div style="background-color: white">
      <el-form :inline="true" :model="formInline" class="demo-form-inline" style="margin-left: 750px">
        <el-form-item>
          <el-button type="primary" icon="el-icon-plus">上一周</el-button>
          <el-button type="success" icon="el-icon-s-operation">当前周</el-button>
          <el-button type="primary" icon="el-icon-plus">下一周</el-button>
        </el-form-item>
      </el-form>
    </div>
    <br>

    <!-- 日期时间 -->
    <div>
      <p>2023-03-06(周一)-2023-03-12(周日)</p>
    </div>
    <br>

    <!-- 表格主体 -->
    <div>
      <el-table :data="tableData" :span-method="objectSpanMethod" border style="width: 100%; margin-top: 20px">
        <el-table-column prop="doctor" label="医生"></el-table-column>
        <el-table-column prop="home" label="科室"></el-table-column>
        <el-table-column prop="date" label="时间/日期"></el-table-column>
        <el-table-column prop="day1" label="2023-03-06(周一)"></el-table-column>
        <el-table-column prop="day2" label="2023-03-07(周二)"></el-table-column>
        <el-table-column prop="day3" label="2023-03-08(周三)"></el-table-column>
        <el-table-column prop="day4" label="2023-03-09(周四)"></el-table-column>
        <el-table-column prop="day5" label="2023-03-10(周五)"></el-table-column>
        <el-table-column prop="day6" label="2023-03-11(周六)"></el-table-column>
        <el-table-column prop="day7" label="2023-03-12(周七)"></el-table-column>
        <el-table-column align="center" fixed="right" label="操作">
          <el-button @click="edit(scope.row)" type="text" size="small" icon="el-icon-edit">编辑</el-button>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyShiftSchedule",
  methods: {
    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      if (columnIndex === 0) {
        if (rowIndex % 3 === 0) {
          return {
            rowspan: 3,
            colspan: 1
          };
        } else {
          return {
            rowspan: 0,
            colspan: 0
          };
        }
      }
      if (columnIndex === 1) {
        if (rowIndex % 3 === 0) {
          return {
            rowspan: 3,
            colspan: 1
          };
        } else {
          return {
            rowspan: 0,
            colspan: 0
          };
        }
      }
      if (columnIndex === 10) {
        if (rowIndex % 3 === 0) {
          return {
            rowspan: 3,
            colspan: 1
          };
        } else {
          return {
            rowspan: 0,
            colspan: 0
          };
        }
      }
    }
  },
  data(){
    return {
      formInline: {
        user: '',
        region: ''
      },
      tableData: [{
        doctor: "方号",
        home: "内科",
        date: "上午",
        day1: "门诊",
        day2: 1,
        day3: 1,
        day4: 1,
        day5: 1,
        day6: 1,
        day7: 1,
      },
        {
          doctor: "方号",
          home: "内科",
          date: "下午",
          day1: "急诊",
          day2: 1,
          day3: 1,
          day4: 1,
          day5: 1,
          day6: 1,
          day7: 1,
        },
        {
          doctor: "方号",
          home: "内科",
          date: "晚上",
          day1: "门诊",
          day2: 1,
          day3: 1,
          day4: 1,
          day5: 1,
          day6: 1,
          day7: 1,
        }]
    }
  }
}
</script>

<style scoped>

</style>
