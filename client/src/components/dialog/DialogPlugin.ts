import Vue from 'vue'
import Dialog from '@/components/dialog/Dialog.vue'
import { DialogOptions } from '@/types'
import i18n from '@/config/i18n'

export default class DialogPlugin {
    private $root = {};
    private mounted = false;

    confirm (text: string, options: DialogOptions = {}) {
      this.mountIfNotMounted()
      return new Promise((resolve, reject) => {
        options.id = Vue.$uuid()
        options.resolve = resolve
        options.reject = reject
        // @ts-ignore
        this.$root.commit({ text, ...options })
      })
    }

    mountIfNotMounted () {
      this.$root = (() => {
        const DialogConstructor = Vue.extend(Dialog)
        const node = document.createElement('div')
        document.body.appendChild(node)
        const Vm = new DialogConstructor({ i18n })
        return Vm.$mount(node)
      })()

      this.mounted = true
    }

    destroy () {
      if (this.mounted) {
        // @ts-ignore
        const elem = this.$root.$el
        // @ts-ignore
        this.$root.$destroy()
        // @ts-ignore
        this.$root.$off()
        elem.remove()
        this.mounted = false
      }
    }
}
