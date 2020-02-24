import axios, { AxiosPromise } from "axios"
import SignUpInfo, { SignUpInfoModel, SignUpInfoData } from '@/types/model/SignUpInfo'

let signUpOption: SignUpInfo = new SignUpInfo()
let baseAddr: string = process.env.NODE_ENV === 'production' ? '' : 'http://192.168.11.37:8080'
let baseUrl: string = `${baseAddr}/api/signUp`
let resultUrl: string = baseUrl + "/userCheckId?userId=" + signUpOption.userId

export const requiredText = (val: any): boolean | string => {
  return !!val || 'Required.'
}
export const requiredEmail = (val: any): boolean | string => {
  const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  return !!pattern.test(val) || 'Invalid e-mail.'
}

export const requiredPwd = (val: any): boolean | string => {
  // return val.length <= 20 || 'Max 20 characters'
  return !!val || 'Required.'
}

export const requiredId = (val: any): boolean | string => {
//   console.log("signUpOption.userId:", signUpOption.userId)
//   axios.get(resultUrl).then((res) => {
//     console.log(res.data)
//   })
//   return true
  return !!val || 'Required.'
}
