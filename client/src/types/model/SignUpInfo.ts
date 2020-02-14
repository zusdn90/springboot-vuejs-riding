export default class SignUpInfo implements SignUpInfoModel {
    userId!: string
    userPw!: string
    email!: string
    phoneNumber!: string

    /**
     * 전송을 위한 옵션 데이터를 리턴한다.
     */
    getOptionDataForSending (): object {
      return {
        USER_ID: this.userId,
        USER_PW: this.userPw,
        USER_EMAIL: this.email,
        USER_PHONENUMBER: this.phoneNumber
      }
    }
}

export interface SignUpInfoModel {
    userId: string
    userPw: string
    email: string
    phoneNumber: string
}

export interface SignUpInfoData {
    USER_ID: string
    USER_PW: string
    USER_EMAIL: string
    USER_PHONENUMBER: string
}
