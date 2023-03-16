<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="float:left" >
      <el-form-item label="身份证号" style="float:left">
        <el-input v-model="formInline.user" placeholder="请输入患者身份证号" style="width: 400px"></el-input>
      </el-form-item>
      <el-form-item style="float:left">
        <el-button type="primary" @click="cx"  icon="el-icon-search">加载身份证号</el-button>
        <el-button type="warning" @click="cx"  icon="el-icon-search">加载患者信息</el-button>

      </el-form-item>
    </el-form>
    <!--    <div style="clear: both">请输入正确的挂号单ID查询</div>-->
    <el-form :inline="true" :model="formInline2" class="demo-form-inline" style="float: left;margin-top:30px" :rules="rules" >
      <el-form-item label="身份证号" prop="zfzh" required style="float: left">
        <el-input v-model="formInline2.user" placeholder="请输入身份证号" ></el-input>
      </el-form-item>
      <el-form-item label="患者姓名" prop="hzxm" required style="float:left">
        <el-input v-model="formInline2.user" placeholder="请输入患者姓名"></el-input>
      </el-form-item>
      <el-form-item label="患者电话" prop="hzdh" required style="float:left">
      <el-input v-model="formInline2.user" placeholder="请输入患者电话"></el-input>
    </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="formInline2.resource">
          <el-radio label="男"></el-radio>
          <el-radio label="女"></el-radio>
          <el-radio  label="未知"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="出生日期"  style="float: left;margin-left: 10px;clear: both">
        <el-col :span="11">
          <el-form-item>
            <el-date-picker type="date" placeholder="选择日期" v-model="formInline2.date1" style="width: 205px;"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="患者年龄" style="float:left;margin-left: 1px">
        <el-input v-model="formInline2.user" placeholder="请输入患者年龄"></el-input>
      </el-form-item>
      <el-form-item label="地址信息" style="float:left;margin-left: 8px">
        <el-input v-model="formInline2.user" placeholder="请输入地址信息" style="width: 450px"></el-input>
      </el-form-item>
    </el-form>
      <el-radio-group  v-model="radio1" style="clear: both;float: left;margin-top: 30px">
      <el-radio-button label="门诊"></el-radio-button>
      <el-radio-button label="门诊+病历本"></el-radio-button>
      <el-radio-button label="急诊"></el-radio-button>
      <el-radio-button label="急诊+病历本"></el-radio-button>
    </el-radio-group>
    <el-button type="danger" style="float: right;margin-top: 28px">挂号收费</el-button>

    <div style="clear: both">
      <el-form :inline="true" :model="formInline3" class="demo-form-inline" style="float: left;margin-top: 50px">
        <el-form-item label="所属科室" style="float:left">
            <el-input v-model="formInline3.user" placeholder="请输入操作类型"></el-input>
        </el-form-item>
        <el-form-item label="挂号类型" style="float:left">
          <el-input v-model="formInline3.user" placeholder="请输入操作类型"></el-input>
        </el-form-item>
        <el-form-item label="挂号时段" style="float:left">
          <el-input v-model="formInline3.user" placeholder="请输入操作类型"></el-input>
        </el-form-item>
        <el-form-item label="挂号时间"  style="float: left">
          <el-col :span="11">
            <el-form-item>
              <el-date-picker type="date" placeholder="选择日期" v-model="formInline3.date1" style="width: 210px"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item style="float: left">
          <el-button type="primary" @click="onSubmit"  icon="el-icon-search">搜索</el-button>
          <el-button type="primary" @click="resetForm('ruleForm')" icon="el-icon-refresh">重置</el-button>
        </el-form-item>
      </el-form>

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
        <el-table-column
            align="center"
            prop="je"
            label="科室ID"
            min-width="175">
        </el-table-column>
        <el-table-column
            align="center"
            prop="bz"
            label="科室名称"
            min-width="175">
        </el-table-column>
        <el-table-column
            align="center"
            prop="zt"
            fixed="right"
            label="当前号数"
            min-width="180">
        </el-table-column>

      </el-table>

    </div>
  </div>
</template>

<script>
export default {
  methods: {
    cx(){

    }
  },
  data() {
    return {
      formInline: {
        user: '',
        region: ''
      },
      size: '',
      tableData3:[{
        xh:1,
        xmmc:"脑心通",
        sl:2,
        dj:250,
        je:250,
        bz:"无",
        zt:"未支付"

      }],formInline2: {
        user: '',
        region: '',
        resource:"未知",
        date1:''

      },
      rules:{
        sfzh:'',
        hzxm:'',
        hzdh:''
      },
      radio1: '门诊',
      formInline3: {
        user: '',
        region: '',
        date1:''
      }
    }
  }


}
</script>