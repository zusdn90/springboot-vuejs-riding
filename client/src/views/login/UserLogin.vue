<template>
<layout>
  <h-header/>
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="grey darken-1">
                <v-toolbar-title>Seoul City Riding</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field prepend-icon="person" name="login" label="Login" type="text"></v-text-field>
                  <v-text-field id="password" prepend-icon="lock" name="password" label="Password" type="password"></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  round large dark ripple
                  color="teal"
                  @click="authUser">
                  Login
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
    <h-footer/>
</layout>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator"

@Component
export default class UserLogin extends Vue {
  // ------------------------------------------------------------
  // lifecyle hook
  mounted () {
    // this.$router.replace('intro')
  }
}
</script>
