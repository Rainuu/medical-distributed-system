<template>
  <div v-loading="loading" class="app-container">
    <!-- 选择门诊和急诊的选项开始 -->
    <el-card style="margin-bottom:5px;text-align:center">
      <el-radio-group v-model="schedulingType" @change="schedulingTypeChange">
        <el-radio-button
            v-for="dict in schedulingTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictValue"
            :value="dict.dictValue"
        >
          {{ dict.dictLabel }}
        </el-radio-button>
      </el-radio-group>
    </el-card>
    <!-- 选择门诊和急诊的选项结束 -->

    <!-- 下面的整体卡片开始 -->
    <el-row :gutter="5">
      <el-col :span="7">
        <!-- 左边的患者信息开始 -->
        <el-card>
          <el-form ref="form" :model="patientAllObj.patientObj" :inline="true" label-width="88px">
            <el-form-item label="患者姓名" prop="name">
              <el-input
                  v-model="patientAllObj.patientObj.name"
                  placeholder="请输入患者姓名"
                  :disabled="true"
                  style="width:100%"
                  size="small"
              >
                <el-button slot="append" icon="el-icon-user-solid" @click="viewRegistration" />
              </el-input>
            </el-form-item>
            <el-form-item label="身份证号" prop="idCard">
              <el-input
                  v-model="patientAllObj.patientObj.idCard"
                  placeholder="请输入身份证号"
                  :disabled="true"
                  style="width:100%"
                  size="small"
              />
            </el-form-item>
            <el-form-item label="患者电话" prop="phone">
              <el-input
                  v-model="patientAllObj.patientObj.phone"
                  placeholder="请输入患者电话"
                  :disabled="true"
                  style="width:100%"
                  size="small"
              />
            </el-form-item>
            <el-form-item label="性别" prop="sex">
              <el-radio-group v-model="patientAllObj.patientObj.sex">
                <el-radio
                    v-for="d in sexOptions"
                    :key="d.dictValue"
                    :label="d.dictValue"
                    :value="d.dictValue"
                >{{ d.dictLabel }}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="出生日期" prop="phone">
              <el-input
                  v-model="patientAllObj.patientObj.birthday"
                  placeholder="请选择出生日期"
                  :disabled="true"
                  style="width:100%"
                  size="small"
              />
            </el-form-item>
            <el-form-item label="患者年龄" prop="age">
              <el-input
                  v-model="patientAllObj.patientObj.age"
                  placeholder="请输入患者年龄"
                  :disabled="true"
                  style="width:100%"
                  size="small"
              />
            </el-form-item>
            <el-form-item label="患者地址" prop="address">
              <el-input
                  v-model="patientAllObj.patientObj.address"
                  placeholder="请输入患者地址"
                  :disabled="true"
                  style="width:100%"
                  size="small"
              />
            </el-form-item>
            <el-form-item label="过敏信息" prop="allergyInfo">
              <el-input
                  v-model="patientAllObj.patientObj.allergyInfo"
                  placeholder="请输入过敏信息"
                  type="textarea"
                  :autosize="{minRows:2,maxRows:4}"
                  :disabled="true"
                  maxlength="100"
                  show-word-limit
                  style="width:100%"
                  size="small"
              />
            </el-form-item>
          </el-form>
          <el-tabs :stretch="true" type="card">
            <el-tab-pane label="患者档案">
              <div v-if="patientAllObj.patientObj.isFinal==='1'">
                <div class="item">
                  紧急联系人:<span v-text="patientAllObj.patientFileObj.emergencyContactName" />
                </div>
                <div class="item">
                  紧急联系人电话:<span v-text="patientAllObj.patientFileObj.emergencyContactPhone" />
                </div>
                <div class="item">
                  关系: <span v-text="patientAllObj.patientFileObj.emergencyContactRelation" />
                </div>
                <div class="item">
                  左耳听力:<span v-text="patientAllObj.patientFileObj.leftEarHearing" />
                </div>
                <div class="item">
                  右耳听力:<span v-text="patientAllObj.patientFileObj.rightEarHearing" />
                </div>
                <div class="item">
                  左眼视力:<span v-text="patientAllObj.patientFileObj.leftVision" />
                </div>
                <div class="item">
                  右眼视力:<span v-text="patientAllObj.patientFileObj.rightVision" />
                </div>
                <div class="item">
                  身高:<span v-text="patientAllObj.patientFileObj.height" />
                </div>
                <div class="item">
                  体重:<span v-text="patientAllObj.patientFileObj.weight" />
                </div>
                <div class="item">
                  血型:<span v-text="patientAllObj.patientFileObj.bloodType" />
                </div>
                <div class="item">
                  个人史:<span v-text="patientAllObj.patientFileObj.personalInfo" />
                </div>
                <div class="item">
                  家族史:<span v-text="patientAllObj.patientFileObj.familyInfo" />
                </div>
                <div class="item">
                  档案创建时间:<span v-text="patientAllObj.patientFileObj.createTime" />
                </div>
                <div class="item">
                  档案更新时间:<span v-text="patientAllObj.patientFileObj.updateTime" />
                </div>
              </div>
              <div v-else style="text-align:center">
                暂无患者档案信息
              </div>
            </el-tab-pane>
            <el-tab-pane label="历史病历">
              <div v-if="patientAllObj.careHistoryObjList.length>0">
                <el-collapse accordion>
                  <el-collapse-item v-for="(item,index) in patientAllObj.careHistoryObjList" :key="index">
                    <template slot="title">
                      就诊部门:【{{ item.deptName }}】 就诊时间:{{ item.careDate }}
                    </template>
                    <div class="item">
                      主诉:{{ item.caseTitle }}
                    </div>
                    <div class="item">
                      发病日期:{{ item.caseDate }}
                    </div>
                    <div class="item">
                      诊断信息:{{ item.caseResult }}
                    </div>
                    <div class="item">
                      医生建议:{{ item.doctorTips }}
                    </div>
                    <div class="item">
                      备注:{{ item.remark }}
                    </div>
                    <div class="item">
                      接诊类型:{{ item.receiveType==='0'?'初诊':'复诊' }}
                    </div>
                    <div class="item">
                      是否传染:{{ item.isContagious==='0'?'否':'是' }}
                    </div>
                  </el-collapse-item>
                </el-collapse>
              </div>
              <div v-else style="text-align:center">
                暂无患者病历信息
              </div>
            </el-tab-pane>
          </el-tabs>
        </el-card>

        <!-- 左边的患者信息结束 -->
      </el-col>
      <el-col :span="17">
        <!-- 右边的病历，处方开始 -->
        <el-card>
          <el-row :gutter="5">
            <el-col :span="12">
              挂号单号:
              <span v-if="careHistory.regId===undefined" style="color:red">选择患者后显示</span>
              <span v-else style="color:red">{{ careHistory.regId }}</span>
              <br>
              病历编号:
              <span v-if="careHistory.chId===undefined" style="color:red">保存病例后显示</span>
              <span v-else style="color:#0000ff">{{ careHistory.chId }}</span>
            </el-col>
            <el-col :span="12" style="text-align:right">
              <el-button type="primary" :disabled="careHistory.regId===undefined" icon="el-icon-check" @click="handleSaveCareHistory">保存病历</el-button>
              <el-button type="danger" :disabled="careHistory.regId===undefined" icon="el-icon-finished" @click="handleVisitComplete">完成就诊</el-button>
            </el-col>
          </el-row>
        </el-card>
        <el-card style="margin-top:3px">
          <el-tabs :v-model="careActiveName" type="card">
            <el-tab-pane label="病历">
              <!-- 病例表单开始 -->
              <el-card>
                <el-form ref="form" :model="careHistory" :inline="true" label-width="88px">
                  <el-form-item label="发病日期" prop="caseDateObj">
                    <el-date-picker
                        v-model="caseDateObj"
                        value-format="yyyy-MM-dd"
                        size="small"
                    />
                  </el-form-item>
                  <el-form-item label="接诊类型" prop="receiveType">
                    <el-select
                        v-model="careHistory.receiveType"
                        placeholder="接诊类型"
                        size="small"
                    >
                      <el-option
                          v-for="dict in receiveTypeOptions"
                          :key="dict.dictValue"
                          :label="dict.dictLabel"
                          :value="dict.dictValue"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="是否传染" prop="isContagious">
                    <el-select
                        v-model="careHistory.isContagious"
                        placeholder="是否传染"
                        size="small"
                    >
                      <el-option
                          v-for="dict in isContagiousOptions"
                          :key="dict.dictValue"
                          :label="dict.dictLabel"
                          :value="dict.dictValue"
                      />
                    </el-select>
                  </el-form-item>
                </el-form>
                <el-form ref="form" :model="careHistory" label-width="88px">
                  <el-form-item label="主诉" :inline="false" prop="caseTitle">
                    <el-input
                        v-model="careHistory.caseTitle"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6}"
                        placeholder="请输入主诉"
                        style="width:100%"
                        maxlength="200"
                        show-word-limit
                        size="small"
                    />
                  </el-form-item>
                  <el-form-item label="诊断信息" :inline="false" prop="caseResult">
                    <el-input
                        v-model="careHistory.caseResult"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6}"
                        placeholder="请输入诊断信息"
                        style="width:100%"
                        maxlength="200"
                        show-word-limit
                        size="small"
                    />
                  </el-form-item>
                  <el-form-item label="医生建议" :inline="false" prop="doctorTips">
                    <el-input
                        v-model="careHistory.doctorTips"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6}"
                        placeholder="请输入医生建议"
                        style="width:100%"
                        maxlength="200"
                        show-word-limit
                        size="small"
                    />
                  </el-form-item>
                  <el-form-item label="备注" :inline="false" prop="remark">
                    <el-input
                        v-model="careHistory.remark"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6}"
                        placeholder="请输入备注"
                        style="width:100%"
                        maxlength="200"
                        show-word-limit
                        size="small"
                    />
                  </el-form-item>
                </el-form>
              </el-card>
              <!-- 病例表单结束 -->
            </el-tab-pane>
            <el-tab-pane label="处方">
              <!-- 处方详情开始 -->
              <el-collapse v-if="careOrders.length>0" accordion>
                <el-collapse-item v-for="(item,index) in careOrders" :key="index">
                  <template slot="title">
                    【{{ item.careOrder.coType==='0'?'药用处方':'检查处方' }}】【{{ index+1 }}】【处方总额】:￥{{ item.careOrder.allAmount }}
                  </template>
                  <el-table
                      v-loading="loading"
                      border
                      :data="item.careOrderItems"
                  >
                    <el-table-column label="序号" align="center" type="index" width="50" />
                    <el-table-column :label="item.careOrder.coType==='0'?'药品名称':'项目名称'" align="center" prop="itemName" />
                    <el-table-column label="数量" align="center" prop="num" />
                    <el-table-column label="单价(元)" align="center" prop="price" />
                    <el-table-column label="金额(元)" align="center" prop="amount" />
                    <el-table-column label="备注" align="center" prop="remark" />
                    <el-table-column label="状态" align="center" prop="status" :formatter="orderDetailsStatusFormatter" />
                    <el-table-column label="操作" align="center">
                      <template slot-scope="scope">
                        <el-button v-show="scope.row.status==='0'" type="text" icon="el-icon-delete" size="mini" @click="handleCareOrderItemDeleteByItemId(scope.row)">删除</el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                </el-collapse-item>
              </el-collapse>
              <!-- 处方详情结束 -->
              <div style="margin:3px;text-align:left">
                <el-button type="success" icon="el-icon-plus" :disabled="careHistory.chId===undefined" @click="handelAddMedicinesOrder">添加药用处方</el-button>
                <el-button type="success" icon="el-icon-plus" :disabled="careHistory.chId===undefined" @click="handelAddCheckItemOrder">添加检查处方</el-button>
              </div>
            </el-tab-pane>
          </el-tabs>
        </el-card>
        <!-- 右边的病历，处方结束 -->
      </el-col>
    </el-row>
    <!-- 下面的整体卡片结束 -->

    <!-- 弹出选择患者的层开始 -->
    <el-dialog
        title="请选择挂号患者"
        :visible.sync="openRegistration"
        width="1000px"
        center
        :close-on-click-modal="false"
        append-to-body
    >
      <el-tabs v-model="activeName" :stretch="true" type="card" @tab-click="handleRegistrationTabClick">
        <el-tab-pane label="待就诊列表" name="t1">
          <el-table v-loading="tableLoading" border :data="toBeSeenRegistration">
            <el-table-column type="expand">
              <template slot-scope="scope">
                <el-form label-position="right" inline class="demo-table-expand">
                  <el-form-item label="挂号ID">
                    <span>{{ scope.row.regId }}</span>
                  </el-form-item>
                  <el-form-item label="患者ID">
                    <span>{{ scope.row.patientId }}</span>
                  </el-form-item>
                  <el-form-item label="挂号员">
                    <span>{{ scope.row.createBy }}</span>
                  </el-form-item>
                  <el-form-item label="挂号时间">
                    <span>{{ scope.row.createTime }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column label="患者姓名" align="center" prop="patientName" />
            <el-table-column label="流水编号" align="center" prop="regNumber" />
            <el-table-column label="挂号类型" align="center" prop="schedulingType" :formatter="schedulingTypeFormatter" />
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button type="success" icon="el-icon-check" size="mini" @click="handleVisit(scope.row)">接诊</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="就诊中列表" name="t2">
          <el-table v-loading="tableLoading" border :data="visitingRegistration">
            <el-table-column type="expand">
              <template slot-scope="scope">
                <el-form label-position="right" inline class="demo-table-expand">
                  <el-form-item label="挂号ID">
                    <span>{{ scope.row.regId }}</span>
                  </el-form-item>
                  <el-form-item label="患者ID">
                    <span>{{ scope.row.patientId }}</span>
                  </el-form-item>
                  <el-form-item label="挂号员">
                    <span>{{ scope.row.createBy }}</span>
                  </el-form-item>
                  <el-form-item label="挂号时间">
                    <span>{{ scope.row.createTime }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column label="患者姓名" align="center" prop="patientName" />
            <el-table-column label="流水编号" align="center" prop="regNumber" />
            <el-table-column label="挂号类型" align="center" prop="schedulingType" :formatter="schedulingTypeFormatter" />
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button type="success" icon="el-icon-check" size="mini" @click="handleLoading(scope.row)">载入</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="就诊完成列表" name="t3">
          <el-table v-loading="tableLoading" border :data="visitCompletedRegistration">
            <el-table-column type="expand">
              <template slot-scope="scope">
                <el-form label-position="right" inline class="demo-table-expand">
                  <el-form-item label="挂号ID">
                    <span>{{ scope.row.regId }}</span>
                  </el-form-item>
                  <el-form-item label="患者ID">
                    <span>{{ scope.row.patientId }}</span>
                  </el-form-item>
                  <el-form-item label="挂号员">
                    <span>{{ scope.row.createBy }}</span>
                  </el-form-item>
                  <el-form-item label="挂号时间">
                    <span>{{ scope.row.createTime }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column label="患者姓名" align="center" prop="patientName" />
            <el-table-column label="流水编号" align="center" prop="regNumber" />
            <el-table-column label="挂号类型" align="center" prop="schedulingType" :formatter="schedulingTypeFormatter" />
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button type="success" icon="el-icon-check" size="mini" @click="handleLoading(scope.row)">载入</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
    <!-- 弹出选择患者的层结束 -->

    <!-- 添加药用处方和检查出方的弹出层开始 -->
    <el-dialog
        :title="title"
        :visible.sync="openAddOrderItem"
        width="1000px"
        center
        :close-on-click-modal="false"
        append-to-body
    >
      <div style="margin:3px;text-align:right">
        【{{ submitCareOrder.careOrder.coType==='0'?'药用':'检查' }}】处方总额:￥<span style="color:red">{{ submitCareOrder.careOrder.allAmount }}</span>
        <el-button style="margin-left:15px" type="success" icon="el-icon-plus" :disabled="submitCareOrder.careOrderItems.length===0" @click="handleSaveOrderItem">确定添加</el-button>
      </div>
      <el-table
          border
          :data="submitCareOrder.careOrderItems"
          :row-class-name="tableRowClassName"
      >
        <el-table-column label="序号" align="center" type="index" width="50" />
        <el-table-column :label="submitCareOrder.careOrder.coType==='0'?'药品名称':'项目名称'" align="center" prop="itemName" />
        <el-table-column label="数量" width="180px" align="center" prop="num">
          <template slot-scope="scope">
            <el-input-number
                v-model="scope.row.num"
                size="small"
                @change="handleCareOrderItemNumChange(scope.row)"
            />
          </template>
        </el-table-column>
        <el-table-column label="单价(元)" align="center" prop="price" />
        <el-table-column label="金额(元)" align="center" prop="amount" />
        <el-table-column label="备注" align="center" prop="remark">
          <template slot-scope="scope">
            <el-input
                v-model="scope.row.remark"
                size="small"
            />
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-delete" size="mini" @click="handleCareOrderItemDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="primary" size="mini" style="width:100%;margin-top:2px" @click="handleOpenAddOrderItemDrawer">添加【{{ submitCareOrder.careOrder.coType==='0'?'药品':'检查' }}】项 </el-button>
    </el-dialog>
    <!-- 添加药用处方和检查处方的弹出层结束 -->

    <!-- 药品列表的抽屉开始 -->
    <el-drawer
        :visible.sync="openDrawerMedicines"
        direction="rtl"
        append-to-body
    >
      <h2 align="center">药品列表</h2>
      <el-form ref="queryItemForm" :model="queryItemFormParams" label-width="68px">
        <el-form-item label="关键字" prop="keywords">
          <el-input
              v-model="queryItemFormParams.keywords"
              placeholder="请输入关键字"
              clearable
              size="small"
              style="width:180px"
          />
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleMedicinesFormQuery">查询</el-button>
          <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetItemFormQuery">重置</el-button>
        </el-form-item>

        <el-table
            v-loading="drawerLoading"
            height="600px"
            border
            :data="tableItemList"
            @selection-change="handleMedicinesSelectionChange"
        >
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="药品ID" align="center" prop="medicinesId" />
          <el-table-column label="药品名称" align="center" prop="medicinesName" />
          <el-table-column label="关键字" align="center" prop="keywords" />
          <el-table-column label="库存量" align="center" prop="medicinesStockNum" />
          <el-table-column label="单位" align="center">
            <template slot-scope="scope">
              {{ scope.row.conversion }}/ {{ scope.row.unit }}
            </template>
          </el-table-column>
        </el-table>
        <!-- 数据表格结束 -->
        <!-- 分页控件开始 -->
        <el-pagination
            v-show="total>0"
            :current-page="queryItemFormParams.pageNum"
            :page-sizes="[5, 10, 20, 30]"
            :page-size="queryItemFormParams.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleMedicinesSizeChange"
            @current-change="handleMedicinesCurrentChange"
        />
        <el-form-item>
          <div style="text-align:center">
            <el-button type="primary" icon="el-icon-check" size="mini" @click="hanldeAddCareItem">添加并关闭</el-button>
          </div>
        </el-form-item>
      </el-form>
      <!-- 分页控件结束 -->
    </el-drawer>
    <!-- 药品列表的抽屉结束 -->

    <!-- 检查项目的抽屉开始 -->
    <el-drawer
        :visible.sync="openDrawerCheckItem"
        direction="rtl"
        append-to-body
    >
      <h2 align="center">检查项目列表</h2>
      <el-form ref="queryItemForm" :model="queryItemFormParams" label-width="68px">
        <el-form-item label="关键字" prop="keywords">
          <el-input
              v-model="queryItemFormParams.keywords"
              placeholder="请输入关键字"
              clearable
              size="small"
              style="width:180px"
          />
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleCheckItemFormQuery">查询</el-button>
          <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetItemFormQuery">重置</el-button>
        </el-form-item>

        <el-table
            v-loading="drawerLoading"
            height="600px"
            border
            :data="tableItemList"
            @selection-change="handleCheckItemSelectionChange"
        >
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="项目ID" align="center" prop="checkItemId" />
          <el-table-column label="项目名称" align="center" prop="checkItemName" />
          <el-table-column label="关键字" align="center" prop="keywords" />
          <el-table-column label="项目单价" align="center" prop="unitPrice" />
          <el-table-column label="单位" align="center" prop="unit" />
        </el-table>
        <!-- 数据表格结束 -->
        <!-- 分页控件开始 -->
        <el-pagination
            v-show="total>0"
            :current-page="queryItemFormParams.pageNum"
            :page-sizes="[5, 10, 20, 30]"
            :page-size="queryItemFormParams.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleCheckItemSizeChange"
            @current-change="handleCheckItemCurrentChange"
        />
        <el-form-item>
          <div style="text-align:center">
            <el-button type="primary" icon="el-icon-check" size="mini" @click="hanldeAddCareItem">添加并关闭</el-button>
          </div>
        </el-form-item>
      </el-form>

    </el-drawer>
    <!-- 检查项目的抽屉结束 -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 总的遮罩层
      loading: false,
      // 表格的遮罩层
      tableLoading: false,
      // 门诊急诊的默认值
      schedulingType: '1',
      // 就诊类型的字典数据
      schedulingTypeOptions: [],
      // 排班类型的字典数据
      subsectionTypeOptions: [],
      // 性别数据字典
      sexOptions: [],
      // 接诊类型字典
      receiveTypeOptions: [],
      // 是否传染字典
      isContagiousOptions: [],
      // 处方详情的状态字典数据
      orderDetailsStatusOptions: [],
      // 是否打开选择挂号患者的弹层
      openRegistration: false,
      // 当前选中的挂号选项卡的
      activeName: 't1',
      // 右边的病例和处方的选项卡
      careActiveName: 'c1',
      // 待就诊数据
      toBeSeenRegistration: [],
      // 就诊中的数据
      visitingRegistration: [],
      // 就诊完成的数据
      visitCompletedRegistration: [],
      // 发病日期
      caseDateObj: new Date(),
      // 左边患者数据
      patientAllObj: {
        patientObj: {},
        patientFileObj: {},
        careHistoryObjList: []
      },
      // 提交到数据库的对象
      careHistory: {
        // 当前就诊中的挂号单ID
        regId: undefined,
        chId: undefined,
        caseDate: undefined,
        receiveType: '0',
        isContagious: '0',
        caseTitle: undefined,
        caseResult: undefined,
        doctorTips: undefined,
        remark: undefined,
        patientId: undefined,
        patientName: undefined
      },
      // 存放处方及详情数据
      careOrders: [],
      // 最后要提交到的处方及详情
      submitCareOrder: {
        careOrder: {
          allAmount: 0.00,
          patientId: undefined,
          patientName: undefined,
          coType: '0' // 默认为药用处方
        },
        careOrderItems: []
      },
      // 弹出层的标题
      title: '',
      // 是否打开药品和检查项的添加的弹出层
      openAddOrderItem: false,
      // 是否打开药品列表的抽屉
      openDrawerMedicines: false,
      // 是否打开检查项目列表的抽屉
      openDrawerCheckItem: false,
      // 药品和检查项目抽屉的查询条件
      queryItemFormParams: {
        pageNum: 1,
        pageSize: 10,
        keywords: undefined
      },
      // 药品或检查项目表格的数据
      tableItemList: [],
      // 抽屉里面数据的总条数
      total: 0,
      // 抽屉数据加载的遮罩
      drawerLoading: false,
      // 抽屉里面选中的数据
      selectItemList: [],


    }
  },
  created() {
    //查询门诊急诊按钮
    this.queryRegistrationType();
    // 查询新开就诊中挂号患者的就诊列表
    this.queryRegistrationNumber1();
    this.queryRegistrationNumber2();

    this.queryRegistrationNumber3();
  },
  methods: {
    // 门诊急诊切换事件
    schedulingTypeChange(value) {
      this.schedulingType = value
    },
    // 打开选择挂号患者的弹出层
    viewRegistration() {
      this.$axios.get("doctor/patient/queryRegistrationStatus1/",).then(result=> {

        this.activeName = 't1'
        this.openRegistration = true
        this.queryToBeSeenRegistration()
      })
    },
    //查询门诊急诊按钮
    queryRegistrationType(){
      this.$axios.get("/system/api/dict/data/findByType/his_scheduling_type").then(result=>{
        this.schedulingTypeOptions=result.data.t;
      })
    },
    // 查询挂号患者的就诊列表








  }
}
</script>
<style scoped>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.item{
  font-size: 14px;
  margin-bottom: 8px;
}
</style>
