import CommBiz from './1.0.0/CommBiz'
import axios, { AxiosInstance } from 'axios'
import Response from '@/types/model/Response'

export default class BizApi {
    bizAxios!: AxiosInstance
    commBiz: CommBiz
    transition: boolean = false

    constructor () {
      this.bizAxios = axios.create()
      this.bizAxios.interceptors.response.use(this.responseInterceptor)
      // ------------------------------------------------------------
      this.commBiz = new CommBiz(this.bizAxios)
      // ------------------------------------------------------------
    }

    responseInterceptor = (response: any) => {
      let resdata: Response = new Response(response.data, true)
      if (this.transition && (!resdata.isSuccess)) {
        throw resdata.errMsg
      }
      return response
    }
    /**
     * Transition 상태를 변경한다.
     */
    setTransition = (flag: boolean) => {
      this.transition = flag
    }
}
