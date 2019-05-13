<template>
  <div class="app-container">
    
    <el-tabs tab-position="top" style="height: 200px;">
      <!--基本信息-->
      <el-tab-pane label="基本信息">
        <el-form :inline="true" ref="dataForm" :model="bdCustomerPo" label-position="left" label-width="150px">
          <el-form-item label="用户编号" prop="code">
            <el-input v-model="bdCustomerPo.code"></el-input>
          </el-form-item>
          <el-form-item label="联系人姓名" prop="name">
            <el-input v-model="bdCustomerPo.name"></el-input>
          </el-form-item>
          <el-form-item label="个人/公司名称" prop="corpname">
            <el-input v-model="bdCustomerPo.corpname"></el-input>
          </el-form-item>
          <el-form-item label="公司地址" prop="corpaddress">
            <el-input v-model="bdCustomerPo.corpaddress"></el-input>
          </el-form-item>
          <el-form-item label="登录账号" prop="tel">
            <el-input v-model="bdCustomerPo.tel"></el-input>
          </el-form-item>
          <el-form-item label="联系人电话" prop="tel">
            <el-input v-model="bdCustomerPo.tel"></el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <!--收货信息-->
      <el-tab-pane label="收货信息">
        <el-button class="filter-item" type="primary" @click="takeOverData.visible = true">录入</el-button>
        <hr/>
        <el-table :data="bdTakeoverPoList" style="width: 100%">
          <el-table-column prop="address" label="地址" width="180"></el-table-column>
          <el-table-column prop="takeoverPsn" label="收货人" width="180"></el-table-column>
          <el-table-column prop="takeoverTel" label="联系电话" width="180"></el-table-column>
          <el-table-column prop="defaultis" label="是否默认" width="180"></el-table-column>
          <el-table-column label="操作栏">
            <template slot-scope="scope">
              <el-button @click="editTaskOver(scope.row)">修改</el-button>
              <el-button @click="saveTakeOver('del', scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <!--开票信息-->
      <el-tab-pane label="开票信息">
        <el-tag>开票信息</el-tag>
        <hr/>
        <el-form :inline="true" ref="dataForm" :model="bdInvoicePo" label-position="left" label-width="150px">
          <el-form-item label="开票公司名称" prop="corpname">
            <el-input v-model="bdInvoicePo.corpname"></el-input>
          </el-form-item>
          <el-form-item label="税号" prop="dutynum">
            <el-input v-model="bdInvoicePo.dutynum"></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="invoiceAddress">
            <el-input v-model="bdInvoicePo.invoiceAddress"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="invoiceTel">
            <el-input v-model="bdInvoicePo.invoiceTel"></el-input>
          </el-form-item>
          <el-form-item label="开户行" prop="invoiceBank">
            <el-input v-model="bdInvoicePo.invoiceBank"></el-input>
          </el-form-item>
          <el-form-item label="账号" prop="invoiceAccount">
            <el-input v-model="bdInvoicePo.invoiceAccount"></el-input>
          </el-form-item>
        </el-form>
        
        <br/>
        <el-tag>发票收件地址信息</el-tag>
        <hr/>
        <el-form :inline="true" ref="dataForm" :model="bdInvoicePo" label-position="left" label-width="150px">
          <el-form-item label="发票收件人" prop="addressee">
            <el-input v-model="bdInvoicePo.addressee"></el-input>
          </el-form-item>
          <el-form-item label="发票收件人电话" prop="addresseeTel">
            <el-input v-model="bdInvoicePo.addresseeTel"></el-input>
          </el-form-item>
          <el-form-item label="发票收件人地址" prop="addresseeAdrs">
            <el-input v-model="bdInvoicePo.addresseeAdrs"></el-input>
          </el-form-item>
        </el-form>

        <el-button type="primary" style="float: right;margin-right: 200px;" @click="saveInvoice()">保存</el-button>
      </el-tab-pane>
      <!--联系人-->
      <el-tab-pane label="联系人">
        <el-row>
          <el-col :span="12">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>采购联系人管理</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="customdocData.visible=true">录入</el-button>
              </div>
              <div class="text item">
                <el-table :data="bdCustomdocPoList1" style="width: 100%">
                  <el-table-column prop="customname" label="采购联系人姓名" width="120"></el-table-column>
                  <el-table-column prop="telnum" label="采购联系电话" width="120"></el-table-column>
                  <el-table-column prop="sex" label="性别" width="100"></el-table-column>
                  <el-table-column prop="defaultis" label="是否默认" width="120"></el-table-column>
                  <el-table-column label="操作栏">
                    <template slot-scope="scope">
                      <el-button @click="editCustomdoc(scope.row)">修改</el-button>
                      <el-button @click="saveCustomdoc('del', scope.row)">删除</el-button>
                      <el-button @click="setDefaultCustomer(scope.row)">设为默认</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-card>
          </el-col>

          <el-col :span="12">
            <el-card class="box-card" style="margin-left: 50px;">
              <div slot="header" class="clearfix">
                <span>技术联系人管理</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="customdocData.visible=true">录入</el-button>
              </div>
              <div class="text item">
                <el-table :data="bdCustomdocPoList2" style="width: 100%">
                  <el-table-column prop="customname" label="技术联系人姓名" width="120"></el-table-column>
                  <el-table-column prop="telnum" label="技术联系电话" width="120"></el-table-column>
                  <el-table-column prop="sex" label="性别" width="60"></el-table-column>
                  <el-table-column prop="defaultis" label="是否默认" width="120"></el-table-column>
                  <el-table-column label="操作栏">
                    <template slot-scope="scope">
                      <el-button @click="editCustomdoc(scope.row)">修改</el-button>
                      <el-button @click="saveCustomdoc('del', scope.row)">删除</el-button>
                      <el-button @click="setDefaultCustomer(scope.row)">设为默认</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-card>
          </el-col>
        </el-row>

        <br/>
        <el-row>
          <el-col :span="11">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>其他人员</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="customdocData.visible=true">录入</el-button>
              </div>
              <div class="text item">
                <el-table :data="bdCustomdocPoList3" style="width: 100%">
                  <el-table-column prop="customname" label="人员姓名" width="120"></el-table-column>
                  <el-table-column prop="telnum" label="联系电话" width="120"></el-table-column>
                  <el-table-column prop="sex" label="性别" width="100"></el-table-column>
                  <el-table-column label="操作栏">
                    <template slot-scope="scope">
                      <el-button @click="editCustomdoc(scope.row)">修改</el-button>
                      <el-button @click="saveCustomdoc('del', scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>

    <!--收货信息Dialog-->
    <div v-if="takeOverData.visible">
      <el-dialog title="详细信息" :visible.sync="takeOverData.visible" width="1000px">
        <el-form :inline="true" ref="dataForm" :model="takeOverData.temp" label-position="left" label-width="200px">
          <el-form-item label="主键" prop="pkTakeover" v-if="false">
            <el-input v-model="takeOverData.temp.pkTakeover"></el-input>
          </el-form-item>
          <el-form-item label="客户主键" prop="pkCustomer" v-if="false">
            <el-input v-model="takeOverData.temp.pkCustomer"></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="takeOverData.temp.address"></el-input>
          </el-form-item>
          <el-form-item label="收货人" prop="takeoverPsn">
            <el-input v-model="takeOverData.temp.takeoverPsn"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="takeoverTel">
            <el-input v-model="takeOverData.temp.takeoverTel"></el-input>
          </el-form-item>
          <el-form-item label="是否默认" prop="defaultis">
            <el-input v-model="takeOverData.temp.defaultis"></el-input>
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="takeOverData.visible = false">关闭</el-button>
          <el-button type="primary" @click="saveTakeOver()">保存</el-button>
        </div>
      </el-dialog>
    </div>
    <!--客户人员信息Dialog-->
    <div v-if="customdocData.visible">
      <el-dialog title="详细信息" :visible.sync="customdocData.visible" width="1000px">
        <el-form :inline="true" ref="dataForm" :model="customdocData.temp" label-position="left" label-width="200px">
          <el-form-item label="主键" prop="pkCustomdoc" v-if="false">
            <el-input v-model="customdocData.temp.pkCustomdoc"></el-input>
          </el-form-item>
          <el-form-item label="客户主键" prop="pkCustomer" v-if="false">
            <el-input v-model="customdocData.temp.pkCustomer"></el-input>
          </el-form-item>
          <el-form-item label="人员姓名" prop="customname">
            <el-input v-model="customdocData.temp.customname"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="telnum">
            <el-input v-model="customdocData.temp.telnum"></el-input>
          </el-form-item>

          <el-form-item label="性别" prop="sex">
            <el-select v-model="customdocData.temp.sex" placeholder="请选择">
              <el-option
                v-for="item in sexOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="人员类别" prop="customtype">
            <el-select v-model="customdocData.temp.customtype" placeholder="请选择">
              <el-option
                v-for="item in customdocPsnTypeOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="customdocData.visible = false">关闭</el-button>
          <el-button type="primary" @click="saveCustomdoc()">保存</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import { parseTime, randomNum } from '@/utils/viewCompUtil'
import { selectCustomInfo, saveTakeOver, saveInvoice, saveCustomdoc } from '@/api/customer/customer'

export default {
  name: "custominfo",
  data() {
    return {
      takeOverData: {
        visible: false,
        temp: {
          pkTakeover: null,
          pkCustomer: null,
          address: '',
          takeoverPsn: null,
          takeoverTel: '',
          defaultis: '否',
          creator: null,
          creationtime: parseTime(new Date()),
          dr: 0,
          ts: parseTime(new Date()),
        }
      },
      customdocData: {
        visible: false,
        temp: {
          pkCustomdoc: null,
          pkCustomer: null,
          customname: '',
          telnum: null,
          sex: '',
          customtype: '',
          creator: null,
          creationtime: parseTime(new Date()),
          dr: 0,
          ts: parseTime(new Date())
        }
      },
      sexOption: [
        { value: '男', label: '男' },
        { value: '女', label: '女' }
      ],
      // 客户人员信息类别option
      customdocPsnTypeOption: [
        { value: '1', label: '采购联系人' },
        { value: '2', label: '技术联系人' },
        { value: '3', label: '其他人员' }
      ],
      // 页面数据集
      pkCustomer: null,
      bdCustomerPo: {},
      bdTakeoverPoList: [],
      bdInvoicePo: {},
      bdCustomdocPoList1: [], // 采购联系人
      bdCustomdocPoList2: [], // 技术联系人
      bdCustomdocPoList3: [] // 其他人员
    }
  },
  created() {
    this.initPageData()
  },
  methods: {
    initPageData() {
      let queryParam = {pkCustomer: 5}
      selectCustomInfo(queryParam).then((response) => {
        this.restViewData()
        if (response.object) {
          this.pkCustomer = response.object.pkCustomer
          this.bdCustomerPo = response.object.bdCustomerPo
          this.bdTakeoverPoList = response.object.bdTakeoverPoList
          this.bdInvoicePo = response.object.bdInvoicePo
          let customdocList = response.object.bdCustomdocPoList

          // 区分人员
          if (customdocList) {
            for (let index in customdocList) {
              let customOne = customdocList[index]
              if (customOne && '1' === customOne.customtype) {
                this.bdCustomdocPoList1.push(customOne)
              } else if (customOne && '2' === customOne.customtype) {
                this.bdCustomdocPoList2.push(customOne)
              } else {
                this.bdCustomdocPoList3.push(customOne)
              }
            }
          }

        }
      })
    },
    // 编辑收货信息
    editTaskOver(row) {
      this.takeOverData.temp = row
      this.takeOverData.visible = true
    },
    // 保存收货信息
    saveTakeOver(del, row) {
      if('del' === del) {
        row.dr = 1
        saveTakeOver(row).then((response) => {
          this.initPageData()
          this.takeOverData.visible = false
        })
      } else {
        this.takeOverData.temp.pkCustomer = this.pkCustomer
        saveTakeOver(this.takeOverData.temp).then((response) => {
          this.initPageData()
          this.takeOverData.visible = false
        })
      }
    },
    // 保存修改发票信息
    saveInvoice() {
      this.bdInvoicePo.creationtime = parseTime(new Date())
      this.bdInvoicePo.dr = 0
      this.bdInvoicePo.ts = parseTime(new Date())
      this.bdInvoicePo.pkCustomer = this.pkCustomer
      saveInvoice(this.bdInvoicePo).then((response) => {
        this.initPageData()
      })
    },
    // 编辑客户人员信息
    editCustomdoc(row) {
      this.customdocData.temp = row
      this.customdocData.visible = true
    },
    // 保存或修改客户人员信息
    saveCustomdoc(del, row) {
      if('del' === del) {
        row.dr = 1
        saveCustomdoc(row).then((response) => {
          this.initPageData()
          this.customdocData.visible = false
          this.restCustomdocTemp()
        })
      } else {
        this.customdocData.temp.pkCustomer = this.pkCustomer
        saveCustomdoc(this.customdocData.temp).then((response) => {
          this.initPageData()
          this.customdocData.visible = false
          this.restCustomdocTemp()
        })
      }
    },
    // 设置默认客户人员 
    setDefaultCustomer(row) {
      let defParam = {
        pkCustomdoc: row.pkCustomdoc, 
        pkCustomer: row.pkCustomer,
        customtype: row.customtype,
        defaultis: '是', 
        setdeflag: '是'}
      saveCustomdoc(defParam).then((response) => {
        this.initPageData()
      })
    },
    // 清空客户人员temp
    restCustomdocTemp() {
      this.customdocData.temp = {
        pkCustomdoc: null,
        pkCustomer: null,
        customname: '',
        telnum: null,
        sex: '',
        customtype: '',
        creator: null,
        creationtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      }
    },
    // 刷新界面数据
    restViewData() {
      this.bdCustomerPo = {}
      this.bdTakeoverPoList = []
      this.bdInvoicePo = {}
      this.bdCustomdocPoList1 = [] // 采购联系人
      this.bdCustomdocPoList2 = [] // 技术联系人
      this.bdCustomdocPoList3 = [] // 其他人员
    }
  }
}
</script>

<style>
  .center-div{
    padding: 20px 20px 20px 20px;
  }
</style>
