<template>
  <div>
<!--    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="float:left" >-->
<!--      <el-form-item label="挂号单ID" style="float:left">-->
<!--        <el-input v-model="formInline.user" placeholder="请输入挂号单号"></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item style="float:left">-->
<!--        <el-button type="primary" @click="find"  icon="el-icon-search">查询</el-button>-->
<!--        <el-button type="primary" @click="resetForm('ruleForm')" icon="el-icon-refresh">读取IC卡</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->

  <div style="display: flex;">
    <span style="float: contour;line-height: 40px;margin: 20px 10px">挂号ID</span>
    <el-input  placeholder="请输入挂号单号" v-model="input1" style="width: 400px;float: contour;margin: 20px 10px"/>

    <el-button type="primary"  icon="el-icon-search" @click="find" style="margin: 20px 10px">查询</el-button>
    <el-button type="primary"  icon="el-icon-search" @click="resetForm" style="margin: 20px 10px">读取IC卡</el-button>

  </div>

    <div style="clear: both">请输入正确的挂号单ID查询</div>



    <el-table
        :data="tableData"
        border
        style="width: 100%">
      <el-table-column
          fixed
          prop="date"
          label="日期"
          >
      </el-table-column>
      <el-table-column
          prop="name"
          label="姓名"
          >
      </el-table-column>
      <el-table-column
          prop="province"
          label="省份"
          >
      </el-table-column>
      <el-table-column
          prop="city"
          label="市区"
          >
      </el-table-column>
      <el-table-column
          prop="address"
          label="地址"
          >
      </el-table-column>
      <el-table-column
          prop="zip"
          label="邮编"
          >
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          >
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>


  </div>

</template>

<script>
export default {
  methods: {
    handleClick(row) {
      console.log(row);
    },
    find(){

    }
  },
  data() {
    return {
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
      formInline: {
        user: '',
        region: ''
      }
    }
  }


}
</script>