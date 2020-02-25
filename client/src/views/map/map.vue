<template>
  <div>
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
  </div>
</template>

<script>
import naver from 'vue-naver-maps'
export default {
  name: 'HelloWorld',
  data () {
    return {
      info: false,
      marker: null,
      count: 1,
      map: null,
      mapOptions: {
        lat: 37,
        lng: 127,
        zoom: 10,
        zoomControl: true,
        zoomControlOptions: { position: 'TOP_RIGHT' },
        mapTypeControl: true
      }
    }
  },
  computed: {
    hello () {
      return `Hello, World! × ${this.count}`
    }
  },
  methods: {
    onLoad (vue) {
    },
    onWindowLoad (that) {
    },
    onMarkerClicked (event) {
      this.info = !this.info
    },
    onMarkerLoaded (vue) {
      this.marker = vue.marker
    }
  },
  mounted () {
    setInterval(() => this.count++, 1000)
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
