import _Vue, { PluginObject } from 'vue'
import axios, { AxiosStatic } from 'axios'
import uuid from 'uuid/v4'
import BizApi from '@/bizApi'
import { DialogOptions, LogLevel } from '@/types'
import DialogPlugin from '@/components/dialog/DialogPlugin'
import i18n from '@/config/i18n'
import _ from 'lodash'

declare module 'vue/types/vue' {
    export interface VueConstructor<V extends _Vue = _Vue> {
        $http: AxiosStatic
        $uuid: () => string
        _: _.LoDashStatic
        // ---------------------------------------------------------------
        // components
        $alert: <T>(text: string, options?: DialogOptions) => Promise<T>
    }
}

declare module 'vue/types/vue' {
  interface Vue {
    $log: LogLevel;
    $bizApi: BizApi;
    // ---------------------------------------------------------------
    // i18n
    $lang(key: string, params?: object): string;
    $msg(key: string, params?: object): string;
    $label(key: string, params?: object): string;
    $errMsg(key: number, params?: object): string;
  }
}

export default {
  install (Vue: typeof _Vue, store: any, config: any): void {
    Vue.$http = axios
    Vue.$uuid = uuid
    Vue.prototype.$bizApi = new BizApi()
    Vue.prototype.$lang = (key: string, params?: {}) => i18n.t(key, params)
    Vue.prototype.$msg = (key: string, params?: {}) => i18n.t(`message.${key}`, params)
    Vue.prototype.$label = (key: string, params?: {}) => i18n.t(`label.${key}`, params)
    Vue.prototype.$errMsg = (key: number, params?: {}) => i18n.t(`error.${key}`, params)
    Vue.$alert = <T>(text: string, options?: DialogOptions) => new DialogPlugin().confirm(text, options) as Promise<T>
    Vue._ = _
  }
} as PluginObject<any>
