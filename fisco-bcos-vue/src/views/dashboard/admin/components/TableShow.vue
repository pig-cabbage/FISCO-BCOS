<template>
  <div>
    <el-table
      :data="btableData"
      border
      style="width: 100%;box-shadow: 0 0 10px #e2e2e2;"
    >
      <el-table-column prop="businessId" label="企业ID#" width="180"></el-table-column>
      <!--el-table-column prop="name" label="积分授信ID"></el-table-column-->
      <!--el-table-column-- prop="price" label="企业金钱余额"></el-table-column-->
      <el-table-column prop="score" label="企业申请积分"></el-table-column>
      <el-table-column label="状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | tagClass">{{
            scope.row.status | statusText
          }}</el-tag>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  props: {
    btableData: {
      type: Array,
      required: true
    }
  },
  filters: {
    statusText(val) {
      if (val === undefined) return '已完成'
      if (val === 0) {
        return '已完成'
      } else if (val === 1) {
        return '进行中'
      } else {
        return '已取消'
      }
    },
    tagClass(val) {
      if (val === undefined) return 'success'
      if (val === 0) {
        return 'success'
      } else if (val === 1) {
        return 'warning'
      } else {
        return 'danger'
      }
    }
  }
}
</script>
