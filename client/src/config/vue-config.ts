import 'babel-polyfill'
import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import router from '@/router'
import i18n from './i18n'
import plugins from './plugin-config'
import { VueLogger, vueLoggerOptions } from './logger-config'
import modules from '@/modules'
// import VuetifyDialog from 'vuetify-dialog'

Vue.config.productionTip = false

Vue.use(Vuetify, {
  iconfont: 'md'
})

Vue.use(VueLogger, vueLoggerOptions)

// Vue.use(VuetifyDialog)

Object.keys(modules).forEach((key) => {
  Vue.use(modules[key])
})

// add plugin
Object.keys(plugins).forEach((key) => {
  Vue.use(plugins[key])
})

export {
  i18n,
  Vue,
  router
}
