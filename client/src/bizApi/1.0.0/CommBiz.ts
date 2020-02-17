import { AxiosPromise, AxiosInstance } from 'axios'
import i18n from '@/config/i18n'

export default class CommBiz implements CommBizIF {
    private baseAddr: string = process.env.NODE_ENV === 'production' ? '' : 'http://192.168.11.37:5000'
    private baseUrl: string = `${this.baseAddr}/api/common`
    private bizAxios: AxiosInstance

    constructor (_bizAxios: AxiosInstance) {
      this.bizAxios = _bizAxios
    }

    private actions = {
      // 관리자 로그인
      userSignUp: { url: '/userSignUp', comment: "회원가입을 진행합니다." }
    }

    userSignUp = (param?: string, config?: object): AxiosPromise<any> => {
      const action = this.actions.userSignUp
      return this.callAxios(action.url, action.comment, param, config)
    }
    // ----------------------------------------------------------
    private callAxios = (actUrl: string, actComment: string, param?: object | string | number, config?: object): AxiosPromise<any> => {
      if (config) {
        return this.bizAxios.post(`${this.baseAddr}${actUrl}`, param, config)
      }
      if (param) {
        return this.bizAxios.post(`${this.baseUrl}${actUrl}`, param)
      }
      return this.bizAxios.post(`${this.baseUrl}${actUrl}`)
    }

    // request get(Admin login session check)
    private getCallAxios = (actUrl: string, actComment: string): AxiosPromise<any> => {
      return this.bizAxios.get(`${this.baseAddr}${actUrl}`)
    }
}

export interface CommBizIF {

}
