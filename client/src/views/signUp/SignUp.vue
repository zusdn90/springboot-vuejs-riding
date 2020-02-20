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
                    :rules="[v => !!v || 'id is required']"
                    type="text">
                  </v-text-field>
                  <v-text-field
                    v-model="signUpOption.userEmail"
                    prepend-icon="email"
                    name="email"
                    label="Email"
                    :rules="[v => !!v || 'email is required']"
                    type="text">
                  </v-text-field>
                  <v-text-field
                    v-model="signUpOption.userPwd"
                    id="password"
                    prepend-icon="lock"
                    name="password"
                    label="Password"
                    :rules="[v => !!v || 'pwd is required']"
                    type="password">
                  </v-text-field>
                  <v-text-field
                    v-model="signUpOption.userPhoneNumber"
                    id="phoneNumber"
                    prepend-icon="phone"
                    name="phoneNumber"
                    label="phoneNumber"
                    :rules="[v => !!v || 'phoneNumber is required']"
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

@Component
export default class SignUp extends Vue {
  private signUpOption: SignUpInfo = new SignUpInfo()

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
      Vue.$alert('Please check the id')
      return
    }

    /* Email를 입력하지 않았을 경우 */
    if (this.signUpOption.userEmail === '') {
      Vue.$alert('Please check the Email')
      return
    }

    /* Password를 입력하지 않았을 경우 */
    if (this.signUpOption.userPwd === '') {
      Vue.$alert('Please check the password')
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
