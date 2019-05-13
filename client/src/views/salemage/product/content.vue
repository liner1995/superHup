<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="80%">
    <el-form :inline="true" ref="dataForm" :model="temp" :rules="rules" label-position="left" label-width="120px">
      <!--基本信息-->
      <el-tabs type="border-card">
        <el-tab-pane label="基本信息">
          <el-form-item label="名称" prop="name">
            <el-input v-model="temp.name"></el-input>
          </el-form-item>
          <el-form-item label="编码" prop="code">
            <el-input v-model="temp.code" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="产品型号" prop="model">
            <el-input v-model="temp.model"></el-input>
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
            <el-select v-model="temp.pkCustomer" placeholder="请选择" @change="customerChange()">
              <el-option
                v-for="item in customerOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="采购联系人" prop="purchaseContacts">
            <el-select v-model="temp.purchaseContacts" placeholder="请选择">
              <el-option
                v-for="item in customdocOprion"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="技术联系人" prop="skillContacts">
            <el-select v-model="temp.skillContacts" placeholder="请选择">
              <el-option
                v-for="item in customdocOprion"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="负责人" prop="pkPsndoc">
            <el-select v-model="temp.pkPsndoc" placeholder="请选择">
              <el-option
                v-for="item in psndocOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="订单数量" prop="orderNumber">
            <el-input v-model="temp.orderNumber"></el-input> 
          </el-form-item>
        </el-tab-pane>
      </el-tabs>

      <!--加工工艺选项-->
      <br/>
      <el-tabs type="border-card">
        <el-tab-pane label="加工工艺选项">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="组装" prop="isinstal">
                <el-select v-model="temp.isinstal" placeholder="请选择">
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
                <el-input v-model="temp.instalMin" :disabled="temp.isinstal === '0' ? true : false"></el-input> 
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="测试" prop="istest">
                <el-select v-model="temp.istest" placeholder="请选择">
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
                <el-input v-model="temp.testMin" :disabled="temp.istest === '0' ? true : false"></el-input> 
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="老化" prop="isaging">
                <el-select v-model="temp.isaging" placeholder="请选择">
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
                <el-input v-model="temp.agingMin" :disabled="temp.isaging === '0' ? true : false"></el-input> 
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="三防" prop="defense">
                <el-select v-model="temp.defense" placeholder="请选择">
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
                <el-input v-model="temp.defenseArea" :disabled="temp.defense === '0' ? true : false"></el-input> 
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item label="焊接工艺" prop="sealTechnics">
            <el-select v-model="temp.sealTechnics" placeholder="请选择">
              <el-option
                v-for="item in sealTechnicsOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="物料来源" prop="materialSource">
            <el-select v-model="temp.materialSource" placeholder="请选择">
              <el-option
                v-for="item in materialSourceOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="执行标准" prop="exeStandard">
            <el-select v-model="temp.exeStandard" placeholder="请选择">
              <el-option
                v-for="item in exeStandardOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="钢网来源" prop="steelMeshSource">
            <el-select v-model="temp.steelMeshSource" placeholder="请选择">
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
        <el-switch v-model="temp.ispcb" active-value="是" inactive-value="否"></el-switch>
      </el-form-item>

      <!--PCB参数-->
      <el-tabs type="border-card" v-if="temp.ispcb === '是' ? true : false">
        <el-tab-pane label="PCB参数">

          <el-form-item label="板子厚度" prop="pcbThick">
            <el-select v-model="temp.pcbThick" placeholder="请选择">
              <el-option
                v-for="item in pcbThickOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="阻焊颜色" prop="pcbResistColor">
            <el-select v-model="temp.pcbResistColor" placeholder="请选择">
              <el-option
                v-for="item in pcbResistColorOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="字符颜色" prop="pcbCharColor">
            <el-select v-model="temp.pcbCharColor" placeholder="请选择">
              <el-option
                v-for="item in pcbCharColorOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="阻焊覆盖" prop="pcbResistCover">
            <el-select v-model="temp.pcbResistCover" placeholder="请选择">
              <el-option
                v-for="item in pcbResistCoverOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="焊盘喷镀" prop="pcbPadSpary">
            <el-select v-model="temp.pcbPadSpary" placeholder="请选择">
              <el-option
                v-for="item in pcbPadSparyOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="铜厚" prop="pcbCopperThick">
            <el-select v-model="temp.pcbCopperThick" placeholder="请选择">
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

      <!--制具参数-->
      <br/>
      <el-form-item label="制具(默认乙方提供)" prop="ismakeTool" label-width="200px">
        <el-switch v-model="temp.ismakeTool" active-value="是" inactive-value="否"></el-switch>
      </el-form-item>
      <el-tabs type="border-card" v-if="temp.ismakeTool === '是' ? true : false">
        <el-tab-pane label="制具参数">

          <el-form-item label="钢网制具" prop="makeSteelMesh">
            <el-select v-model="temp.makeSteelMesh" placeholder="请选择">
              <el-option
                v-for="item in steelMeshSourceOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="DIP制具" prop="makeDip">
            <el-select v-model="temp.makeDip" placeholder="请选择">
              <el-option
                v-for="item in steelMeshSourceOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="测试制具" prop="makeTest">
            <el-select v-model="temp.makeTest" placeholder="请选择">
              <el-option
                v-for="item in steelMeshSourceOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="三防制具" prop="makeDefense">
            <el-select v-model="temp.makeDefense" placeholder="请选择">
              <el-option
                v-for="item in steelMeshSourceOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="老化制具" prop="makeAging">
            <el-select v-model="temp.makeAging" placeholder="请选择">
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
          <el-col :span="8">
            <el-upload drag
              class="upload-demo"
              ref="BOMUpload"
              :action="uploadUrl"
              :file-list="BOMFileList"
              :limit="1"
              :on-exceed="handleExceed"
              :on-change="BOMFileChange"
              :auto-upload="false">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">BOM文件   <em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip">
                1: 上传文件大小不得超过200M
              </div>
            </el-upload>
          </el-col>

          <el-col :span="8">
            <el-upload drag
              class="upload-demo"
              ref="PCBUpload"
              :action="uploadUrl"
              :file-list="PCBFileList"
              :limit="1"
              :on-exceed="handleExceed"
              :on-change="PCBFileChange"
              :auto-upload="false">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">PCB文件   <em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip">
                1: pcb文件必须为.rar或是.zip格式压缩包文件。
                <br/>
                2: 上传文件大小不得超过200M
              </div>
            </el-upload>
          </el-col>

          <el-col :span="8">
            <el-upload drag
              class="upload-demo"
              ref="GONGYIUpload"
              :action="uploadUrl"
              :file-list="GONGYIFileList"
              :limit="1"
              :on-exceed="handleExceed"
              :on-change="GONGYIFileChange"
              :auto-upload="false">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">工艺文件   <em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip">
                1: 工艺文件必须为.rar或是.zip格式压缩包文件。
                <br/>
                2: 上传文件大小不得超过200M
              </div>
            </el-upload>
          </el-col>
        </el-tab-pane>
      </el-tabs>

    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取 消</el-button>
      <el-button type="primary" @click="sureDialog">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { mapGetters } from 'vuex'
import { initReplaceUpdateData, initReplaceAddData, parseTime, randomNum } from '@/utils/viewCompUtil'
import { saveProductFile } from '@/api/salemage/product'
import { listCustomerAsRef, selectCustomdocDefaultPsn, selectAllCustomdocAsRef } from '@/api/customer/customer'
import { listBdPsndocAsRef } from '@/api/orgs/bdpsndoc'

export default {
  name: 'Productcontent',
  props: ["replace", "title", "visiable", 'action', 'updateRow'],
  computed: {
    ...mapGetters([
      'listData', 'columns', 'name', 'userid'
    ])
  },
  data() {
    return {
      contentVisiable: false, // dialog 显示框
      uploadUrl: process.env.BASE_API + '/', // 上传地址
      BOMFileList: [],
      BOMFile: null,
      PCBFileList: [],
      PCBFile: null,
      GONGYIFileList: [],
      GONGYIFile: null,
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
      // 页面填写数据规则校验
      rules: {
        orderNumber: [
          {required: true, message: '订单数量不能为空', trigger: 'blur' }
        ],
        name: [
          {required: true, message: '产品名称不能为空', trigger: 'blur' }
        ]
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
      psndocOption: []
    }
  },
  watch: {
    visiable() {
      this.contentVisiable = this.visiable
      if (this.title === '录入') {
        this.restTemp()
        this.temp.creatorShow = this.name
      }
      if (this.title === '修改' && this.updateRow) {
        this.temp = this.updateRow
        this.temp.modifier = this.name
        this.customerChange()
        let BOMFileName = { name: this.updateRow.bomFilename }
        let PCBFileName = { name: this.updateRow.pcbFilename }
        let CRAFTFileName = { name: this.updateRow.craftFilename }
        this.BOMFileList = [ BOMFileName ]
        this.PCBFileList = [ PCBFileName ]
        this.GONGYIFileList = [ CRAFTFileName ]
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
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    BOMFileChange(file, fileList) { // 记录BOM文件
      this.BOMFile = file
    },
    PCBFileChange(file, fileList) { // 记录PCB文件
      this.PCBFile = file
    },
    GONGYIFileChange(file, fileList) { // 记录工艺文件
      this.GONGYIFile = file
    },
    closeDialog() { // 回调关闭Dialog
      this.$emit('close', null)
    },
    sureDialog() { // 确定保存回调Dialog
      let formData = new FormData()
      if (this.title === '录入' && (this.BOMFile == null || this.PCBFile == null || this.GONGYIFile == null)) {
        this.$message.warning('上传文件不完整!请选择需要上传的文件。')
        return
      } else {
        if (this.BOMFile) {
          formData.append(`bomfile`, this.BOMFile.raw)
        }
        if (this.PCBFile) {
          formData.append(`pcbfile`, this.PCBFile.raw)
        }
        if (this.GONGYIFile) {
          formData.append(`gongyifile`, this.GONGYIFile.raw)
        }
      }

      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          // 保存上传的文件
          saveProductFile(formData).then((response) => {
            if (response.object) {
              this.temp.bomFilename = response.object.BOM_FILE_NAME
              this.temp.bomFilefullname = response.object.BOM_FILE_FULL_NAME
              this.temp.pcbFilename = response.object.PCB_FILE_NAME
              this.temp.pcbFilefullname = response.object.PCB_FILE_FULL_NAME
              this.temp.craftFilename = response.object.CRAFT_FILE_NAME
              this.temp.craftFilefullname = response.object.CRAFT_FILE_FULL_NAME

              const actTemp = this.temp
              if (this.action) {
                initReplaceUpdateData(actTemp, this.replace, this.userid)
                this.action(actTemp).then((response) => {
                  if (response.primary) {
                    actTemp.pkProduct = response.primary
                  }
                  if (response.success) {
                    initReplaceAddData(actTemp, this.replace, this.name)
                    this.$store.commit('HANDLE_LISTVIEW_DATA', actTemp)
                    this.$message({ message: '操作成功', type: 'success' })
                    this.$emit('close', actTemp)
                    this.$emit('refreshList', null)
                  } else {
                    this.$message({ message: `操作失败:${response.message}`, type: 'error' })
                  }
                })
              }
            }
          })
        } else {
          this.$message.warning('表单验证未通过!')
          return false
        }
      })
    },
    customerChange() {
      let queryParam = { pkCustomer: this.temp.pkCustomer }
      selectAllCustomdocAsRef(queryParam).then((response) => {
        if (response.object) {
          this.customdocOprion = response.object
          
          selectCustomdocDefaultPsn(queryParam).then((response) => {
            if (response.object) {
              for(let index in response.object) {
                let custondocOne = response.object[index]
                if (custondocOne && custondocOne.customtype === '1') { // 采购联系人
                  this.temp.purchaseContacts = custondocOne.pkCustomdoc + ''
                }
                if (custondocOne && custondocOne.customtype === '2') { // 技术联系人
                  this.temp.skillContacts = custondocOne.pkCustomdoc + ''
                }
              }
            }
          })
        }
      })
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
      this.BOMFileList = []
      this.BOMFile = null
      this.PCBFileList = []
      this.PCBFile = null
      this.GONGYIFileList = []
      this.GONGYIFile = null
    }
  }
}
</script>

<style>

</style>
