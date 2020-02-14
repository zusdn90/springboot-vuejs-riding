<template>
    <v-app>
    </v-app>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import router from '../router/index'

@Component
export default class Index extends Vue {
  // ------------------------------------------------------------
  // lifecyle hook
  mounted () {
    // this.$router.push('intro')

  }
}
</script>
