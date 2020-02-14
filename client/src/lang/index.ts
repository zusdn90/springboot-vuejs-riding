const langs = require.context(
  '@/lang',
  true,
  /\.json$/
)

const messages: any = {}

for (const file of langs.keys()) {
  if (file === './index.ts') {
    continue
  }

  const path = file.replace(/(\.\/|\.json$)/g, '').split('/')

  path.reduce((o: any, s: string, i: number) => {
    if (o[s]) {
      return o[s]
    }

    o[s] = i + 1 === path.length ? langs(file) : {}

    return o[s]
  }, messages)
}

export default messages
