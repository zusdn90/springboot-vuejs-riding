export default class SignUpInfo implements SignUpInfoModel {
    userEmail: string
    userPwd: string
    userName: string
    userPhoneNumber: string

    /**
     * 전송을 위한 옵션 데이터를 리턴한다.
     */
    getOptionDataForSending (): object {
      return {
        userEmail: this.userEmail,
        userPwd: btoa(this.userPwd),
        userName: this.userName,
        userPhoneNumber: this.userPhoneNumber
      }
    }
}

export interface SignUpInfoModel {
  userEmail: string
  userPwd: string
  userName: string
  userPhoneNumber: string
}

export interface SignUpInfoData {
  userEmail: string
  userPwd: string
  userName: string
  userPhoneNumber: string
}
