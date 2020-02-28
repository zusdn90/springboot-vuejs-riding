import { AxiosPromise, AxiosInstance } from 'axios'
import i18n from '@/config/i18n'

export default class CommBiz implements CommBizIF {
    private baseAddr: string = process.env.NODE_ENV === 'production' ? '' : 'http://localhost:8080'
    private baseUrl: string = `${this.baseAddr}/api`
    private bizAxios: AxiosInstance

    constructor (_bizAxios: AxiosInstance) {
      this.bizAxios = _bizAxios
    }

    private actions = {
      // 회원가입
      userSignUp: { url: '/user', comment: "회원가입을 진행합니다." },
      // ID 중복확인
      userCheckId: { url: '/user/checkId', comment: "사용가능한 ID를 체크합니다." }
    }

    userSignUp = (param?: object, config?: object): AxiosPromise<any> => {
      const action = this.actions.userSignUp
      return this.callAxios(action.url, action.comment, param, config)
    }

    userCheckId = (param?: string, config?: object): AxiosPromise<any> => {
      const action = this.actions.userCheckId
      return this.getCallAxios(action.url, action.comment, param)
    }
    // ----------------------------------------------------------
    private callAxios = (actUrl: string, actComment: string, param?: object | string | number, config?: object): AxiosPromise<any> => {
      if (config) {
        return this.bizAxios.post(`${this.baseUrl}${actUrl}`, param, config)
      }
      if (param) {
        return this.bizAxios.post(`${this.baseUrl}${actUrl}`, param)
      }
      return this.bizAxios.post(`${this.baseUrl}${actUrl}`)
    }

    // request get
    private getCallAxios = (actUrl: string, actComment: string, param?: object | string | number): AxiosPromise<any> => {
      return this.bizAxios.get(`${this.baseUrl}${actUrl}`, { params: { userEmail: param } })
    }
}

export interface CommBizIF {

}
