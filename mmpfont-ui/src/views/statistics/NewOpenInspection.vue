<!--新开检查-->
<template>
  <div >
    <!--form表单-->

    <el-form ref="form" :model="form" label-width="80px" style="text-align-last: justify;padding-left: 10px">

      <el-form-item label="检查项目" >

        <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange" >全选</el-checkbox>
        <div style="margin: 15px 0;"></div>
        <el-checkbox-group v-model="checkedCities" @change="handleCheckedCitiesChange">
          <el-checkbox v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox>
        </el-checkbox-group>

      </el-form-item>

    </el-form>


    <el-form :inline="true" :model="formInline" class="demo-form-inline" label-width="80px">

      <el-form-item label="挂号单号" style="float: left">
        <el-input v-model="formInline.user" placeholder="请输入挂号单号"></el-input>
      </el-form-item>

      <el-form-item style="float: left">
        <el-button icon="el-icon-search" type="primary" @click="onSubmit">搜索</el-button>
        <el-button icon="el-icon-refresh" type="primary" @click="onSubmit">重置</el-button>
      </el-form-item>
      <el-form-item>
      </el-form-item>

    </el-form>

    <!--table表格-->
    <el-table
        :data="tableData"
        height="250"
        border
        style="width: 100%">
      <el-table-column
          prop="id"
          label="序号"
          width="100">
      </el-table-column>
      <el-table-column
          prop="allid"
          label="详细ID"
          width="280">
      </el-table-column>
      <el-table-column
          prop="name"
          label="项目名称">
      </el-table-column>
      <el-table-column
          prop="num"
          label="数量">
      </el-table-column>
      <el-table-column
          prop="price"
          label="单价(元)">
      </el-table-column>
      <el-table-column
          prop="money"
          label="金额(元)">
      </el-table-column>
      <el-table-column
          prop="remark"
          label="备注">
      </el-table-column>
      <el-table-column
          prop="statu"
          label="状态">
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
const cityOptions = ['乙肝五项', '血常规', 'CT', 'X光'];
export default {
  name: "NewOpenInspection",

  data() {
    return {
      checkAll: false,
      checkedCities: ['乙肝五项', '血常规'],
      cities: cityOptions,
      isIndeterminate: true,

      formInline: {
        user: '',
        region: ''
      },
      form: {
        type: []
      },
      tableData: [{
        id: 1,
        allid: 73139529341861888,
        name: '乙肝五项',
        num: 1,
        price: 30,
        money: 30,
        remark: '按要求检查',
        statu: '已支付'
      }, {
        id: 2,
        allid: 3139529341863344,
        name: '血常规',
        num: 1,
        price: 5,
        money: 5,
        remark: '按要求检查',
        statu: '已支付'
      }, {
        id: 3,
        allid: 139529341866256,
        name: 'CT',
        num: 1,
        price: 50,
        money: 50,
        remark: '按要求检查',
        statu: '已支付'
      }],

    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    },

    handleCheckAllChange(val) {
      this.checkedCities = val ? cityOptions : [];
      this.isIndeterminate = false;
    },
    handleCheckedCitiesChange(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.cities.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
    }
  }
}
</script>

<style scoped>

</style>
