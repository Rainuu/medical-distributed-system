<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="float:left" >
      <el-form-item label="挂号单ID" style="float:left">
        <el-input v-model="formInline.user" placeholder="请输入挂号单号"></el-input>
      </el-form-item>
      <el-form-item style="float:left">
        <el-button type="primary" @click="cx"  icon="el-icon-search">查询</el-button>
        <el-button type="primary" @click="resetForm('ruleForm')" icon="el-icon-refresh">读取IC卡</el-button>
      </el-form-item>
    </el-form>
<!--    <div style="clear: both">请输入正确的挂号单ID查询</div>-->



    <div>
      <el-descriptions class="margin-top" :column="2" :size="size" border>
        <el-descriptions-item>
          <template slot="label">
            挂号单号:
          </template>
          1
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            医生姓名:
          </template>
          牛牧原
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            科室名称:
          </template>
          神经内科
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            患者姓名:
          </template>
          方狗
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            就诊时间:
          </template>
          2030-01-01 00:00:00
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            主诉:
          </template>
          蛋疼
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            诊断信息:
          </template>
          睾丸炎
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            医生建议:
          </template>
          手术切除
        </el-descriptions-item>


      </el-descriptions>
    <div style="float: left;clear: both;margin-top:20px">
      <el-button type="success" icon="el-icon-finished">全选</el-button>
      <el-button type="success" icon="el-icon-finished">取消全选</el-button>
      <el-button type="danger" icon="el-icon-bank-card">现金支付</el-button>
      <el-button type="danger" icon="el-icon-bank-card">支付宝支付</el-button>
    </div>
    <div style="float: left;clear: both;margin-top:20px" :data="tableData3">
      【检查处方】【】【处方总额】【250】
    </div>

    <el-table
        ref="multipleTable"
        :data="tableData3"
        tooltip-effect="dark"


        border
        style="width: 100%;clear: both"
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          align="center"
          prop="xh"
          label="序号"
          width="55">
      </el-table-column>
      <el-table-column
          align="center"
          prop="xmmc"
          label="项目名称"
          min-width="175">
      </el-table-column>
      <el-table-column
          align="center"
          prop="sl"
          label="数量"
          min-width="175">
      </el-table-column>
      <el-table-column
          align="center"
          prop="dj"
          label="单价(元)"
          min-width="175">
      </el-table-column>
      <el-table-column
          align="center"
          prop="je"
          label="金额(元)"
          min-width="175">
      </el-table-column>
      <el-table-column
          align="center"
          prop="bz"
          label="备注"
          min-width="175">
      </el-table-column>
      <el-table-column
          align="center"
          prop="zt"
          fixed="right"
          label="状态"
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

      }]
    }
  }


}
</script>