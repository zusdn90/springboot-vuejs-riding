<template>
<layout>
  <v-toolbar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      class="grey darken-3"
      dark
      fixed
      app
    >
      <v-toolbar-title style="width: 300px" class="ml-0 pl-3">
        <v-toolbar-side-icon></v-toolbar-side-icon>
        <span class="hidden-sm-and-down">서울 자전거</span>
      </v-toolbar-title>
      <v-text-field
        flat
        solo-inverted
        hide-details
        prepend-inner-icon="search"
        label="Search"
        class="hidden-sm-and-down"
      ></v-text-field>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-sm-and-down">
        <v-btn flat to='/index'>홈</v-btn>
        <v-btn flat to="/login">로그인</v-btn>
        <v-btn flat to="/signUpSteps">회원가입</v-btn>
        <v-btn flat>이용안내</v-btn>
        <v-btn flat>공지사항</v-btn>
      </v-toolbar-items>
    </v-toolbar>
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
