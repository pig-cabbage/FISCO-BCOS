<template>
  <div class="dashbord">
    <!-- cardlist -->
    <el-row class="infoCrads">
      <el-col :span="6">
        <div class="cardItem">
          <div class="cardItem_txt">
            <count-to
                    class="cardItem_p0 color-green1"
                    :startVal="startVal"
                    :endVal="vistors"
                    :duration="2000"
            ></count-to>
            <p class="cardItem_p1">Bank ID</p>
          </div>
          <div class="cardItem_icon">
            <i class="el-icon-user color-green1"></i>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="cardItem">
          <div class="cardItem_txt">
            <count-to
                    class="cardItem_p0 color-blue"
                    :startVal="startVal"
                    :endVal="message"
                    :duration="2000"
            ></count-to>
            <p class="cardItem_p1">Bank Message</p>
          </div>
          <div class="cardItem_icon">
            <i class="el-icon-s-comment color-blue"></i>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="cardItem">
          <div class="cardItem_txt">
            <count-to
                    class="cardItem_p0 color-red"
                    :startVal="startVal"
                    :endVal="order"
                    :duration="2000"
            ></count-to>
            <p class="cardItem_p1">Bank Order Placed</p>
          </div>
          <div class="cardItem_icon">
            <i class="el-icon-shopping-cart-2 color-red"></i>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="cardItem">
          <div class="cardItem_txt">
            <count-to
                    class="cardItem_p0 color-green2"
                    :startVal="startVal"
                    :endVal="profit"
                    :duration="2000"
            ></count-to>
            <p class="cardItem_p1">Bank Money Balance</p>
          </div>
          <div class="cardItem_icon">
            <i class="el-icon-wallet color-green2"></i>
          </div>
        </div>
      </el-col>
    </el-row>
    <!-- end -->
    <!-- lineEcharts -->
    <!-- <line-charts class="lCharts" :lineChartData="lineChartData"></line-charts> -->
    <!-- end -->
    <!-- table and pie -->
    <el-row class="tableChart">
      <el-col :span="16">
        <table-show :btableData="tableData" class="tableShow"></table-show>
      </el-col>
      <!-- <el-col :span="8">
        <pie-charts class="pieCharts"></pie-charts>
      </el-col> -->
    </el-row>
    <!-- end -->
    <!-- bar chart -->
    <!-- <bar-charts class="barCharts" :barData="barData"></bar-charts> -->
    <!-- end -->
  </div>
</template>

<script>
  import CountTo from 'vue-count-to'
  //import LineCharts from './components/LineCharts'
  //import PieCharts from './components/PieCharts'
  import TableShow from './components/TableShow'
  import store from '@/store'
  //import BarCharts from './components/BarCharts'
  import {
    getBankData
    //getBTableData,
    //getLineData,
    //getBarData
  } from '@/api/dashboard'
  import { getAllBanks } from '@/api/roles'
  export default {
    data() {
      return {
        startVal: 0,
        vistors: 1234,
        message: 11,
        order: 13,
        profit: 998888,
        tableData: [
          {
            "businessId": "128",
            "score": 9725
          },
          {
            "businessId": "305881",
            "score": 300
          },
          {
            "businessId": "1",
            "score": 9928
          },
          {
            "businessId": "3058981",
            "score": 0
          },
          {
            "businessId": "30589811",
            "score": 0
          },
          {
            "businessId": "305898112",
            "score": 400
          },
          {
            "businessId": "123456",
            "score": 200
          },
          {
            "businessId": "12345",
            "score": 500
          },
          {
            "businessId": "688",
            "score": 500
          },
          {
            "businessId": "6888",
            "score": 500
          },
          {
            "businessId": "609",
            "score": 1401
          }
        ],
        //lineChartData: {},
        //barData: {}
      }
    },
    created() {
      this._getAllData()
    },
    components: {
      CountTo,
      //LineCharts,
      //PieCharts,
      TableShow,
      //BarCharts
    },
    methods: {
      _getAllData() {
        //let name = {businessId:parseInt(localStorage.getItem('name'))}
        this.$http
        /*.all([getBusinessData(), getBTableData()])
        .then(
                this.$http.spread((cardData, bTabData) => {
                  this.vistors = localStorage.getItem('name')
                  this.message = cardData*2.5
                  this.order = localStorage.getItem('length')
                  this.profit = cardData
                  //this.lineChartData = lineData.data
                  this.tableData = bTabData.data.tableList
                  //this.barData = barData.data
                })
        )*/
                .all([getBankData(),getAllBanks()])
                .then(
                        this.$http.spread((cardData,bTabData) => {
                          this.vistors = localStorage.getItem('name')
                          this.message = bTabData.response.length
                          this.order = cardData.response.length
                          console.log(bTabData,cardData)
                          this.profit = 998888
                          //this.lineChartData = lineData.data
                          //this.barData = barData.data
                        })
                )
      }
    }
  }
</script>
<style scoped lang="scss">
  $mgTop: 30px;
  @mixin shadow {
    box-shadow: 0 0 10px #e2e2e2;
  }
  .color-green1 {
    color: #40c9c6 !important;
  }
  .color-blue {
    color: #36a3f7 !important;
  }
  .color-red {
    color: #f4516c !important;
  }
  .color-green2 {
    color: #34bfa3 !important;
  }
  .dashbord {
    background-color: #f0f3f4;
  }
  .infoCrads {
    margin: 0 -20px 0 -20px;
    .el-col {
      padding: 0 20px;
      .cardItem {
        height: 108px;
        background: #fff;
      }
    }
  }
  .cardItem {
    color: #666;
    @include shadow();
    .cardItem_txt {
      float: left;
      margin: 26px 0 0 20px;
      .cardItem_p0 {
        font-size: 20px;
        font-weight: bold;
      }
      .cardItem_p1 {
        font-size: 16px;
      }
    }
    .cardItem_icon {
      float: right;
      margin: 24px 20px 0 0;
      i {
        font-size: 55px;
      }
    }
  }
  .lCharts {
    background: #fff;
    margin-top: $mgTop;
    padding: 30px 0;
    @include shadow();
  }
  .barCharts {
    background: #fff;
    margin-top: $mgTop;
    padding: 30px 0;
    @include shadow();
  }
  .pieCharts {
    background: #fff;
    padding: 20px 0;
    @include shadow();
  }
  .tableChart {
    margin-top: $mgTop;
  }
</style>
