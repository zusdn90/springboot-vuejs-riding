<template>
  <v-content>
    <v-spacer></v-spacer>
    <v-stepper v-model="e1">
      <v-stepper-header>
        <v-stepper-step :complete="e1 > 1" step="1">{{
          $label("Agreement")
        }}</v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step :complete="e1 > 2" step="2">{{
          $label("IdentityVerification")
        }}</v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step step="3">{{
          $label("EnterInformation")
        }}</v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          <v-card class="mb-5" color="grey lighten-1" height="200px"></v-card>

          <v-btn dark ripple color="teal" @click="e1 = 2">
            Continue
          </v-btn>

          <v-btn flat>Cancel</v-btn>
        </v-stepper-content>

        <v-stepper-content step="2">
          <v-card class="mb-5" color="grey lighten-1" height="200px"></v-card>

          <v-btn dark ripple color="teal" @click="e1 = 3">
            Continue
          </v-btn>

          <v-btn flat>Cancel</v-btn>
        </v-stepper-content>

        <v-stepper-content step="3">
          <v-card class="mb-5" color="grey lighten-1" height="200px"></v-card>

          <v-btn dark ripple color="teal" to="/signUp">
            Continue
          </v-btn>

          <v-btn flat @click="goBackStep">Cancel</v-btn>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
    <v-spacer></v-spacer>
  </v-content>
</template>
<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator"

@Component
export default class SignUpSteps extends Vue {
  private e1: number = 0
  // ------------------------------------------------------------
  // lifecyle hook
  mounted () {
    // this.$router.push('intro')
  }

  /**
   * 이전 작업 단계로 이동합니다.
   */
  private goBackStep (): void {
    // 전 페이지로 이동
    this.$router.go(-1)
  }
}
</script>
