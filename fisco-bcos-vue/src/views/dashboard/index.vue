<template>
  <div class="homeBox">
    <components :is="componentOn" />
  </div>
</template>

<script>
import adminComponent from './admin'
import userComponent from './user'
import clientComponent from './client'
import { mapGetters } from 'vuex'
export default {
  computed: {
    ...mapGetters(['status']),
    componentOn() {
      if (this.status === '0') {
        return 'adminComponent'
      } else if (this.status==='1'){
        return 'userComponent'
      } else {
        return 'clientComponent'
      }
    }
  },
  components: {
    adminComponent,
    userComponent,
    clientComponent
  }
}
</script>
