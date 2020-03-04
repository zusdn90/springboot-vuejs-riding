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
                    v-model="signUpOption.uid"
                    prepend-icon="email"
                    name="email"
                    label="Email"
                    :rules="[requiredEmail]"
                    type="text">
                  </v-text-field>
                  <v-btn flat small @click="getCheckUserEmail">중복 검사</v-btn>
                  <v-text-field
                    v-model="signUpOption.password"
                    id="password"
                    :append-icon="isPwdShowOption ? 'visibility' : 'visibility_off'"
                    prepend-icon="lock"
                    name="password"
                    label="Password"
                    maxlength="20"
                    hint="At least 20 characters"
                    :rules="[requiredText]"
                    type="password">
                  </v-text-field>
                  <v-text-field
                    v-model="signUpOption.name"
                    id="userName"
                    prepend-icon="person"
                    name="userName"
                    label="userName"
                    :rules="[requiredText]"
                    type="text">
                  </v-text-field>
                  <v-text-field
                    v-model="signUpOption.phoneNumber"
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
  private isValidateForm: boolean = true
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
  private userSignInfoSave () {
    const config = {
      headers: {
        "Content-Type": "application/json"
      }
    }

    /* 회원가입 입력 폼 검증 */
    this.userValidation()

    if (this.isValidateForm === true) {
    /* 회원가입 진행 */
      this.$bizApi.commBiz.userSignUp(this.signUpOption.getOptionDataForSending(), config)
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

  /**
   * 회원정보 입력에 대한 검증
   */
  private userValidation () {
    /* Email를 입력하지 않았을 경우 */
    if (this.signUpOption.uid === '' || this.signUpOption.uid === undefined) {
      this.isValidateForm = false
      Vue.$alert('Please check the Email')
      return
    }

    /* Password를 입력하지 않았을 경우 */
    if (this.signUpOption.password === '' || this.signUpOption.password === undefined) {
      this.isValidateForm = false
      Vue.$alert('Please check the Password')
      return
    }

    /* Phone Number를 입력하지 않았을 경우 */
    if (this.signUpOption.phoneNumber === '' || this.signUpOption.phoneNumber === undefined) {
      this.isValidateForm = false
      Vue.$alert('Please check the Phone Number')
      return
    }

    /* 이름을 입력하지 않았을 경우 */
    if (this.signUpOption.name === '' || this.signUpOption.name === undefined) {
      this.isValidateForm = false
      Vue.$alert('Please check the Name')
      return
    }

    /* ID가 중복되었을 경우 */
    if (this.isDuplicateId === true) {
      Vue.$alert('사용할 수 없는 ID 입니다.')
      return false
    }
  }

  /**
   * 사용 가능한 ID를 체크한다.
   */
  private async getCheckUserEmail () {
    await this.$bizApi.commBiz.userCheckId(this.signUpOption.uid)
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
