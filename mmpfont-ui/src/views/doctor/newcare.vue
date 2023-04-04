<!--新开就诊-->
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
            <el-form-item label="性别" prop="sex" :formatter="sexFormat">
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
                  <el-form-item label="接诊类型" prop="receiveType" :formatter="receivetypeFormatter">
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
                  <el-form-item label="是否传染" prop="isContagious" :formatter="contagiousFormatter">
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
                    【{{ item.coType==='0'?'药用处方':'检查处方' }}】【{{ index+1 }}】【处方总额】:￥{{ item.allAmount }}
                  </template>
                  <el-table
                      v-loading="loading"
                      border
                      :data="item.careOrderItemList"
                  >
                    <el-table-column label="序号" align="center" type="index" width="50" />
                    <el-table-column :label="item.careOrderItemList.itemType==='0'?'药品名称':'项目名称'" align="center" prop="itemName" />
                    <el-table-column label="数量" align="center" prop="num" />
                    <el-table-column label="单价(元)" align="center" prop="price" />
                    <el-table-column label="金额(元)" align="center" prop="amount" />
                    <el-table-column label="备注" align="center" prop="remark" />
                    <el-table-column label="状态" align="center" prop="status" :formatter="orderDetailsStatusFormatter" />
                    <el-table-column label="操作" align="center">
                      <template slot-scope="scope">
                        <el-button v-show="scope.row.status==='0'" type="text" icon="el-icon-delete" size="mini" @click="DeleteByItemIdhandleCareOrderItem(scope.row)">删除</el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                </el-collapse-item>
              </el-collapse>
              <!-- 处方详情结束 -->
              <div style="margin:3px;text-align:left">

                <el-button type="success" :disabled="careHistory.chId===undefined" icon="el-icon-plus"  @click="handelAddMedicinesOrder">添加药用处方</el-button>
                <el-button type="success" :disabled="careHistory.chId===undefined" icon="el-icon-plus"  @click="handelAddCheckItemOrder">添加检查处方</el-button>
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
                    <span>{{ scope.row.registrationId }}</span>
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
            <el-table-column label="流水编号" align="center" prop="registrationNumber" />
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
                    <span>{{ scope.row.registrationId }}</span>
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
            <el-table-column label="流水编号" align="center" prop="registrationNumber" />
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
                    <span>{{ scope.row.registrationId }}</span>
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
            <el-table-column label="流水编号" align="center" prop="registrationNumber" />
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
                :max="scope.row.stock"
                min="1"
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
      // 提交到数据库的对象 处方的中间表
      careHistory: {
        // 当前就诊中的挂号单ID
        regId: undefined,
        chId: undefined,
        caseDate: undefined,
        receiveType: '',
        isContagious: '',
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
      selectItemList: []
    }
  },
  created() {
    //查询门诊急诊按钮
    this.queryRegistrationType();


    //字典区
    //查询字典
    this.initSex();
    //查询右边病历里的接诊类型下拉列表 初诊 复诊
    this.queryReceptionType();
    //查询右边病历里的是否传染
    this.queryInfectType();
    //查询药品处方跟内容里的支付状态
    this.queryStatus();


  },
  methods: {
    // 门诊急诊切换事件
    schedulingTypeChange(value) {
      this.resetAllData();
      if (value){
        this.schedulingType = value
      }

    },
    // 打开选择挂号患者的弹出层
    viewRegistration() {
      this.activeName = 't1'
      //查询待就诊的患者
      this.queryRegistrationNumber1();
      this.openRegistration = true
    },
    //查询门诊急诊按钮
    queryRegistrationType(){
      this.$axios.get("/system/api/dict/data/findByType/his_scheduling_type").then(result=>{
        this.schedulingTypeOptions=result.data.t;
        //清空患者的数据
        this.patientAllObj.patientObj={};
        this.patientAllObj.patientFileObj={};
        this.patientAllObj.areHistoryObjList=[];
      })
    },
    //查询待接诊的患者的数量
    queryRegistrationNumber1(){
      this.$axios.post("/doctor/patient/queryRegistrationStatus1/1/"+this.schedulingType).then(result=>{
        this.toBeSeenRegistration=result.data.t;
      })
    },
    //查询接诊中的患者的数量
    queryRegistrationNumber2(){
      this.$axios.post("/doctor/patient/queryRegistrationStatus1/2/"+this.schedulingType).then(result=>{
        this.visitingRegistration=result.data.t;
      })
    },
    //查询接诊完成的患者的数量
    queryRegistrationNumber3(){
      this.$axios.post("/doctor/patient/queryRegistrationStatus1/3/"+this.schedulingType).then(result=>{
        this.visitCompletedRegistration=result.data.t;
      })
    },

    //字典区
    //药与处方状态
    //查询性别字典
    initSex(){
      this.$axios.get("/system/api/dict/data/findByType/sys_user_sex").then(result=>{
        this.sexOptions=result.data.t;
      })
    },
    //把性别的0/1换成男女等
    sexFormat(row){
      let v=row.sex;
      return this.formatDict2(this.sexOptions,v);
    },
    //查询药品处方跟内容里的支付状态
    queryStatus() {
      this.$axios.get("/system/api/dict/data/findByType/his_order_details_status").then(result=>{
        this.orderDetailsStatusOptions=result.data.t;
      })
    },
    //查询挂号类型字典下拉列表
    queryRegistrationType(){
      this.$axios.get("/system/api/dict/data/findByType/his_scheduling_type").then(result=>{
        this.schedulingTypeOptions=result.data.t;
      })
    },
    //
    //查询右边病历里的接诊类型 初诊 复诊
    queryReceptionType(){
      this.$axios.get("/system/api/dict/data/findByType/his_receive_type").then(result=>{
        this.receiveTypeOptions=result.data.t;
        this.careHistory.receiveType=this.receiveTypeOptions[0].dictValue;
      })
    },
    // 解析初诊复诊
    receivetypeFormatter() {
      let v=row.receiveType;
      return this.formatDict2(this.receiveTypeOptions,v);
    },
    //查询右边病历里的是否传染下拉列表 是否
    queryInfectType(){
      this.$axios.get("/system/api/dict/data/findByType/his_contagious_status").then(result=>{
        this.isContagiousOptions=result.data.t;
        //提前设置是否传染 默认否
        this.careHistory.isContagious=this.isContagiousOptions[0].dictValue;
      })
    },
    // 右侧病历中---》是否传染  是、否
    contagiousFormatter() {
      let v=row.isContagious;
      return this.formatDict(this.isContagiousOptions,v);
    },

    //翻译字典区
    //把挂号类型翻译
    schedulingTypeFormatter(row){
      let v=row.schedulingType;
      return this.formatDict2(this.schedulingTypeOptions,v);
    },
    // 翻译处方详情状态
    orderDetailsStatusFormatter(row) {
      return this.formatDict2(this.orderDetailsStatusOptions, row.status)
    },

    //左边患者弹出层里的待接诊  接诊按钮
    handleVisit(row){
      //接诊之后根据挂号单id 改变挂号的状态 改为就诊中
      this.$axios.get("/doctor/registered/updRegistrationId/"+row.registrationId+"/2").then(result=>{
        if (result.data.t){
          //接诊成功后 执行查询患者数据
          this.$message({
            showClose: true,
            message: '接诊成功',
            type: 'success'
          });
          //根据患者id查询患者信息然后赋值到左边的表单里
          this.$axios.post("/doctor/patient/queryByPatient/"+row.patientId).then(result=>{
            //赋值之前先清空上一个患者的数据
            this.patientAllObj.patientObj={};
            this.patientAllObj.patientFileObj={};
            this.patientAllObj.areHistoryObjList=[];
            //把患者数据赋值到左边的表单里
            this.patientAllObj.patientObj=result.data.t;
            this.careHistory.regId = row.registrationId;
            //点击接诊按钮 接诊类型直接赋值为0初诊
            this.careHistory.receiveType=this.receiveTypeOptions[1].dictValue;
            //提前设置是否传染 默认否
            this.careHistory.isContagious=this.isContagiousOptions[0].dictValue;

          })
          //当点击按钮时已经获取到了当前行的数据需要用到里面的id
          this.$axios.get("/doctor/patient/getByFileId/"+row.patientId).then(result=>{
            this.patientAllObj.patientFileObj=result.data.t;

            if (!this.patientAllObj.patientFile){
              //检测一下patientFile里有没有值没有的话会报错所以得赋值
              // 因为调用了里面的emergencyContactName
              this.patientAllObj.patientFile={};
            }
          })
          //查询患者病历里面的数据
          //当点击按钮时已经获取到了当前行的数据需要用到里面的id
          this.$axios.get("/doctor/patient/CareHistoryByIdAll/"+row.patientId).then(result=> {
            this.patientAllObj.careHistoryObjList=result.data.t;
          })


          //关闭弹框
          this.openRegistration=false;

        }else{
          this.$message({
            showClose: true,
            message: '接诊失败',
            type: 'error'
          });
        }

      })


    },
    //左边患者弹出层里的就诊完成 的载入按钮
    handleLoading(row){
      //根据患者id查询患者的信息赋值到左边的表单里
      this.$axios.post("/doctor/patient/queryByPatient/"+row.patientId).then(result=>{
        //赋值之前先清空上一个患者的数据
        this.patientAllObj.patientObj={};
        this.patientAllObj.patientFileObj={};
        this.patientAllObj.careHistoryObjList=[];

        //把患者数据赋值到左边的表单里
        this.patientAllObj.patientObj=result.data.t;
        this.careHistory.regId = row.registrationId;
        //点击载入按钮 接诊类型直接赋值为0 初诊
        this.careHistory.isContagious=this.receiveTypeOptions[0].dictValue;
      })
      //当点击按钮时已经获取到了当前行的数据需要用到里面的id
      this.$axios.get("/doctor/patient/getByFileId/"+row.patientId).then(result=>{
        this.patientAllObj.patientFileObj=result.data.t;

        if (!this.patientAllObj.patientFile){
          //检测一下patientFile里有没有值没有的话会报错所以得赋值
          // 因为调用了里面的emergencyContactName
          this.patientAllObj.patientFile={};
        }
      })
      //查询患者病历里面的数据
      //当点击按钮时已经获取到了当前行的数据需要用到里面的id
      this.$axios.get("/doctor/patient/CareHistoryByIdAll/"+row.patientId).then(result=> {
        this.patientAllObj.careHistoryObjList=result.data.t;
      })
      //给右边的病历表和处方表赋值
      // this.$axios.post("/doctor/newcare/queryCareHistoryId/"+row.patientId).then(result=> {
      //   this.careHistory=result.data.t;
      //   this.careOrders=this.careHistory.careOrderList;
      // })
      //给右边的病历表和处方表赋值 查询以挂号单查询病历 没有就返回空的病历
      this.$axios.post("/doctor/newcare/queryCareHistoryId/"+row.registrationId).then(result=> {
        if (JSON.stringify(result.data.t.chId)=="null"){
          this.careHistory=result.data.t;
          this.careOrders=this.careHistory.careOrderList;
        }
        this.careHistory=result.data.t;
        this.careOrders=this.careHistory.careOrderList;
      })

      //关闭弹框
      this.openRegistration=false;
    },

    //左边患者弹出层里面的待就诊表格绑定的方法
    // 挂号患者弹出层的选项卡change事件
    handleRegistrationTabClick(tab, event) {
      if (tab.name === 't1') {
        //查询待就诊的患者
        this.queryRegistrationNumber1();
      } else if (tab.name === 't2') {
        //查询就诊中的患者
        this.queryRegistrationNumber2();
      } else if (tab.name === 't3') {
        //查询就诊完成的患者
        this.queryRegistrationNumber3();
      }
    },

    // 把弹出层的表格的数据加上index
    tableRowClassName({ row, rowIndex }) {
      row.index = rowIndex
    },




    //右边处方表里面的添加药用处方按钮
    handelAddMedicinesOrder(){
      if (!this.careHistory.regId) {
        this.$message({
          showClose: true,
          message: '请选择患者',
          type: 'error'
        });
        return
      }
      if (!this.careHistory.chId) {
        this.$message({
          showClose: true,
          message: '请先保存病历',
          type: 'error'
        });
        return
      }
      this.submitCareOrder.careOrder.coType = '0'
      this.submitCareOrder.careOrder={
        allAmount: 0.00,
        patientId: undefined,
        patientName: undefined,
        coType: '0' // 默认为药用处方
      },
          this.title = '添加【药用】处方'
      this.openAddOrderItem = true
      this.submitCareOrder.careOrderItems = []
      //先清空表格里面的数据
      this.tableItemList=[];

    },
    //右边处方表里面的添加检查项目按钮
    handelAddCheckItemOrder(){
      if (!this.careHistory.regId) {
        this.$message({
          showClose: true,
          message: '请选择患者',
          type: 'error'
        });
        return
      }
      if (!this.careHistory.chId) {
        this.$message({
          showClose: true,
          message: '请先保存病历',
          type: 'error'
        });
        return
      }
      this.submitCareOrder.careOrder={
        allAmount: 0.00,
        patientId: undefined,
        patientName: undefined,
        coType: '0' // 默认为药用处方
      },
          this.submitCareOrder.careOrder.coType = '1'
      this.title = '添加【检查】处方'
      this.openAddOrderItem = true
      this.submitCareOrder.careOrderItems = []
      //先清空表格里面的数据
      this.tableItemList=[];

    },
    //药品和检查项目公用的添加按钮
    handleOpenAddOrderItemDrawer(){
      //如果为0的话 就是药品 不为0的话 就是检查
      if (this.submitCareOrder.careOrder.coType === '0') {

        //重置一下
        this.resetItemFormQuery();
        //查询药品的数据
        this.queryDrug();
        // 打开药口列表抽屉
        this.openDrawerMedicines = true;

      } else {
        //重置一下
        this.resetItemFormQuery();
        //查询检测项目数据
        this.queryItem();
        // 打开检查项目的抽屉
        this.openDrawerCheckItem = true;
      }
    },
    //药品表格多选框选中的内容
    handleMedicinesSelectionChange(val){
      if (val.length>0){
        //选中药品表格里面的数据直接赋值给selectItemList
        this.selectItemList=val;
      }else {

        this.selectItemList=[];
      }

    },
    //检查表格多选框选中的内容
    handleCheckItemSelectionChange(val){
      if (val.length>0){
        //选中检查项目表格里面的数据直接赋值给selectItemList
        this.selectItemList=val;
      }else {
        this.selectItemList=[];
      }

    },

    // 药品 和 检查项目 确定添加按钮
    handleSaveOrderItem(){
      //检查开诊细表里面是否存储内容
      if (this.submitCareOrder.careOrderItems.length === 0) {
        this.$message({
          showClose: true,
          message: '请添加处方详情',
          type: 'error'
        });
        return
      }
      //药品中间表里面的患者id
      this.submitCareOrder.careOrder.patientId = this.careHistory.patientId;
      //药品中间表里面的患者的名字
      this.submitCareOrder.careOrder.patientName = this.careHistory.patientName;
      //关联的病历id
      this.submitCareOrder.careOrder.chId = this.careHistory.chId;
      console.log(this.submitCareOrder)
      this.loading = true;
      //储存药方跟药方的内容
      this.$axios.post("/doctor/patient/getCareOrders",this.submitCareOrder).then(result=>{
        if (result.data.t){
          this.$message({
            showClose: true,
            message: '添加处方成功',
            type: 'success'
          });
          this.$axios.post("/doctor/patient/queryCoId/"+result.data.t).then(result=>{
            this.careOrders=result.data.t;
            this.openDrawerMedicines=false;
            this.openDrawerCheckItem=false;
            this.openAddOrderItem = false
            this.loading=false;

          })
        }else {
          this.$message({
            showClose: true,
            message: '添加处方失败',
            type: 'error'
          });
        }
      })

    },

    //药品表格的查询按钮
    handleMedicinesFormQuery(){
      this.queryDrug();
    },
    //药品表格的重置按钮
    resetItemFormQuery(){
      this.queryItemFormParams = {
        pageNum: 1,
        pageSize: 10,
        keywords: undefined
      }
      if (this.submitCareOrder.careOrder.coType === '0') {
        this.queryDrug();
      } else {
        this.queryItem();
      }

    },

    //添加药品表格当前页数
    handleMedicinesCurrentChange(val){
      this.queryItemFormParams.pageNum=val;
      console.log("当前页面"+val)
      this.queryDrug();
    },
    //添加药品表格每页显示几条
    handleMedicinesSizeChange(val){
      this.queryItemFormParams.pageSize=val;
      console.log("每页"+val+"条")
      this.queryDrug();
    },


    //右边检查项目里面的查询按钮
    handleCheckItemFormQuery(){
      this.queryItem();
    },
    //右边检查项目里面的重置按钮
    resetItemFormQuery(){
      this.queryItemFormParams = {
        pageNum: 1,
        pageSize: 10,
        keywords: undefined
      }
      this.queryItem();
    },

    //药品跟检查项目公用的添加并关闭按钮
    hanldeAddCareItem(){
      //获取 coType 0就是药品 不是0就是检查项目
      const coType = this.submitCareOrder.careOrder.coType
      //判断这个数组的长度是0的话 就是没有添加数据
      if (this.selectItemList.length === 0) {
        if (coType==='0'){
          this.$message({
            showClose: true,
            message: '请选【药品】',
            type: 'error'
          });
          return
        }else {
          this.$message({
            showClose: true,
            message: '请选【检查项目】',
            type: 'error'
          });
          return
        }
      }

      if (coType === '0') { // 药品
        this.selectItemList.filter(item => {
          const obj = {
            itemRefId: item.medicinesId,
            itemName: item.medicinesName,
            itemType: coType,
            num: 1,
            price: item.prescriptionPrice,
            amount: 1 * item.prescriptionPrice,
            remark: '请按说明服用',
            stock:item.medicinesStockNum
          }
          let flag = false// 默认里面没有加
          this.submitCareOrder.careOrderItems.filter(i => {
            if (i.itemRefId === obj.itemRefId) {
              i.num = i.num + 1
              flag = true// 说明之前加过
            }
          })
          if (!flag) {
            this.submitCareOrder.careOrderItems.push(obj)
          }
          this.openDrawerMedicines = false
        })
      } else { // 检查项目
        this.selectItemList.filter(item => {
          const obj = {
            itemRefId: item.checkItemId,
            itemName: item.checkItemName,
            itemType: coType,
            num: 1,
            price: item.unitPrice,
            amount: 1 * item.unitPrice,
            remark: '按要求检查'
          }
          let flag = false// 默认里面没有加
          this.submitCareOrder.careOrderItems.filter(i => {
            if (i.itemRefId === obj.itemRefId) {
              i.num = i.num + 1
              flag = true// 说明之前加过
            }
          })
          if (!flag) {
            this.submitCareOrder.careOrderItems.push(obj)
          }
          this.openDrawerCheckItem = false
        })
      }
      // 计算总价
      this.computeOrderItemAllAmount()

    },
    // 删除弹出层里面的详情
    handleCareOrderItemDelete(row) {
      this.submitCareOrder.careOrderItems.splice(row.index, 1)
      this.computeOrderItemAllAmount()
    },
    // 根据详情ID删除数据库里面的详情【只能删除未支付的】
    DeleteByItemIdhandleCareOrderItem(row) {

      const itemId=row.itemId
      const itemName=row.itemName;
      const amount=row.amount;
      this.$confirm('是否确定删除【' + itemName + '】这条详情, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get("/doctor/patient/deleteCareOrderItemById/"+itemId+"/"+amount).then(result=> {

          if (result.data.t==true){
            //给右边的处方表赋值
            this.$axios.post("/doctor/newcare/queryCareHistoryId/"+this.careHistory.patientId).then(result=> {
              alert(JSON.stringify(result.data.t))
              this.careOrders=result.data.t.careOrderList;

            })
            this.$message({
              showClose: true,
              message: '删除成功!',
              type: 'success'
            });
          }else {
            this.$message({
              showClose: true,
              message: '删除失败',
              type: 'error'
            });
            //给右边的处方表赋值
            this.$axios.post("/doctor/newcare/queryCareHistoryId/"+this.careHistory.patientId).then(result=> {
              this.careOrders=result.data.t.careOrderList;
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });

    },
    // 把弹出层的表格的数据加上index
    tableRowClassName({ row, rowIndex }) {
      row.index = rowIndex
    },
    // 计算当前处方详情的总价
    computeOrderItemAllAmount() {
      this.submitCareOrder.careOrder.allAmount = 0.00
      this.submitCareOrder.careOrderItems.filter(item => {
        this.submitCareOrder.careOrder.allAmount +=
            (item.num * item.price)
      })
    },
    // 监听药品或检查项目弹出层的数量的变化
    handleCareOrderItemNumChange(row) {
      row.amount = row.num * row.price
      this.computeOrderItemAllAmount()
    },
    //药品的查询按钮
    handleCheckItemFormQuery1(){

    },
    //检查项目的重置按钮
    resetItemFormQuery1(){
      this.queryItemFormParams = {
        pageNum: 1,
        pageSize: 10,
        keywords: undefined
      }
      this.queryDrug();
    },



    //保存病历按钮
    handleSaveCareHistory(){
      this.careHistory.patientId=this.patientAllObj.patientObj.patientId;
      this.careHistory.patientName=this.patientAllObj.patientObj.name;
      this.$axios.post("/doctor/newcare/insertCareHistory",this.careHistory).then(result=> {
        if (result.data.t.length>10){
          this.$message({
            showClose: true,
            message: '获取病历编号成功',
            type: 'success'
          });
          //把病历id赋值给上面的病历id
          this.careHistory.chId=result.data.t;
        }else if (result.data.t=="添加成功"){
          this.$message({
            showClose: true,
            message: '添加成功',
            type: 'success'
          });
        }else if (result.data.t=="添加失败"){
          this.$message({
            showClose: true,
            message: '添加失败',
            type: 'error'
          });
        }else if (result.data.t=="修改成功"){
          this.$message({
            showClose: true,
            message: '修改成功',
            type: 'success'
          });
        }else if (result.data.t=="修改失败"){
          this.$message({
            showClose: true,
            message: '修改失败',
            type: 'error'
          });
        }else {
          this.$message({
            showClose: true,
            message: '保存病历失败',
            type: 'success'
          });
        }

      })
    },
    //完成就诊按钮
    handleVisitComplete(){
      if (!this.careHistory.chId){
        this.$message({
          showClose: true,
          message: '请获取病历号',
          type: 'error'
        });
        return;
      }
      this.$confirm('确认就诊完成?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/doctor/newcare/queryCareHistoryById/"+this.careHistory.chId).then(result=>{
          if (result.data.t==true){
            this.$axios.get("/doctor/registered/updRegistrationId/"+this.careHistory.regId+"/3").then(result=>{
              if (result.data.t){
                this.$message({
                  showClose: true,
                  message: '就诊完成成功',
                  type: 'success'
                });
                //清空一下数据
                this.resetAllData();
              }else{
                this.$message({
                  showClose: true,
                  message: '就诊完成失败',
                  type: 'error'
                });
              }

            })
          }else {
            this.$message({
              showClose: true,
              message: '请保存病历',
              type: 'error'
            });
          }

        })

      }).catch(() => {
        this.$message({
          showClose: true,
          type: 'info',
          message: '已取消删除'
        });
      });

    },
    // 重置所有数据
    resetAllData() {
      this.patientAllObj = {
        patientObj: {},
        patientFileObj: {},
        careHistoryObjList: []
      }
      this.careHistory = {
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
      }
      this.careOrders = []
      this.submitCareOrder = {
        careOrder: {
          allAmount: 0.00,
          patientId: undefined,
          patientName: undefined,
          coType: '0' // 默认为药用处方
        },
        careOrderItems: []
      }
    },
    //查询药品表格
    queryDrug(){
      this.drawerLoading=true;
      this.$axios.post("/doctor/newcare/getStockMedicines",this.queryItemFormParams).then(result=> {
        //先清空表格里面的数据
        this.tableItemList=[];
        //给添加药品表格赋值
        this.tableItemList=result.data.t.records;
        this.total=result.data.t.total
      })
      this.drawerLoading=false;
    },
    //检查项目表格里的数据查询
    queryItem(){
      this.drawerLoading=true;
      this.$axios.post("/doctor/newcare/getCheckItem",this.queryItemFormParams).then(result=> {

        //先清空表格里面的数据
        this.tableItemList=[];
        //给添加药品表格赋值
        this.tableItemList=result.data.t.records;
        this.total=result.data.t.total
      })
      this.drawerLoading=false;
    },


    //分页插件没页几条
    handleCheckItemSizeChange(val){

    },
    //分页插件当前页
    handleCheckItemCurrentChange(val){

    },






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
