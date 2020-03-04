export default class SignUpInfo implements SignUpInfoModel {
    uid: string
    password: string
    name: string
    phoneNumber: string

    /**
     * 전송을 위한 옵션 데이터를 리턴한다.
     */
    getOptionDataForSending (): object {
      return {
        uid: this.uid,
        password: btoa(this.password),
        name: this.name,
        phoneNumber: this.phoneNumber
      }
    }
}

export interface SignUpInfoModel {
  uid: string
  password: string
  name: string
  phoneNumber: string
}

export interface SignUpInfoData {
  uid: string
  password: string
  name: string
  phoneNumber: string
}
