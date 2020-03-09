<template>
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
            <v-btn
                round large dark ripple
                color="teal"
                @click="popupKakaoLogin">
                KakaoLogin
            </v-btn>
        </v-layout>
      </v-container>
    </v-content>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator"

@Component
export default class UserLogin extends Vue {
  // ------------------------------------------------------------
  // lifecyle hook
  mounted () {
    // this.$router.replace('intro')
  }

  /**
   * kakao Login 연동 테스트
   */
  private async popupKakaoLogin () {
    await this.$bizApi.commBiz.kakaoLogin()
      .then((res) => {
        Vue.$alert(res.data)
      }).catch((err) => {
        this.$log.error(err)
      })
  }
}
</script>
