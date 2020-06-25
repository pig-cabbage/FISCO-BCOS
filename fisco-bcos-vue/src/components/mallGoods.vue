<template>
  <div class="good-item">
    <div style="">
      <div class="good-img">
        <img src=" msg.goodsUrl " :alt="msg.goodsName" :key="msg.goodsUrl">
      </div>
      <h6 class="good-title" v-html="msg.goodsName">{{msg.goodsName}}</h6>
      <h3 class="sub-title ellipsis">{{msg.businessId}}</h3>
      <div class="good-price pr">
        <div class="ds pa">
          <y-button text="积分购买" style="margin: 0 5px"
                    @btnClick="addPoint"></y-button>
          <y-button text="金钱购买"
                    style="margin: 0 5px"
                    @btnClick="addCart"
                    classStyle="main-btn"
          ></y-button>
        </div>
        <p><span style="font-size:14px">￥</span>{{Number(msg.priceScore).toFixed(2)}}</p>
      </div>
    </div>
  </div>
</template>
<script>
  import YButton from '@/components/YButton'
  import { addCartS , addPointS} from '@/api/goods.js'
  //import { mapMutations, mapState } from 'vuex'
  import { getStore } from '@/vendor/storage'

  export default {
    props: {
      msg: {
        salePrice: 0
      }
    },
    data () {
      return {}
    },
    methods: {
      addCart () {
        let params={
          userId:parseInt(localStorage.getItem('name')),
          goodsId:this.msg.goodsId,
          num:1
        }
        addCartS(params)
                .then(res => {
                  console.log(res)
                  console.log(params.goodsId,params.userId,params.num)
                  this.$notify({
                    title: '成功',
                    message: res.response,
                    type: 'success'
                  })
                })
                .catch(error => {
                  this.$message.error(error)
                })
      },
      addPoint () {
        let params={
          userId:parseInt(localStorage.getItem('name')),
          goodsId:this.msg.goodsId,
          num:1
        }
        addPointS(params)
                .then(res => {
                  console.log(res)
                  this.$notify({
                    title: '成功',
                    message: res.response,
                    type: 'success'
                  })
                })
                .catch(error => {
                  this.$message.error(error)
                })
      }
      //...mapMutations(['ADD_CART', 'ADD_ANIMATION', 'SHOW_CART']),
      /*goodsDetails (id) {
        //this.$router.push({path: 'goodsDetails/productId=' + id})
        this.$router.push({
            path:'/goodsDetails',
            query:{
                productId:id
            }
        })
      },
      openProduct (id) {
        window.open('//' + window.location.host + '/#/goodsDetails?productId=' + id)
      },*/
    },
    computed: {
      //...mapState(['login', 'showMoveImg', 'showCart'])
    },
    mounted () {
    },
    components: {
      YButton
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "../style/mixin";
  @import "../style/theme";

  .good-item {
    background: #fff;
    width: 25%;
    transition: all .5s;
    height: 430px;
    &:hover {
      transform: translateY(-3px);
      box-shadow: 1px 1px 20px #999;
      .good-price p {
        display: none;
      }
      .ds {
        display: flex;
      }
    }
    .ds {
      @extend %block-center;
      width: 100%;
      display: none;
    }

    .good-img {
      img {
        margin: 50px auto 10px;
        @include wh(206px);
        display: block;
      }
    }
    .good-price {
      margin: 15px 0;
      height: 30px;
      text-align: center;
      line-height: 30px;
      color: #d44d44;
      font-family: Arial;
      font-size: 18px;
      font-weight: 700;
    }
    .good-title {
      line-height: 1.2;
      font-size: 16px;
      color: #424242;
      margin: 0 auto;
      padding: 0 14px;
      text-align: center;
      overflow: hidden;
    }
    h3 {
      text-align: center;
      line-height: 1.2;
      font-size: 12px;
      color: #d0d0d0;
      padding: 10px;
    }

  }
</style>
