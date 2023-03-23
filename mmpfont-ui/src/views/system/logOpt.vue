<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="float:left">
      <el-form-item label="系统模块" style="float:left">
        <el-input v-model="formInline.user" placeholder="请输入系统模块"></el-input>
      </el-form-item>&nbsp&nbsp
      <el-form-item label="操作人员" style="float:left">
        <el-input v-model="formInline.user" placeholder="请输入操作人员"></el-input>
      </el-form-item>&nbsp&nbsp
      <el-form-item label="操作类型" style="float:left">
        <el-input v-model="formInline.user" placeholder="请输入操作类型"></el-input>
      </el-form-item>&nbsp&nbsp
      <el-form-item label="操作状态" style="float:left;clear: both">
        <el-select v-model="formInline.region" placeholder="请选择操作状态">
          <el-option label="区域一" value="shanghai"></el-option>
          <el-option label="区域二" value="beijing"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" style="float:left">
        <el-date-picker
            v-model="value3"
            type="datetimerange"
            range-separator="-"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item style="float:left">
        <el-button type="primary" @click="onSubmit"  icon="el-icon-search">查询</el-button>
        <el-button type="primary" @click="resetForm('ruleForm')" icon="el-icon-refresh">重置</el-button>
      </el-form-item>
    </el-form>
    <div style="float: left;clear: both">
      <el-button type="danger" icon="el-icon-delete">删除</el-button>
      <el-button type="warning" icon="el-icon-error">清空</el-button>
    </div>
    <el-table
        ref="multipleTable"
        :data="tableData3"
        tooltip-effect="dark"
        border
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column type="expand">
          <template slot-scope="props">
            <el-form  inline class="demo-table-expand">
              <el-form-item label="操作模块">
                <span>{{ props.row.name }}</span>
              </el-form-item>
              <el-form-item label="登录信息">
                <span>{{ props.row.shop }}</span>
              </el-form-item>
              <el-form-item label="请求地址">
                <span>{{ props.row.id }}</span>
              </el-form-item>
              <el-form-item label="操作方法">
                <span>{{ props.row.shopId }}</span>
              </el-form-item>
              <el-form-item label="请求参数">
                <span>{{ props.row.category }}</span>
              </el-form-item>
              <el-form-item label="返回参数">
                <span>{{ props.row.address }}</span>
              </el-form-item>
              <el-form-item label="操作状态">
                <span>{{ props.row.desc }}</span>
              </el-form-item>
              <el-form-item label="操作时间">
                <span>{{ props.row.time}}</span>
              </el-form-item>
              <el-form-item label="异常信息">
                <span>{{ props.row.ycxx }}</span>
              </el-form-item>
            </el-form>
          </template>
      </el-table-column>
      <el-table-column
          align="center"
          prop="ksid"
          label="日志ID"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="ksmc"
          label="系统模块"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="zt1"
          label="操作类型"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="zt2"
          label="请求方式"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fbz"
          label="操作人员"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fbz"
          label="主机"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fbz"
          label="操作地点"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          prop="fbz"
          label="操作状态"
          min-width="100">
      </el-table-column>
      <el-table-column
          align="center"
          label="操作时间"
          min-width="150">
        <template slot-scope="scope">{{ scope.row.date }}</template>
      </el-table-column>
      <el-table-column
          align="center"
          fixed="right"
          label="操作"
          min-width="100">
        <template slot-scope="scope"  align="center">
          <el-button @click="shanchu(scope.row)" type="text" size="small" icon="el-icon-delete">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block" style="float: left">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage4"
          :page-sizes="[100, 200, 300, 400]"
          :page-size="4"
          layout="total, sizes, prev, pager, next, jumper"
          :total="400">
      </el-pagination>
    </div>
  </div>
</template>
<style>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 250px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item  {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>

<script>
export default {
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    onSubmit() {
      console.log('submit!');
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }
  },
  data() {
    return {
      tableData3: [{
        id: '12987122',
        name: '好滋好味鸡蛋仔',
        category: '江浙小吃、小吃零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }, {
        id: '12987123',
        name: '好滋好味鸡蛋仔',
        category: '江浙小吃、小吃零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }, {
        id: '12987125',
        name: '好滋好味鸡蛋仔',
        category: '江浙小吃、小吃零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }, {
        id: '12987126',
        name: '好滋好味鸡蛋仔',
        category: '江浙小吃、小吃零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }],
      multipleSelection: [],
      formInline: {
        user: '',
        region: ''
      },
      currentPage4: 4,
      value3: [],

    }
  }


}
</script>