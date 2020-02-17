import { DialogOptions } from '@/types'
import Vue from 'vue'
import i18n from '@/config/i18n'

export default class Response {
    isSuccess: boolean = false
    resData?: Array<any> | Object = []
    errMsg?: string | undefined = ''
    returnCode: number | undefined

    constructor (rtndata: any, ignorePopup?: boolean) {
      try {
        this.returnCode = rtndata.returncode
        if (this.returnCode === 0) {
          this.isSuccess = true
          this.resData = rtndata.stdout
        } else {
          this.isSuccess = false
          this.errMsg = rtndata.stderr
          if (this.errMsg !== undefined) {
            Vue.prototype.$log.debug(`Response => ${rtndata.stderr}`)
            if (!ignorePopup) {
              // Vue.$alert(rtndata.stderr, { title: i18n.t('label.error'), apply: i18n.t('label.confirm'), confirm: false } as DialogOptions)
            }
          }
        }
      } catch (error) {
        // Vue.prototype.$log.error(error)
        this.isSuccess = false
      }
    }
}
