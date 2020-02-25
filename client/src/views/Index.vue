<template>
  <v-app>
    <layout>
      <v-toolbar
        :clipped-left="$vuetify.breakpoint.lgAndUp"
        class="grey darken-3"
        dark
        fixed
        app>
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
          <v-btn flat to='/login'>로그인</v-btn>
          <v-btn flat to='/signUpSteps'>회원가입</v-btn>
          <v-btn flat>이용안내</v-btn>
          <v-btn flat>공지사항</v-btn>
        </v-toolbar-items>
      </v-toolbar>
    </layout>
    <layout mt-5>
      <v-footer
        dark
        height="auto"
      >
          <v-card
            class="flex"
            flat
            tile
          >
            <v-card-title class="teal">
              <strong class="subheading">Get connected with us on social networks!</strong>

              <v-spacer></v-spacer>

              <v-btn
                v-for="icon in icons"
                :key="icon"
                class="mx-3"
                dark
                icon
              >
                <v-icon size="24px">{{ icon }}</v-icon>
              </v-btn>
            </v-card-title>

            <v-card-actions class="grey darken-3 justify-center">
              &copy;2020 — <strong>따릉이 토이 프로젝트</strong>
            </v-card-actions>
          </v-card>
        </v-footer>
    </layout>
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
  private mapOption: Object = {
    lat: 37,
    lng: 127,
    zoom: 10,
    zoomControl: true,
    zoomControlOptions: { position: 'TOP_RIGHT' },
    mapTypeControl: true
  }
  private info: boolean = false
  private marker: any = null
  private count: number = 1
  private map: any = null
  // ------------------------------------------------------------
  // lifecyle hook
  mounted () {
    setInterval(() => this.count++, 1000)
    // this.$router.replace('intro')
  }

  private onLoad () {

  }

  private onWindowLoad () {

  }

  private onMarkerClicked () {
    this.info = !this.info
  }

  private onMarkerLoaded () {
  }
}
</script>
<style scoped>
.info-window-container {
    padding: 10px;
    width: 300px;
    height: 100px;
  }
</style>
