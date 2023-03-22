<!--补充页面-->
<template>
  <div id="zhuti">
    <div id="head">
      <el-tabs type="border-card">
        <el-button-group id="anniu">
          <el-button type="primary" @click="dianji1()">门诊</el-button>
          <el-button type="primary" @click="dianji2()">急诊</el-button>
        </el-button-group>
      </el-tabs>
    </div>
    <div id="body" :hidden="this.button1">
      <div id="left">
        <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-tabs type="border-card">
            <el-form-item label="活动名称" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="身份证号码" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="患者电话" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="性别:">
              <el-radio-group v-model="ruleForm.resource">
                <el-radio label="男"></el-radio>
                <el-radio label="女"></el-radio>
                <el-radio label="未知"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="出生日期" prop="name">
              <el-date-picker v-model="ruleForm.value1" type="datetime" placeholder="选择挂号日期时间">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="患者年龄" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="患者地址" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="患者地址" prop="name">
              <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容"
                        v-model="ruleForm.textarea2">
              </el-input>
            </el-form-item>

          </el-tabs>
          <el-tabs type="border-card">
            <el-tab-pane label="患者档案">暂无患者档案</el-tab-pane>
            <el-tab-pane label="历史档案">暂无历史档案</el-tab-pane>
          </el-tabs>
        </el-form>
      </div>
      <div id="right">
        <div id="right1">
          <el-tabs type="border-card">
            <div id="right1_left">
              <span>挂号单号:</span><br />
              <span>病历编号:</span>
            </div>
            <div id="right1_right">
              <el-button icon="el-icon-check" type="primary">保存病历</el-button>
              <el-button icon="el-icon-s-claim"el-icon-s-claim type="danger">完成就诊</el-button>
            </div>

          </el-tabs>
        </div>
        <div id="right2">
          <el-tabs type="border-card">
            <el-tab-pane label="病历">
              <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="出生日期" prop="name">
                  <el-date-picker v-model="ruleForm.value1" type="datetime" placeholder="选择挂号日期时间">
                  </el-date-picker>
                </el-form-item>
                <el-form-item label="接诊类型" >
                  <el-select v-model="ruleForm.region" placeholder="请选择接诊类型">
                    <el-option label="部门一" value="shanghai"></el-option>
                    <el-option label="部门二" value="beijing"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="是否传染" >
                  <el-select v-model="ruleForm.region" placeholder="是否传染">
                    <el-option label="是" value="shanghai"></el-option>
                    <el-option label="否" value="beijing"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="主诉" >
                  <el-input
                      type="textarea"
                      :autosize="{ minRows: 4, maxRows: 4}"
                      placeholder="请输入内容"
                      v-model="ruleForm.textarea2">
                  </el-input>
                </el-form-item>
                <el-form-item label="诊断信息" >
                  <el-input
                      type="textarea"
                      :autosize="{ minRows: 4, maxRows: 4}"
                      placeholder="请输入内容"
                      v-model="ruleForm.textarea2">
                  </el-input>
                </el-form-item>
                <el-form-item label="医生建议" >
                  <el-input
                      type="textarea"
                      :autosize="{ minRows: 4, maxRows: 4}"
                      placeholder="请输入内容"
                      v-model="ruleForm.textarea2">
                  </el-input>
                </el-form-item>
                <el-form-item label="备注" >
                  <el-input
                      type="textarea"
                      :autosize="{ minRows: 4, maxRows: 4}"
                      placeholder="请输入内容"
                      v-model="ruleForm.textarea2">
                  </el-input>
                </el-form-item>

              </el-form>

            </el-tab-pane>
            <el-tab-pane label="处方">
              <el-button icon="el-icon-plus" type="success">添加药用处方</el-button>
              <el-button icon="el-icon-plus" type="success">添加检测处方</el-button>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </div>
    <div id="body2" :hidden="this.button2">
      <div id="left">
        <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-tabs type="border-card">
            <el-form-item label="活动名称" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="身份证号码" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="患者电话" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="性别:">
              <el-radio-group v-model="ruleForm.resource">
                <el-radio label="男"></el-radio>
                <el-radio label="女"></el-radio>
                <el-radio label="未知"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="出生日期" prop="name">
              <el-date-picker v-model="ruleForm.value1" type="datetime" placeholder="选择挂号日期时间">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="患者年龄" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="患者地址" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="患者地址" prop="name">
              <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容"
                        v-model="ruleForm.textarea2">
              </el-input>
            </el-form-item>

          </el-tabs>
          <el-tabs type="border-card">
            <el-tab-pane label="患者档案">暂无患者档案</el-tab-pane>
            <el-tab-pane label="历史档案">暂无历史档案</el-tab-pane>
          </el-tabs>
        </el-form>
      </div>
      <div id="right">
        <div id="right1">
          <el-tabs type="border-card">
            <div id="right1_left">
              <span>挂号单号:</span><br />
              <span>病历编号:</span>
            </div>
            <div id="right1_right">
              <el-button icon="el-icon-check" type="primary">保存病历</el-button>
              <el-button icon="el-icon-s-claim"el-icon-s-claim type="danger">完成就诊</el-button>
            </div>

          </el-tabs>
        </div>
        <div id="right2">
          <el-tabs type="border-card">
            <el-tab-pane label="病历">
              <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="出生日期" prop="name">
                  <el-date-picker v-model="ruleForm.value1" type="datetime" placeholder="选择挂号日期时间">
                  </el-date-picker>
                </el-form-item>
                <el-form-item label="接诊类型" >
                  <el-select v-model="ruleForm.region" placeholder="请选择接诊类型">
                    <el-option label="部门一" value="shanghai"></el-option>
                    <el-option label="部门二" value="beijing"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="是否传染" >
                  <el-select v-model="ruleForm.region" placeholder="是否传染">
                    <el-option label="是" value="shanghai"></el-option>
                    <el-option label="否" value="beijing"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="主诉" >
                  <el-input
                      type="textarea"
                      :autosize="{ minRows: 4, maxRows: 4}"
                      placeholder="请输入内容"
                      v-model="ruleForm.textarea2">
                  </el-input>
                </el-form-item>
                <el-form-item label="诊断信息" >
                  <el-input
                      type="textarea"
                      :autosize="{ minRows: 4, maxRows: 4}"
                      placeholder="请输入内容"
                      v-model="ruleForm.textarea2">
                  </el-input>
                </el-form-item>
                <el-form-item label="医生建议" >
                  <el-input
                      type="textarea"
                      :autosize="{ minRows: 4, maxRows: 4}"
                      placeholder="请输入内容"
                      v-model="ruleForm.textarea2">
                  </el-input>
                </el-form-item>
                <el-form-item label="备注" >
                  <el-input
                      type="textarea"
                      :autosize="{ minRows: 4, maxRows: 4}"
                      placeholder="请输入内容"
                      v-model="ruleForm.textarea2">
                  </el-input>
                </el-form-item>

              </el-form>

            </el-tab-pane>
            <el-tab-pane label="处方">
              <el-button icon="el-icon-plus" type="success">添加药用处方</el-button>
              <el-button icon="el-icon-plus" type="success">添加检测处方</el-button>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {

      },
      tableData: [],
      button1: false,
      button2: true,



    }
  },
  methods: {
    //每页显示几条
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      // this.page.size = val;
      // this.querDate();
    },
    //当前页数
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      // this.page.current = val;
      // this.querDate();
    },
    dianji1(){
      this.button1=false;
      this.button2=true;
    },
    dianji2(){
      this.button1=true;
      this.button2=false;
    }

  }
}
</script>

<style>
#zhuti {
  margin: 10px;
}
#anniu {
  display: flex;
  align-items: center;
  justify-content: center;
}

#left {
  width: 30%;
  float: left;
  margin-top: 20px;
}

#right {
  height: 40px;
  width: 1300px;
  float: right;
  margin-top: 20px;
}
#right2{
  margin-top: 20px;
}

#right1_left {
  width: 50%;
  float: left;

}

#body{
  display: flex;
}
#right1_right {

  float: right;
}
</style>

