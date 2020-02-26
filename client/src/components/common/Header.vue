<template>
    <layout>
      <v-toolbar
        :clipped-left="$vuetify.breakpoint.lgAndUp"
        class="grey darken-3"
        dark
        fixed
        app>
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
          <v-btn flat to='/login'>로그인</v-btn>
          <v-btn flat to='/signUpSteps'>회원가입</v-btn>
          <v-btn flat>이용안내</v-btn>
          <v-btn flat>공지사항</v-btn>
        </v-toolbar-items>
      </v-toolbar>
    </layout>
</template>
