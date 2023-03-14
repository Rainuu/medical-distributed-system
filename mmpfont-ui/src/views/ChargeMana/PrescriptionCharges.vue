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
    <div style="clear: both">请输入正确的挂号单ID查询</div>
  </div>
</template>

<script>
export default {
  methods: {
    cx(){
      this.$router.push({name:'pc2'})

    }
  },
  data() {
    return {
      formInline: {
        user: '',
        region: ''
      }
    }
  }


}
</script>