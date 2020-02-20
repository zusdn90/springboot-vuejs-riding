<template>
  <v-app>
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
        <v-btn flat to='/login'>로그인</v-btn>
        <v-btn flat to='/signUp'>회원가입</v-btn>
        <v-btn flat>이용안내</v-btn>
        <v-btn flat>공지사항</v-btn>
      </v-toolbar-items>
    </v-toolbar>
    <v-content>
      <v-container fluid fill-height>
        <naver-maps
          :height="800"
          :width="800"
          :mapOptions="mapOptions"
          :useStyleMap="true"
          :initLayers="['BACKGROUND', 'BACKGROUND_DETAIL', 'BYCYCLE', 'CADASTRAL', 'CTT', 'HIKING_TRAIL', 'PANORAMA', 'POI_KOREAN', 'TRANSIT']"
          @load="onLoad">
          <naver-info-window
            class="info-window"
            @load="onWindowLoad"
            :isOpen="info"
            :marker="marker">
            <div class="info-window-container">
              <h1>{{hello}}</h1>
            </div>
          </naver-info-window>
          <naver-marker :lat="37" :lng="127" @click="onMarkerClicked" @load="onMarkerLoaded"/>
          <naver-circle :lat="37" :lng="127" :radius="88600"/>
          <naver-rectangle :south="36" :north="38" :west="126" :east="128"/>
          <naver-ellipse :bounds="{south:36,north:38,west:126,east:128}"/>
          <naver-polygon :paths="[[{lat:37,lng:127},{lat:38,lng:127},{lat:38,lng:129},{lat:37,lng:128}]]"/>
          <naver-polyline :path="[{lat:37,lng:127},{lat:38,lng:129}]"/>
          <naver-ground-overlay
            :url="'//logoproject.naver.com/img/img_about.gif'"
            :bounds="{south:36.7,north:36.9,west:126.5,east:127.5}"/>
        </naver-maps>
      </v-container>
    </v-content>
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
