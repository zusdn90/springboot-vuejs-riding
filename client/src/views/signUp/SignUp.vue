<template>
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
                    :rules="[requiredId]"
                    type="text">
                  </v-text-field>
                  <v-btn flat small @click="getCheckUserId">중복 검사</v-btn>
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
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import SignUpInfo, { SignUpInfoModel, SignUpInfoData } from '@/types/model/SignUpInfo'
import axios, { AxiosPromise } from "axios"
import { requiredText, requiredEmail, requiredPwd, requiredId } from '@/helper/validator'

@Component
export default class SignUp extends Vue {
  private signUpOption: SignUpInfo = new SignUpInfo()
  private isPwdShowOption: boolean = false
  private isDuplicateId: boolean
  private requiredText: any = requiredText
  private requiredEmail: any = requiredEmail
  private requiredPwd: any = requiredPwd
  private requiredId: any = requiredId

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
      return
    }

    /* Phone Number를 입력하지 않았을 경우 */
    if (this.signUpOption.userPhoneNumber === '') {
      Vue.$alert('Please check the Phone Number')
      return
    }

    /* ID가 중복되었을 경우 */
    if (this.isDuplicateId === true) {
      Vue.$alert('사용할 수 없는 ID 입니다.')
      return
    }

    /* 회원가입 진행 */
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

  /**
   * 사용 가능한 ID를 체크한다.
   */
  private async getCheckUserId () {
    await this.$bizApi.commBiz.userCheckId(this.signUpOption.userId)
      .then((res) => {
        if (res.data.code === 0) {
          this.isDuplicateId = false
          Vue.$alert("사용 가능한 ID 입니다.")
        } else {
          this.isDuplicateId = true
          Vue.$alert("중복된 ID 입니다.")
        }
      }).catch((err) => {
        this.$log.error(err)
      })
  }
}
</script>
