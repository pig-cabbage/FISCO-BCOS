<template>
    <div>
        <el-table
                :data="btableData"
                border
                style="width: 100%;box-shadow: 0 0 10px #e2e2e2;"
        >
            <el-table-column prop="userId" label="用户ID#" width="180"></el-table-column>
            <el-table-column prop="dealId" label="订单ID"></el-table-column>
            <el-table-column prop="goodsId" label="货物ID"></el-table-column>
            <el-table-column prop="dealTime" label="交易时间"></el-table-column>
            <el-table-column prop="dealNum" label="交易数量"></el-table-column>
            <el-table-column prop="wayOfDeal" label="是否积分">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.wayOfDeal | tagClass">{{
                        scope.row.wayOfDeal | statusText
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
                if (val === undefined) return
                if (val === false) {
                    return '金钱'
                } else if (val === true) {
                    return '积分'
                } else {
                    return '金钱'
                }
            },
            tagClass(val) {
                if (val === undefined) return
                if (val === false) {
                    return 'success'
                } else if (val === true) {
                    return 'warning'
                } else {
                    return 'danger'
                }
            }
        }
    }
</script>
