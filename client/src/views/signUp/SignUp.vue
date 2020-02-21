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
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="grey darken-1">
                <v-toolbar-title>Create Account</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    v-model="signUpOption.userId"
                    prepend-icon="person"
                    name="id"
                    label="Id"
                    :rules="[requiredText]"
                    type="text">
                  </v-text-field>
                  <v-text-field
                    v-model="signUpOption.userEmail"
                    prepend-icon="email"
                    name="email"
                    label="Email"
                    :rules="[requiredEmail]"
                    type="text">
                  </v-text-field>
                  <v-text-field
                    v-model="signUpOption.userPwd"
                    id="password"
                    :append-icon="isPwdShowOption ? 'visibility' : 'visibility_off'"
                    prepend-icon="lock"
                    name="password"
                    label="Password"
                    maxlength="20"
                    hint="At least 20 characters"
                    :rules="[requiredPwd]"
                    type="password">
                  </v-text-field>
                  <v-text-field
                    v-model="signUpOption.userPhoneNumber"
                    id="phoneNumber"
                    prepend-icon="phone"
                    name="phoneNumber"
                    label="phoneNumber"
                    :rules="[requiredText]"
                    type="text">
                  </v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  round large dark ripple
                  color="teal"
                  @click="userSignInfoSave">
                  Sign up
                  </v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </layout>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import SignUpInfo, { SignUpInfoModel, SignUpInfoData } from '@/types/model/SignUpInfo'
import axios, { AxiosPromise } from "axios"
import { requiredText, requiredEmail, requiredPwd } from '@/helper/validator'

@Component
export default class SignUp extends Vue {
  private signUpOption: SignUpInfo = new SignUpInfo()
  private isPwdShowOption: boolean = false
  private requiredText: any = requiredText
  private requiredEmail: any = requiredEmail
  private requiredPwd: any = requiredPwd

  // ------------------------------------------------------------
  // lifecyle hook
  mounted () {
    // this.$router.push('intro')

  }

  /**
   * 회원가입을 수행한다.
   */
  private async userSignInfoSave () {
    const config = {
      headers: {
        "Content-Type": "application/json"
      }
    }

    /* Id를 입력하지 않았을 경우 */
    if (this.signUpOption.userId === '') {
      Vue.$alert('Please check the Id')
      return
    }

    /* Email를 입력하지 않았을 경우 */
    if (this.signUpOption.userEmail === '') {
      Vue.$alert('Please check the Email')
      return
    }

    /* Password를 입력하지 않았을 경우 */
    if (this.signUpOption.userPwd === '') {
      Vue.$alert('Please check the Password')
    }

    /* Phone Number를 입력하지 않았을 경우 */
    if (this.signUpOption.userPhoneNumber === '') {
      Vue.$alert('Please check the Phone Number')
    }

    /* Login 검증 */
    await this.$bizApi.commBiz.userSignUp(this.signUpOption.getOptionDataForSending(), config)
      .then((res) => {
        // Vue.$alert(res.data.msg)
        if (res.data.code === 0) {
          Vue.$alert("회원가입이 완료되었습니다.")
          this.$router.replace('index')
        } else {
          this.$log.error('Please to check data from Server')
        }
      }).catch((err) => {
        this.$log.error(err)
      })
  }
}
</script>
