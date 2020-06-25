<template>
  <div class="className">
    <el-card class="anoCard">
      <div slot="header">
        <span>商品上架和下架操作</span>
      </div>
      <div class="searchDiv">
        <el-input
          type="text"
          placeholder="请输入商品ID"
          class="width1"
          v-model="sch_order"
        ></el-input>
        <el-select
          v-model="sch_status"
          clearable
          class="width1"
          placeholde="请选择状态"
        >
          <el-option
            v-for="item in options"
            :label="item.label"
            :value="item.value"
            :key="item.value"
          ></el-option>
        </el-select>
        <el-date-picker
          class="width1"
          v-model="sch_date"
          type="date"
          placeholder="选择上架日期时间"
          value-format="yyyy-MM-dd"
        ></el-date-picker>
        <el-button type="primary" icon="el-icon-search" @click="searchTab()"
          >搜索</el-button
        >
        <el-button
          type="primary"
          icon="el-icon-circle-plus-outline"
          @click="addTab"
          >上架</el-button
        >
      </div>
      <el-table :data="tableData" border stripe>
        <el-table-column prop="goodsId" label="商品ID" width="70"></el-table-column>
        <el-table-column
                prop="goodsName"
                label="商品名称"
                width="110"
        ></el-table-column>
        <el-table-column
                prop="goodsUrl"
                label="商品图片"
                width="180"
        ></el-table-column>
        <el-table-column prop="number" label="上架数量" width="80"></el-table-column>
        <el-table-column prop="timeOfGrounding" label="上架时间"></el-table-column>
        <!--el-table-column-- prop="dtime" label="下架时间"></el-table-column-->
        <el-table-column prop="priceMoney" label="消耗金钱"></el-table-column>
        <el-table-column prop="priceScore" label="消耗积分"></el-table-column>
        <!--el-table-column label="状态" width="90">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status | tagClass">{{
              scope.row.status | statusText
            }}</el-tag>
          </template>
        </el-table-column-->
        <el-table-column label="操作" width="300">
          <template slot-scope="scope">
            <el-button
              type="primary"
              @click="editTable(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              type="warning"
              @click="toConfirm(scope.row)"
              :disabled="scope.row.status === 1 ? false : true"
              >上架</el-button
            >
            <!--<el-button
              type="success"
              @click="toSuccess(scope.row)"
              :disabled="scope.row.status === 2 ? false : true"
              >完成</el-button
            >-->
            <el-button
              type="danger"
              @click="toDelete(scope.row)"
              :disabled="scope.row.status !== 3 ? false : true"
              >下架</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        layout="total, sizes, prev, pager, next"
        :page-sizes="pageSizes"
        :page-size="pageSize"
        :current-page="currentPage"
        :total="total"
        class="fyDiv"
        @size-change="handleSize"
        @current-change="handlePage"
      >
      </el-pagination>
    </el-card>
    <el-dialog title="商品管理" :visible.sync="diaIsShow" class="diaForm">
      <el-form
        ref="diaForm"
        :model="formData"
        :rules="rules"
        label-width="140px"
        enctype="multipart/form-data"
        method="post"
      >
        <el-form-item label="商品ID">
          <el-input
            type="text"
            v-model="formData.goodsId"
            :disabled="true"
          ></el-input>
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input
                  type="text"
                  placeholder="请输入名称"
                  v-model="formData.goodsName"
          ></el-input>
        </el-form-item>
        <el-form-item label="商品图片" prop="goodsUrl">
                <el-input
                        type="text"
                        placeholder="点击上传图片"
                        v-model="formData.goodsUrl"
                        :disabled="true"
                ></el-input>
        </el-form-item>
        <!--el-form-item label="上传图片" ref="uploadElement">
          <el-upload
                  class="avatar-uploader"
                  action="string"
                  multiple
                  :show-file-list="false"
                  :on-change="handleAvatarSuccess"
                  :before-upload="beforeAvatarUpload"
                  prop="name">
            <img v-if="formData.name" :src="formData.name" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item-->
        <el-form-item label="上架数量" prop="number">
          <el-input
                  type="text"
                  placeholder="请输入上架数量"
                  v-model="formData.number"
          ></el-input>
        </el-form-item>
        <el-form-item label="上架时间" prop="timeOfGrounding">
          <el-date-picker
            v-model="formData.timeOfGrounding"
            type="datetime"
            placeholder="选择日期时间"
            value-format="yyyy-MM-dd HH:mm:ss"
          ></el-date-picker>
        </el-form-item>
        <!--el-form-item label="下架时间" prop="dtime">
          <el-date-picker
                  v-model="formData.dtime"
                  type="datetime"
                  placeholder="选择日期时间"
                  value-format="yyyy-MM-dd HH:mm:ss"
          ></el-date-picker>
        </el-form-item-->
        <el-form-item label="消耗金钱" prop="priceMoney">
          <el-input
            type="text"
            placeholder="请输入金额"
            v-model="formData.priceMoney"
          ></el-input>
        </el-form-item>
        <el-form-item label="消耗积分" prop="priceScore">
          <el-input
                  type="text"
                  placeholder="请输入积分数"
                  v-model="formData.priceScore"
          ></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="formData.status" placeholde="请选择状态">
            <el-option
              v-for="item in options"
              :label="item.label"
              :value="item.value"
              :key="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="changeTab('diaForm', editType)"
            >确认</el-button
          >
          <el-button @click="diaIsShow = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { getPageTab2,addGoods } from '@/api/table'
import { getStore } from '@/vendor/storage'
import axios from 'axios'
import YButton from '@/components/YButton'
export default {
  data() {
    return {
      tableData: [],
      allList: [],
      schArr: [],
      sch_order: '',
      sch_status: null,
      sch_date: null,
      currentPage: 1,
      pageSize: 10,
      total: 0,
      pageSizes: [10, 20, 30, 40],
      diaIsShow: false,
      formData: {},
      editType: '',
      options: [
        //{ label: '待审核', value: 1 },
        { label: '销售中', value: 2 },
        //{ label: '已完成', value: 0 },
        { label: '已下架', value: 3 }
      ],
      rowIndex: 0,
      rules: {
        // order: [{ required: true, message: '请输入订单号', trigger: 'blur' }],
        goodsName: [{ required: true, message: '请输入商品名称', trigger: 'blur' }],
        //name: [{ required: true, message: '请输入图片网址', trigger: 'blur' }],
        number: [{ required: true, message: '请输入上架数量', trigger: 'blur' }],
        timeOfGrounding: [
          {
            // type: 'datetime',
            required: true,
            message: '请输入上架时间',
            trigger: 'change'
          }
        ],
        /*dtime: [
          {
            // type: 'datetime',
            required: true,
            message: '请输入下架时间',
            trigger: 'change'
          }
        ],*/
        priceMoney: [{ required: true, message: '请输入消耗金钱', trigger: 'blur' }],
        priceScore: [{required: true, message: '请输入消耗积分', trigger: 'blur'}],
        status: [
          { required: true, message: '请选择商品状态', trigger: 'change' }
        ],
      }
    }
  },
  created() {
    this._getPageTab2()
  },
  filters: {
    statusText(val) {
      if (val === undefined) return
      if (val === 0) {
        return '已完成'
      } else if (val === 1) {
        return '待审核'
      } else if (val === 2) {
        return '销售中'
      } else {
        return '已下架'
      }
    },
    tagClass(val) {
      if (val === undefined) return
      if (val === 0) {
        return 'success'
      } else if (val === 1) {
        return 'info'
      } else if (val === 2) {
        return 'warning'
      } else {
        return 'danger'
      }
    }
  },
  methods: {
    refreshView () {
      this.showView = false // 通过v-if移除router-view节点
      this.$nextTick(() => {
        this.showView = true // DOM更新后再通过v-if添加router-view节点
      })
    },
    handleSize(val) {
      this.pageSize = val
      this.getPageData()
    },
    handlePage(val) {
      this.currentPage = val
      this.getPageData()
    },
    _getPageTab2() {
      let params = {businessId:parseInt(localStorage.getItem('name'))}
      getPageTab2(params)
        .then(res => {
          this.allList = res.response
          localStorage.setItem('goodsLen',res.response.length)
          this.schArr = this.allList
          this.getPageData()
          this.total = res.response.length
        })
        .catch(error => {
          this.$message.error(error.message)
        })
    },
    getPageData() {
      let start = (this.currentPage - 1) * this.pageSize
      let end = start + this.pageSize
      this.tableData = this.schArr.slice(start, end)
    },
    // 查找
    searchTab() {
      let arrList = []
      for (let item of this.allList) {
        if (
          this.sch_order.trim() === '' &&
          this.sch_status === null &&
          this.sch_date === null
        ) {
          arrList = this.allList
          break
        } else if (
          item.goodsId.startsWith(this.sch_order) &&
          (this.sch_status !== null ? item.status === this.sch_status : true) &&
          (this.sch_date !== null ? item.timeOfGrounding.startsWith(this.sch_date) : true)
        ) {
          let obj = Object.assign({}, item)
          arrList.push(obj)
        }
      }
      this.schArr = arrList
      this.total = arrList.length
      this.currentPage = 1
      this.pageSize = 10
      this.getPageData()
    },
    // add
    addTab() {
      this.formData = {}
      this.diaIsShow = true
      this.formData.goodsId = localStorage.getItem('goodsLen')+1
      this.formData.goodsUrl = localStorage.getItem('picture')
      //this.formData.id = this.allList.length + 1
      this.editType = 'add'
      this.$nextTick(() => {
        this.$refs.diaForm.clearValidate()
      })
    },
    // 审核
    toConfirm(row) {
      row.status = 2
      this.$notify({
        title: '成功',
        message: '上架提交成功',
        type: 'success'
      })
    },
    // 完成
    toSuccess(row) {
      row.status = 0
      this.$notify({
        title: '成功',
        message: '该订单已完成配送',
        type: 'success'
      })
    },
    // 取消
    toDelete(row) {
      row.status = 3
      const fd = new FormData();
      fd.append("goodsId", row.goodsId);
      axios.post(`http://localhost:9000/deleteGoods`,fd)
              .then(res=>{
                this.$notify({
                  title: '成功',
                  message: '已下架该商品',
                  type: 'success'
                })
                console.log('res=>',res);
              })
    },
    // 编辑
    editTable(index, row) {
      this.formData = Object.assign({}, row)
      this.editType = 'update'
      this.diaIsShow = true
      this.$nextTick(() => {
        this.$refs.diaForm.clearValidate()
      })
      this.rowIndex = index
    },
    loginSubmit(){
      this.diaIsShow = false

    },
    handleChange (file, fileList) {
      //let file2 = new window.File([file],file,{type:'image/*'})
      //this.formData.name = URL.createObjectURL(file.raw);
      this.formData.name=file
    },
    beforeUpload(file) {
      return true;
    },
    tirggerFile (event) {
      let self = this;
      let file = event.target.files[0]
      let param = new FormData() // 创建form对象
      param.append('file', file, file.name) // 通过append向form对象添加数据
      //param.append('type', '1') // 添加form表单中其他数据
      console.log(param.get('file')) // FormData私有类对象，访问不到，可以通过get判断值是否传进去
      /*let config = {
        headers: {'Content-Type': 'multipart/form-data'}
      }
      // 添加请求头
      self.$openapi.$axios.post('upload/upImg', param, config)
              .then(response => {
                if (response.data.status == 200) {
                  self.form.img = response.data.data.img;
                  self.form.imgURL  ='http://www.baidu.com/'+response.data.data.img;
                }
              })*/
      this.formData.name = param.get('file')
    },
    handleAvatarSuccess(file,fileList) {
      //this.formData.name = URL.createObjectURL(file.raw);
      this.formData.name=file.raw
      console.log(file.raw)
      let picture = { file: file.raw}
      addGoods(picture)
              .then(res => {
                //resolve(res)
                console.log(res)
              })
              .catch(error => {
                console.log(error)
              })
    },
    beforeAvatarUpload(file) {
      /*const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;*/
      return true
    },
    changeTab(form, type) {
      this.$refs[form].validate(valid => {
        if (valid) {
          if (type === 'update') {
            // 改变整个表格数据
            let start = (this.currentPage - 1) * this.pageSize
            this.allList[start + this.rowIndex] = Object.assign(
              {},
              this.formData
            )
            // 解决数组不能通过索引响应数据变化
            this.$set(
              this.tableData,
              this.rowIndex,
              Object.assign({}, this.formData)
            )
            this.$notify({
              title: '成功',
              message: '商品信息已修改成功',
              type: 'success'
            })
          } else {
            //this.formData.name = getStore('picUrl')
            this.tableData.unshift(Object.assign({}, this.formData))
            this.allList.push(Object.assign({}, this.formData))
            const fd = new FormData();
            fd.append("goodsUrl", this.formData.goodsUrl);
            fd.append("goodsName", this.formData.goodsName);
            fd.append("priceMoney", this.formData.priceMoney);
            fd.append("priceScore", this.formData.priceScore);
            fd.append("businessId", parseInt(localStorage.getItem('name')));
            fd.append("number", this.formData.number);
            axios.post(`http://localhost:9000/addGoods`,fd)
                    .then(res=>{
                      this.$notify({
                        title: '成功',
                        message: '已上架该商品',
                        type: 'success'
                      })
                      console.log('res=>',res);
                    })
            /*addGoods(fd)
                    .then(res => {
                      console.log(res)
                    })
                    .catch(error => {
                      console.log(error)
                    })*/
          }
          this.diaIsShow = false
        } else {
          return
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.fyDiv {
  float: right;
  margin-top: 30px;
  padding-bottom: 20px;
}
.anoCard .el-table .el-button {
  padding: 8px 18px;
  font-size: 12px;
}
.searchDiv {
  margin-bottom: 20px;
  .el-button {
    padding: 11px 20px;
  }
}
.width1 {
  width: 180px;
  margin-right: 10px;
}
.diaForm {
  .el-input {
    width: 350px;
  }
}
</style>
<style lang="scss">
.anoCard {
  .el-card__body:after {
    content: '';
    clear: both;
    width: 0;
    height: 0;
    visibility: hidden;
    display: block;
  }
}
.diaForm .el-form-item__label {
  padding-right: 20px;
}
.searchDiv [class^='el-icon'] {
  color: #fff;
}
</style>
<style>
  input[type="file"] {
    display: none;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>