import { VueModules } from '@/types'

const requireModule = require.context('@/modules', true, /index.ts$/)
const modules: VueModules = {}

requireModule.keys().forEach((fileName) => {
  if (fileName === './index.ts') {
    return
  }

  modules[fileName] = requireModule(fileName).default
})

export default modules
