<template>
  <div id="page">
    <div>
      <el-radio-group  v-model="radio1" >
        <el-radio-button label="门诊" >
        </el-radio-button>
        <el-radio-button label="急诊" ></el-radio-button>
      </el-radio-group>
    </div>
    <br>

      <el-container>
        <el-aside width="500px">
            <el-form ref="form" :model="form" label-width="100px" label-height="100px" style="float: left;margin-top: 20px">
              <el-form-item label="患者姓名">
                <el-input v-model="form.region" placeholder="请输入患者姓名" disabled style="width:300px;float: left"  suffix-icon="iconfont el-icon-user-solid"></el-input>
              </el-form-item>
              <el-form-item label="身份证号">
                <el-input v-model="form.region" placeholder="请输入身份证号" disabled style="width:300px;float: left"  ></el-input>
              </el-form-item>
              <el-form-item label="患者电话">
                <el-input v-model="form.region" placeholder="请输入患者电话" disabled style="width:300px;float: left"  ></el-input>
              </el-form-item>
              <el-form-item label="性别" style="float: left">
                <el-radio-group v-model="form.resource">
                  <el-radio label="男"></el-radio>
                  <el-radio label="女"></el-radio>
                  <el-radio label="未知"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="出生日期" style="clear: both">
                <el-input v-model="form.region" placeholder="请输入出生日期" disabled style="width:300px;"  ></el-input>
              </el-form-item>
              <el-form-item label="患者年龄">
                <el-input v-model="form.region" placeholder="请输入患者年龄" disabled style="width:300px;float: left"  ></el-input>
              </el-form-item>
              <el-form-item label="患者地址">
                <el-input v-model="form.region" placeholder="请输入患者地址" disabled style="width:300px;float: left"  ></el-input>
              </el-form-item>
              <div style="margin: 20px 0;"></div>
              <el-form-item label="过敏信息">
                <el-input type="textarea" placeholder="请输入过敏信息" v-model="textarea"  disabled show-word-limit></el-input>
              </el-form-item>
              <el-tabs v-model="activeName" type="card" @tab-click="handleClick" stretch="boolean">
                <el-tab-pane label="患者档案" name="first">暂无患者档案信息</el-tab-pane>
                <el-tab-pane label="历史病历" name="second">暂无患者历史病历</el-tab-pane>
              </el-tabs>
            </el-form>
        </el-aside>
        <el-main>
          <div>
            <el-form ref="form" :model="form" style="margin-top: 50px">
              <div style="float: left">
                <p>挂号单号:<font color="red">选择患者后显示</font></p>
                <p>病历编号:<font color="red">保存病例后显示</font></p>
              </div>
              <div style="float: right;padding-top: 20px;">
                <el-button type="primary" plain icon="el-icon-check">保存病历</el-button>
                <el-button type="danger" plain  icon="el-icon-finished">完成就诊</el-button>
              </div>
            </el-form>
          </div>
          <div>
            <el-tabs v-model="formInline" type="card" @tab-click="handleClick">
              <el-tab-pane label="病例" name="first">
                <div class="block">
                  <span class="demonstration">发病日期</span>
                  <el-date-picker
                      v-model="value1"
                      type="date"
                      placeholder="选择日期">
                  </el-date-picker>
                  </div>
                  <el-form-item label="接诊类型">
                    <el-select v-model="formInline.region" placeholder="初诊">
                      <el-option label="区域一" value="shanghai"></el-option>
                      <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="是否传染">
                    <el-select v-model="formInline.region" placeholder="否">
                      <el-option label="区域一" value="shanghai"></el-option>
                      <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="主诉" v-model="textarea" >
                  <el-input type="textarea" placeholder="请输入内容"  maxlength="200" show-word-limit>
                  </el-input>
                  </el-form-item>
                  <el-form-item label="诊断信息" v-model="textarea" >
                    <el-input type="textarea" placeholder="请输入诊断信息"  maxlength="200" show-word-limit>
                    </el-input>
                  </el-form-item>
                  <el-form-item label="医生建议" v-model="textarea" >
                    <el-input type="textarea" placeholder="请输入医生建议"  maxlength="200" show-word-limit>
                    </el-input>
                  </el-form-item>
                  <el-form-item label="备注" v-model="textarea" >
                    <el-input type="textarea" placeholder="请输入医生备注"  maxlength="200" show-word-limit>
                    </el-input>
                  </el-form-item>
              </el-tab-pane>
              <el-tab-pane label="处方" name="second">








              </el-tab-pane>

            </el-tabs>
          </div>
        </el-main>
      </el-container>

  </div>
</template>

<script>
export default {
  data() {
    return {
      formInline:{
        region:'',
        textarea:''
      },
      value1:{},
      activeName:
          { name:"first"},
      textarea: '',
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      radio1: '门诊',

    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    }
  }
}
</script>

<style scoped>
  .el-aside {

    background-color: #D3DCE6;
    color: #333;
    text-align: center;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
  }

</style>