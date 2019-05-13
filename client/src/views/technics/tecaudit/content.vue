<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="80%">
    <el-form :inline="true" ref="dataForm" :model="temp" label-position="left" label-width="120px">
      <!--基本信息-->
      <el-tabs type="border-card">
        <el-tab-pane label="基本信息">
          <el-form-item label="名称" prop="name">
            <el-input v-model="temp.name" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="编码" prop="code">
            <el-input v-model="temp.code" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="产品型号" prop="model">
            <el-input v-model="temp.model" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="创建人" prop="creatorShow">
            <el-input v-model="temp.creatorShow" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="创建时间" prop="creationtime">
            <el-input v-model="temp.creationtime" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="修改人" prop="modifierShow">
            <el-input v-model="temp.modifierShow" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="最后修改时间" prop="modifiedtime">
            <el-input v-model="temp.modifiedtime" :disabled="true"></el-input>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>

      <!--报价信息-->
      <br/>
      <el-tabs type="border-card">
        <el-tab-pane label="报价信息">

          <el-form-item label="客户" prop="pkCustomer">
            <el-select v-model="temp.pkCustomer" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in customerOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="采购联系人" prop="purchaseContacts" v-if="refshCustomDocOpflag">
            <el-select v-model="temp.purchaseContacts" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in customdocOprion"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="技术联系人" prop="skillContacts"  v-if="refshCustomDocOpflag">
            <el-select v-model="temp.skillContacts" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in customdocOprion"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="负责人" prop="pkPsndoc">
            <el-select v-model="temp.pkPsndoc" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in psndocOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="订单数量" prop="orderNumber">
            <el-input v-model="temp.orderNumber" :disabled="true"></el-input> 
          </el-form-item>
        </el-tab-pane>
      </el-tabs>

      <!--加工工艺选项-->
      <br/>
      <el-tabs type="border-card">
        <el-tab-pane label="加工工艺选项">
          <el-form-item label="SMT焊点" prop="smt">
            <el-input v-model="temp.smt" placeholder="SMT焊点"></el-input>
          </el-form-item>
          <el-form-item label="DIP焊点" prop="dip">
            <el-input v-model="temp.dip" placeholder="DIP焊点"></el-input>
          </el-form-item>

          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="组装" prop="isinstal">
                <el-select v-model="temp.isinstal" placeholder="请选择" :disabled="true">
                  <el-option
                    v-for="item in yesOrNoOption"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="组装时间" prop="instalMin">
                <el-input v-model="temp.instalMin" :disabled="true"></el-input> 
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="测试" prop="istest">
                <el-select v-model="temp.istest" placeholder="请选择" :disabled="true">
                  <el-option
                    v-for="item in yesOrNoOption"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="测试时间" prop="testMin">
                <el-input v-model="temp.testMin" :disabled="true"></el-input> 
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="老化" prop="isaging">
                <el-select v-model="temp.isaging" placeholder="请选择" :disabled="true">
                  <el-option
                    v-for="item in yesOrNoOption"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="老化时间" prop="agingMin">
                <el-input v-model="temp.agingMin" :disabled="true"></el-input> 
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="三防" prop="defense">
                <el-select v-model="temp.defense" placeholder="请选择" :disabled="true">
                  <el-option
                    v-for="item in yesOrNoOption"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            
            <el-col :span="8">
              <el-form-item label="三防面积" prop="defenseArea">
                <el-input v-model="temp.defenseArea" :disabled="true"></el-input> 
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item label="焊接工艺" prop="sealTechnics">
            <el-select v-model="temp.sealTechnics" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in sealTechnicsOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="物料来源" prop="materialSource">
            <el-select v-model="temp.materialSource" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in materialSourceOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="执行标准" prop="exeStandard">
            <el-select v-model="temp.exeStandard" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in exeStandardOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="钢网来源" prop="steelMeshSource">
            <el-select v-model="temp.steelMeshSource" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in steelMeshSourceOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

        </el-tab-pane>
      </el-tabs>

      <br/>
      <el-form-item label="PCB" prop="ispcb" label-width="200px">
        <el-switch v-model="temp.ispcb" active-value="是" inactive-value="否" :disabled="true"></el-switch>
      </el-form-item>

      <!--PCB参数-->
      <el-tabs type="border-card" v-if="temp.ispcb === '是' ? true : false">
        <el-tab-pane label="PCB参数">
          <el-form-item label="长（cm）" prop="pcbLong">
            <el-input v-model="temp.pcbLong" placeholder="单位：cm"></el-input>
          </el-form-item>
          <el-form-item label="宽（cm）" prop="pcbWide">
            <el-input v-model="temp.pcbWide" placeholder="单位：cm"></el-input>
          </el-form-item>

          <el-form-item label="板子厚度" prop="pcbThick">
            <el-select v-model="temp.pcbThick" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in pcbThickOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="阻焊颜色" prop="pcbResistColor">
            <el-select v-model="temp.pcbResistColor" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in pcbResistColorOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="字符颜色" prop="pcbCharColor">
            <el-select v-model="temp.pcbCharColor" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in pcbCharColorOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="阻焊覆盖" prop="pcbResistCover">
            <el-select v-model="temp.pcbResistCover" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in pcbResistCoverOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="焊盘喷镀" prop="pcbPadSpary">
            <el-select v-model="temp.pcbPadSpary" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in pcbPadSparyOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="铜厚" prop="pcbCopperThick">
            <el-select v-model="temp.pcbCopperThick" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in pcbCopperThick"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="单据状态" prop="billState">
            <el-select v-model="temp.billState" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in billStateOprion"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

        </el-tab-pane>
      </el-tabs>

      <br/>
      <el-form-item label="制具(默认乙方提供)" prop="ismakeTool" label-width="200px">
        <el-switch v-model="temp.ismakeTool" active-value="是" inactive-value="否" :disabled="true"></el-switch>
      </el-form-item>
      <el-tabs type="border-card" v-if="temp.ismakeTool === '是' ? true : false">
        <el-tab-pane label="制具参数">
          <el-form-item label="钢网制具" prop="makeSteelMesh">
            <el-select v-model="temp.makeSteelMesh" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in steelMeshSourceOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="DIP制具" prop="makeDip">
            <el-select v-model="temp.makeDip" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in steelMeshSourceOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="测试制具" prop="makeTest">
            <el-select v-model="temp.makeTest" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in steelMeshSourceOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="三防制具" prop="makeDefense">
            <el-select v-model="temp.makeDefense" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in steelMeshSourceOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="老化制具" prop="makeAging">
            <el-select v-model="temp.makeAging" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in steelMeshSourceOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

        </el-tab-pane>
      </el-tabs>

      <!--附件上传-->
      <br/>
      <el-tabs type="border-card">
        <el-tab-pane label="附件上传">
          <el-col :span="7">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>BOM文件</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="downloadFile(BOMFile.bomFilename, BOMFile.bomFilefullname)">下载BOM文件</el-button>
              </div>
              <div class="text item">
                {{ BOMFile.bomFilename }}
              </div>
            </el-card>
          </el-col>

          <el-col :span="7">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>PCB文件</span>
                <el-button style="float: right; padding: 3px 0" type="text">下载PCB文件</el-button>
              </div>
              <div class="text item">
                {{ PCBFile.pcbFilename }}
              </div>
            </el-card>
          </el-col>

          <el-col :span="7">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>工艺文件</span>
                <el-button style="float: right; padding: 3px 0" type="text">下载工艺文件</el-button>
              </div>
              <div class="text item">
                {{ GONGYIFile.craftFilename }}
              </div>
            </el-card>
          </el-col>
        </el-tab-pane>
      </el-tabs>

    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取 消</el-button>
      <el-button type="primary" @click="sureDialog">保 存</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { mapGetters } from 'vuex'
import { initReplaceUpdateData, initReplaceAddData, parseTime, randomNum } from '@/utils/viewCompUtil'
import { listCustomerAsRef, selectCustomdocDefaultPsn, selectAllCustomdocAsRef } from '@/api/customer/customer'
import { downloadProductFile } from '@/api/pub/pub'
import { listBdPsndocAsRef } from '@/api/orgs/bdpsndoc'

export default {
  name: 'Tecauditcontent',
  props: ["replace", "title", "visiable", 'action', 'updateRow'],
  computed: {
    ...mapGetters([
      'listData', 'columns'
    ])
  },
  data() {
    return {
      contentVisiable: false, // dialog 显示框
      uploadUrl: process.env.BASE_API + '/', // 上传地址
      BOMFile: {
        bomFilename: '',
        bomFilefullname: ''
      },
      PCBFile: {
        pcbFilename: '',
        pcbFilefullname: ''
      },
      GONGYIFile: {
        craftFilename: '',
        craftFilefullname: ''
      },
      temp: {
        pkProduct: undefined,
        name: '',
        code: 'CP' + randomNum(100*1000),
        model: '',
        pkPsndoc: null,
        purchaseContacts: null,
        skillContacts: null,
        smt: null,
        dip: null,
        isinstal: '0',
        instalMin: null,
        istest: '0',
        testMin: null,
        isaging: '0',
        agingMin: null,
        defense: '0',
        defenseArea: null,
        sealTechnics: '1',
        materialSource: '0',
        exeStandard: '0',
        steelMeshSource: '0',
        ispcb: '否',
        pcbLong: null,
        pcbWide: null,
        pcbThick: 1.6,
        pcbResistColor: '绿色',
        pcbCharColor: '白色',
        pcbResistCover: '0',
        pcbPadSpary: '0',
        pcbCopperThick: '0',
        pkCustomer: null,
        orderNumber: null,
        bomFilename: '',
        bomFilefullname: '',
        pcbFilename: '',
        pcbFilefullname: '',
        craftFilename: '',
        craftFilefullname: '',
        ismakeTool: '是',
        makeSteelMesh: '1',
        makeDip: '1',
        makeTest: '1',
        makeDefense: '1',
        makeAging: '1',
        billState: 0,
        creator: '',
        creatorShow: '',
        creationtime: parseTime(new Date()),
        modifier: '',
        modifierShow: '',
        modifiedtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      },
      yesOrNoOption: [
        { value: '0', label: '否' },
        { value: '1', label: '是' }
      ],
      sealTechnicsOption: [ // 焊接工艺
        { value: '0', label: '无铅' },
        { value: '1', label: '有铅' },
        { value: '2', label: 'RoSH' }
      ],
      materialSourceOption: [ // 物料来源
        { value: '0', label: '甲方提供' },
        { value: '1', label: '乙方提供' },
        { value: '2', label: '部分甲方提供' }
      ],
      exeStandardOption: [ // 执行标准
        { value: '0', label: '民用' },
        { value: '1', label: '医疗' },
        { value: '2', label: '军工' }
      ],
      steelMeshSourceOption: [ // 钢网来源
        { value: '0', label: '甲方提供' },
        { value: '1', label: '乙方提供' }
      ],
      pcbThickOption: [ // 板子厚度
        { value: 0.4, label: '0.4' },
        { value: 0.6, label: '0.6' },
        { value: 0.8, label: '0.8' },
        { value: 1.0, label: '1.0' },
        { value: 1.2, label: '1.2' },
        { value: 1.6, label: '1.6' },
        { value: 2.0, label: '2.0' }
      ],
      pcbResistColorOption: [ // 阻焊颜色
        { value: '绿色', label: '绿色' },
        { value: '红色', label: '红色' },
        { value: '黄色', label: '黄色' },
        { value: '蓝色', label: '蓝色' },
        { value: '白色', label: '白色' },
        { value: '黑色', label: '黑色' }
      ],
      pcbCharColorOption: [ // 字符颜色
        { value: '白色', label: '白色' }
      ],
      pcbResistCoverOption: [ // 阻焊覆盖
        { value: '0', label: '过孔盖油' },
        { value: '1', label: '过孔开窗' }
      ],
      pcbPadSparyOption: [ // 焊盘喷镀
        { value: '0', label: '有铅喷锡' },
        { value: '1', label: '无铅喷锡' },
        { value: '2', label: '沉金' }
      ],
      pcbCopperThick: [ // 铜厚
        { value: '0', label: '1盎司' },
        { value: '1', label: '2盎司' }
      ],
      billStateOprion: [
        { value: 0, label: '自由态' },
        { value: 1, label: '工艺审核态' },
        { value: 2, label: '工艺审核完成态' }
      ],
      // 客户信息参照
      customerOption: [],
      // 客户人员档案信息参照
      customdocOprion: [],
      // 人员信息参照
      psndocOption: [],
      //查询客户人员参照后重新刷新客户人员数据标志
      refshCustomDocOpflag: false
    }
  },
  watch: {
    visiable() {
      this.contentVisiable = this.visiable
      if (this.title === '详情' && this.updateRow) {
        this.restTemp()
        this.temp = this.updateRow
        this.BOMFile.bomFilename = this.updateRow.bomFilename
        this.BOMFile.bomFilefullname = this.updateRow.bomFilefullname
        this.PCBFile.pcbFilename = this.updateRow.pcbFilename
        this.PCBFile.pcbFilefullname = this.updateRow.pcbFilefullname
        this.GONGYIFile.craftFilename = this.updateRow.craftFilename
        this.GONGYIFile.craftFilefullname = this.updateRow.craftFilefullname

        // 初始客户人员参照信息
        let queryParam = { pkCustomer: this.temp.pkCustomer }
        selectAllCustomdocAsRef(queryParam).then((response) => {
          if (response.object) {
            this.customdocOprion = response.object
            this.refshCustomDocOpflag = true
          }
        })
      }
    }
  },
  created() {
    // 初始客户信息参照
    listCustomerAsRef().then((response) => {
      this.customerOption = response.object
    })
    // 初始人员信息参照
    listBdPsndocAsRef().then((response) => {
      this.psndocOption = response.object
    })
  },
  methods: {
    downloadFile(fileName, fileFullName) {
      if (fileName && fileFullName) {
        let downLoadParma = {fileName: fileName, fileFullName: fileFullName}
        downloadProductFile(downLoadParma).then((response) => {
          const blob = response.data
          const reader = new FileReader()
          reader.readAsDataURL(blob)
          reader.onload = (e) => {
            const a = document.createElement('a')
            if (fileName) {
              a.download = fileName
            } else {
              a.download = fileName
            }
            a.href = e.target.result
            document.body.appendChild(a)
            a.click()
            document.body.removeChild(a)
          }
        })
      }
    },
    closeDialog() { // 回调关闭Dialog
      this.$emit('close', null)
    },
    sureDialog() { // 确定保存回调Dialog
      const actTemp = this.temp
      if (this.action) {
        initReplaceUpdateData(actTemp, this.replace)
        this.action(actTemp).then((response) => {
          if (response.primary) {
            actTemp.pkProvince = response.primary
          }
          if (response.success) {
            initReplaceAddData(actTemp, this.replace)
            this.$store.commit('HANDLE_LISTVIEW_DATA', actTemp)
            this.$message({ message: '操作成功', type: 'success' })
            this.$emit('close', actTemp)
          } else {
            this.$message({ message: `操作失败:${response.message}`, type: 'error' })
          }
        })
      }
    },
    restTemp() { // 刷新本界面的数据
      this.temp = {
        pkProduct: undefined,
        name: '',
        code: 'CP' + randomNum(100*1000),
        model: '',
        pkPsndoc: null,
        purchaseContacts: null,
        skillContacts: null,
        smt: null,
        dip: null,
        isinstal: '0',
        instalMin: null,
        istest: '0',
        testMin: null,
        isaging: '0',
        agingMin: null,
        defense: '0',
        defenseArea: null,
        sealTechnics: '1',
        materialSource: '0',
        exeStandard: '0',
        steelMeshSource: '0',
        ispcb: '否',
        pcbLong: null,
        pcbWide: null,
        pcbThick: 1.6,
        pcbResistColor: '绿色',
        pcbCharColor: '白色',
        pcbResistCover: '0',
        pcbPadSpary: '0',
        pcbCopperThick: '0',
        pkCustomer: null,
        orderNumber: null,
        bomFilename: '',
        bomFilefullname: '',
        pcbFilename: '',
        pcbFilefullname: '',
        craftFilename: '',
        craftFilefullname: '',
        ismakeTool: '是',
        makeSteelMesh: '1',
        makeDip: '1',
        makeTest: '1',
        makeDefense: '1',
        makeAging: '1',
        billState: 0,
        creator: '',
        creatorShow: '',
        creationtime: parseTime(new Date()),
        modifier: '',
        modifierShow: '',
        modifiedtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      }

      this.BOMFile = { bomFilename: '', bomFilefullname: '' }
      this.PCBFile = { pcbFilename: '', pcbFilefullname: '' }
      this.GONGYIFile = { craftFilename: '', craftFilefullname: '' }
    }
  }
}
</script>

<style>

</style>
