import VueLogger from 'vuejs-logger/dist/index'
// from .env
// const LOG_LEVEL = process.env.APP_LOG_LEVEL
const isProduction = process.env.NODE_ENV === 'production'

const vueLoggerOptions = {
  isEnabled: true,
  logLevel: isProduction ? 'error' : 'debug',
  stringifyArguments: false,
  showLogLevel: true,
  showMethodName: true,
  separator: '|',
  showConsoleColors: true
}

export {
  VueLogger,
  vueLoggerOptions
}
