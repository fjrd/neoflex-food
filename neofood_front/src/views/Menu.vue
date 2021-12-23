<template>
  <ion-menu menu-id="app-menu" content-id="main-content">
    <ion-header>
      <ion-toolbar>
        <ion-title>Welcome!</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <ion-list>
        <ion-item button @click="menuNavigation('/home')">
          <ion-label>Home</ion-label>
        </ion-item>
        <ion-item v-if="currentUser" button @click="menuNavigation('/profile')">
          <ion-label>Profile</ion-label>
        </ion-item>
        <ion-item v-if="currentUser" button @click="menuNavigation('/orders')">
          <ion-label>My orders</ion-label>
        </ion-item>
        <ion-item button @click="menuNavigation('/about')">
          <ion-label>About</ion-label>
        </ion-item>
      </ion-list>
    </ion-content>
  </ion-menu>
</template>
<script>
import {
  IonMenu,IonHeader,IonContent,IonToolbar,IonTitle,IonItem,IonList,IonLabel,
  menuController
} from "@ionic/vue";
import { mapGetters } from "vuex";

export default {
  components: {
    IonMenu,IonHeader,IonContent,IonToolbar,IonTitle,IonItem,IonList,IonLabel,
  },
  computed: {
    ...mapGetters("auth", {
        currentUser: "getUser",
    }),
   },
  methods:{
    menuNavigation(url){
      menuController.close("app-menu");
      this.$router.push(url);
    },
  }
};
</script>