import { Module, Action, Mutation, VuexModule, getModule } from 'vuex-module-decorators'
import store from '@/store'

@Module({ dynamic: true, name: 'mainStore', store })
class MainStore extends VuexModule {
    isMemberExist: boolean = false

    @Action({ commit: 'UPDATE_IS_MEMBER_EXIST' })
    updateIsMemberExist (flag: boolean) {
      return flag
    }

    @Mutation
    UPDATE_IS_MEMBER_EXIST (flag: boolean) {
      this.isMemberExist = flag
    }

    get getIsMemberExist (): boolean {
      return this.isMemberExist
    }
}

export default getModule(MainStore)
