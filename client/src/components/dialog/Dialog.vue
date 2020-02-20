<template>
     <v-dialog
      v-model="dialog"
      persistent
      content-class="dialog-container"
      :max-width="options.maxWidth ? `${options.maxWidth}px` : '350px'"
    >
      <v-card>
        <v-card-title class="headline">
          {{options.title}}
        </v-card-title>

        <v-card-text>
          {{options.text}}
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                v-show="options.confirm"
                color="cancel"
                flat
                @click="cancel"
            >
             {{options.cancel || 'Cancel' }}
          </v-btn>
          <v-btn
            color="primary"
            flat
            @click="next"
          >
            {{options.apply || 'Ok' }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import { DialogOptions } from '@/types'

@Component
export default class Dialog extends Vue {
    private dialog: boolean = false;
    private confirm: boolean = false;
    private options = {} as DialogOptions;

    show (): void {
      this.dialog = true
    }

    close (): void {
      this.dialog = false
    }

    cancel () {
      if (this.options.reject && Vue._.isFunction(this.options.reject)) {
        this.options.reject(this.options)
      }
      this.close()
    }

    next () {
      if (this.options.resolve && Vue._.isFunction(this.options.resolve)) {
        this.options.resolve(this.options)
      }
      this.close()
    }

    commit (options = {}): void {
      this.options = options
      this.show()
    }
}
</script>
