export default class SignUpInfo implements SignUpInfoModel {
    userId: string
    userPwd: string
    userEmail: string
    userPhoneNumber: string

    /**
     * 전송을 위한 옵션 데이터를 리턴한다.
     */
    getOptionDataForSending (): object {
      return {
        userId: this.userId,
        userPwd: btoa(this.userPwd),
        userEmail: this.userEmail,
        userPhoneNumber: this.userPhoneNumber
      }
    }
}

export interface SignUpInfoModel {
    userId: string
    userPwd: string
    userEmail: string
    userPhoneNumber: string
}

export interface SignUpInfoData {
  userId: string
  userPwd: string
  userEmail: string
  userPhoneNumber: string
}
