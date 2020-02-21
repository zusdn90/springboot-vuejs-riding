import axios, { AxiosPromise } from "axios"

export const requiredText = (val: any): boolean | string => {
  return !!val || 'Required.'
}
export const requiredEmail = (val: any): boolean | string => {
  const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  return !!pattern.test(val) || 'Invalid e-mail.'
}

export const requiredPwd = (val: any): boolean | string => {
  return val.length <= 20 || 'Max 20 characters'
}

// export const requiredId = (val: any): boolean | string => {
//   axios.get('http://192.168.11.37:8080//api/common/userCheck').then((res) => {
//     console.log(res.data)
//   })
//   debugger
//   return true
// }
