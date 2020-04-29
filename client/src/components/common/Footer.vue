<template>
  <v-footer dark height="auto">
    <v-card class="flex" flat tile>
      <v-card-title class="teal">
        <strong class="subheading">Get connected with us on social networks!</strong>

        <v-spacer></v-spacer>

        <v-btn v-for="icon in icons" :key="icon" class="mx-3" dark icon>
          <v-icon size="24px">{{ icon }}</v-icon>
        </v-btn>
      </v-card-title>

      <v-card-actions class="grey darken-3 justify-center">
        &copy;2020 — <strong>따릉이 토이 프로젝트</strong>
      </v-card-actions>
    </v-card>
  </v-footer>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator"

@Component
export default class Index extends Vue {
  private icons: Array<any> = [
    "fab fa-facebook",
    "fab fa-twitter",
    "fab fa-linkedin",
    "fab fa-instagram"
  ]
}
</script>
