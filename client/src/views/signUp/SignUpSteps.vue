<template>
  <layout>
    <v-toolbar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      class="grey darken-3"
      dark
      fixed
      app
    >
      <v-toolbar-title style="width: 300px" class="ml-0 pl-3">
        <v-toolbar-side-icon></v-toolbar-side-icon>
        <span class="hidden-sm-and-down">서울 자전거</span>
      </v-toolbar-title>
      <v-text-field
        flat
        solo-inverted
        hide-details
        prepend-inner-icon="search"
        label="Search"
        class="hidden-sm-and-down"
      ></v-text-field>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-sm-and-down">
        <v-btn flat to='/index'>홈</v-btn>
        <v-btn flat to="/login">로그인</v-btn>
        <v-btn flat to="/signUpSteps">회원가입</v-btn>
        <v-btn flat>이용안내</v-btn>
        <v-btn flat>공지사항</v-btn>
      </v-toolbar-items>
    </v-toolbar>

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
  </layout>
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
