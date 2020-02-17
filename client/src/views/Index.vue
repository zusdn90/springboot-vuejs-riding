<template>
  <v-app>
    <v-navigation-drawer
      fixed
      app
    >
      <v-list dense>
        <v-list-tile >
          <v-list-tile-action>
            <v-icon>home</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Home</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile >
          <v-list-tile-action>
            <v-icon>contact_mail</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Contact</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar class="grey darken-3" dark fixed app>
      <v-toolbar-side-icon ></v-toolbar-side-icon>
      <v-toolbar-title>Application</v-toolbar-title>
    </v-toolbar>
    <v-content>
      <v-container fluid fill-height>
           <v-card
              class="pa-3"
              flat
              height="300px"
              img="https://cdn.vuetifyjs.com/images/toolbar/map.jpg"
            >
              <v-toolbar
                dense
                floating
              >
                <v-text-field
                  hide-details
                  prepend-icon="search"
                  single-line
                ></v-text-field>

                <v-btn icon>
                  <v-icon>my_location</v-icon>
                </v-btn>

                <v-btn icon>
                  <v-icon>more_vert</v-icon>
                </v-btn>
              </v-toolbar>
            </v-card>
      </v-container>
    </v-content>
      <v-footer
    dark
    height="auto"
  >
    <v-card
      class="flex"
      flat
      tile
    >
      <v-card-title class="teal">
        <strong class="subheading">Get connected with us on social networks!</strong>

        <v-spacer></v-spacer>

        <v-btn
          v-for="icon in icons"
          :key="icon"
          class="mx-3"
          dark
          icon
        >
          <v-icon size="24px">{{ icon }}</v-icon>
        </v-btn>
      </v-card-title>

      <v-card-actions class="grey darken-3 justify-center">
        &copy;2020 — <strong>riding</strong>
      </v-card-actions>
    </v-card>
  </v-footer>
  </v-app>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator"
import router from "../router/index"

@Component
export default class Index extends Vue {
  private icons: Array<any> = [
    'fab fa-facebook',
    'fab fa-twitter',
    'fab fa-linkedin',
    'fab fa-instagram'
  ]

  // ------------------------------------------------------------
  // lifecyle hook
  mounted () {
    // this.$router.push('intro')
  }
}
</script>
