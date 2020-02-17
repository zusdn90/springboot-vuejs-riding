import { Vue, router, i18n } from '@/config/vue-config'
import App from "./App.vue"
import store from "./store"

new Vue({
  i18n,
  router,
  store,
  render: h => h(App)
}).$mount("#app")
