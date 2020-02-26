<template>
  <v-app>
    <h-header/>
    <h-footer/>
  </v-app>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator"
import router from "../router/index"

@Component
export default class Index extends Vue {
  private icons: Array<any> = [
    'fab fa-facebook',
    'fab fa-twitter',
    'fab fa-linkedin',
    'fab fa-instagram'
  ]
  // ------------------------------------------------------------
  // lifecyle hook
  mounted () {
    // this.$router.replace('intro')
  }
}
</script>
