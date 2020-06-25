<template>
    <div class="rolesControl">
        <el-card>
            <el-button type="primary" @click="addRolesTab">积分操作</el-button>
            <el-table
                    class="mtop30"
                    :data="rolesTab"
                    stripe
                    border
                    style="width: 100%;"
            >
                <!--el-table-column prop="accept" label="处理与否"></el-table-column-->
                <el-table-column prop="sendId" label="发送方"></el-table-column>
                <el-table-column prop="getId" label="接收方"></el-table-column>
                <el-table-column prop="score" label="积分(正为充值，负为转赠)"></el-table-column>
                <!--el-table-column label="操作">
                  <template slot-scope="scope">
                    <el-button
                      type="primary"
                      :disabled="true"
                      >编辑</el-button
                    >
                    <el-button
                      type="warning"
                      :disabled="true"
                      >删除</el-button
                    >
                  </template>
                </el-table-column-->
                <!--el-table-column label="操作" width="90">
                  <template slot-scope="scope">
                    <el-tag :type="scope.row.pages[0] | tagClass">{{
                      scope.row.pages[0] | statusText
                      }}</el-tag>
                  </template>
                </el-table-column-->
            </el-table>
        </el-card>
        <el-dialog title="操作编辑" :visible.sync="diaIsShow" class="diaForm">
            <el-form
                    ref="rolesForm"
                    :model="formData"
                    :rules="rules"
                    label-width="140px"
            >
                <el-form-item label="对方ID" prop="getId">
                    <el-input
                            type="text"
                            placeholder="请输入要操作的对方ID"
                            v-model="formData.getId"
                    ></el-input>
                </el-form-item>
                <el-form-item label="积分/金钱" prop="score">
                    <el-input
                            type="text"
                            placeholder="请输入操作积分数"
                            v-model="formData.score"
                    ></el-input>
                </el-form-item>
                <el-form-item label="操作">
                    <el-tree
                            :data="treeData"
                            ref="tree"
                            node-key="name"
                            :props="defaultProps"
                            show-checkbox
                            :check-strictly="false"
                    ></el-tree>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="changeRoles('rolesForm', editType)"
                    >确认</el-button
                    >
                    <el-button @click="diaIsShow = false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    //import { getAll } from '@/api/roles'
    import { transferScore,addMoney} from '@/api/roles'
    import store from '@/store'
    import { asyncRoutes, currencyRoutes } from '@/router'
    export default {
        data() {
            return {
                rolesTab: [],
                diaIsShow: false,
                formData: {},
                editType: 'update',
                rules: {
                    getId: [
                        {
                            required: true,
                            message: '请输入要操作的对方ID',
                            trigger: 'blur'
                        }
                    ],
                    score: [
                        {
                            required: true,
                            message: '请输入要操作的积分数目',
                            trigger: 'blur'
                        }
                    ]
                },
                editIndex: 0,
                allRoute: ["充值","转赠"],
                treeData: [],
                defaultProps: {
                    label: 'label',
                    children: 'children'
                }
            }
        },
        created() {
            this._getAllRolse()
            this.treeData = this.getTreeData(this.allRoute)
        },
        filters: {
            statusText(val) {
                if (val === undefined) return
                if (val === 0) {
                    return '申请'
                } else if (val === 1) {
                    return '承兑'
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
            _getAllRolse() {
                let names = localStorage.getItem('name')
                let name = {businessId:parseInt(names)}
                /*getAll(name)
                    .then(res => {
                        this.rolesTab = res.response
                        localStorage.setItem('length',res.response.length)
                        console.log(res.response)
                    })
                    .catch(error => {
                        this.$message.error(error)
                    })*/
            },
            isAdmin(row) {
                if (row.key === 'admin' || row.key === 'user') return true
                else return false
            },
            deleteRoles(index) {
                this.$confirm('此操作将永久删除相关数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                })
                    .then(() => {
                        this.rolesTab.splice(index, 1)
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        })
                    })
                    .catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        })
                    })
            },
            addRolesTab() {
                this.diaIsShow = true
                this.editType = 'add'
                this.formData = {}
                this.$nextTick(() => {
                    this.$refs.rolesForm.clearValidate()
                    this.$refs.tree.setCheckedKeys([])
                })
            },
            editRoles(index, row) {
                this.diaIsShow = true
                this.editIndex = index
                this.editType = 'update'
                this.formData = Object.assign({}, this.formData, {
                    key: row.key,
                    description: row.description
                })
                this.$nextTick(() => {
                    this.$refs.rolesForm.clearValidate()
                    this.$refs.tree.setCheckedKeys(row.pages)
                })
            },
            changeRoles(form, type) {
                this.$refs[form].validate(valid => {
                    if (valid) {
                        let treeKeys = this.$refs.tree.getCheckedKeys()
                        if (type === 'update') {
                            let index = this.editIndex
                            this.rolesTab[index].key = this.formData.key
                            this.rolesTab[index].description = this.formData.description
                            this.rolesTab[index].pages = treeKeys
                            this.$notify({
                                title: '成功',
                                message: '权限修改成功',
                                type: 'success'
                            })
                        } else if (type === 'add') {
                            let newTab = {}
                            newTab.sendId = parseInt(store.getters.userName)
                            newTab.score = this.formData.score
                            //newTab.accept = 0
                            newTab.getId = this.formData.getId
                            newTab.pages = treeKeys
                            console.log(newTab.sendId,newTab.score,newTab.getId)
                            let point = {
                                sendId:parseInt(store.getters.userName),
                                getId:this.formData.getId,
                                score:this.formData.score
                            }
                            let money = {
                                userId:parseInt(store.getters.userName),
                                money: this.formData.score
                            }
                            if(newTab.pages[0] === '转赠')
                                newTab.score = 0-newTab.score
                            this.rolesTab.push(newTab)
                            if(newTab.pages[0] === '转赠'){
                                transferScore(point)
                                    .then(res => {
                                        this.$notify({
                                            title: '成功',
                                            message: res.response,
                                            type: 'success'
                                        })
                                    })
                                    .catch(error => {
                                        this.$message.error(error)
                                    })
                            }else {
                                addMoney(money)
                                    .then(res => {
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
                        }
                        this.diaIsShow = false
                    } else return
                })
            },
            getTreeData(route) {
                let arrBox = []
                for (let item of route) {
                    /*if (item.hidden) continue
                    let onlyChild = this.hasOnlyChild(item.children, item)
                    if (onlyChild && !onlyChild.children) {
                      item = onlyChild
                    }*/
                    let data = {
                        //label: item.meta.title,
                        //name: item.name
                        label:item,
                        name:item
                    }
                    if (item.children) {
                        data.children = this.getTreeData(item.children)
                    }
                    arrBox.push(data)
                }
                return arrBox
            },
            hasOnlyChild(children = [], item) {
                let newChildren = children.filter(item => {
                    if (item.hidden) {
                        return false
                    } else {
                        return true
                    }
                })
                if (newChildren.length === 1 && !item.meta) {
                    return newChildren[0]
                } else if (newChildren.length === 0) {
                    return item
                }
                return false
            },
            forSearchArr(route, roles) {
                let arrNew = []
                for (let item of route) {
                    let itemNew = { ...item } //解决浅拷贝共享同一内存地址
                    if (roles.includes(itemNew.name)) {
                        if (itemNew.children) {
                            itemNew.children = this.forSearchArr(itemNew.children, roles)
                        }
                        arrNew.push(itemNew)
                    }
                }
                return arrNew
            }
        }
    }
</script>
<style scoped lang="scss">
    .rolesControl .mtop30 .el-button {
        padding: 8px 18px;
        font-size: 12px;
    }
    .diaForm {
        .el-input {
            width: 350px;
        }
    }
</style>
<style lang="scss">
    .diaForm .el-form-item__label {
        padding-right: 12px;
    }
</style>
