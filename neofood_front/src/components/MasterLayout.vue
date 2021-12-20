<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons @click="openMenu()" slot="start">
          <ion-icon :icon="menu" slot="end"></ion-icon>
        </ion-buttons>
        <ion-buttons v-if="!currentUser" slot="end">
          <ion-button expand="full" @click="openModal(1)" >
            <ion-icon :icon="logInOutline" slot="start"></ion-icon>
            Login
          </ion-button>
          <ion-button expand="full" @click="openModal(2)" >
            <ion-icon :icon="personAddOutline" slot="start"></ion-icon>
            Register
          </ion-button>
        </ion-buttons>
        <ion-buttons v-if="currentUser" slot="end">
          <ion-button expand="full" @click="logOut" >
            <ion-icon :icon="logOutOutline" slot="start"></ion-icon>
            LogOut
          </ion-button>
        </ion-buttons>
        <ion-title>{{ pageTitle }}</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <slot></slot>
    </ion-content>
  </ion-page>
</template>
<script>
import {
  IonPage,IonHeader,IonContent,IonTitle,IonToolbar,IonButtons, IonButton,
  menuController, modalController, IonIcon
} from "@ionic/vue";
import LoginModal from './LoginModal.vue';
import RegisterModal from './RegisterModal.vue';
import { menu, logInOutline, logOutOutline, personAddOutline } from "ionicons/icons";
import { mapGetters, mapActions } from "vuex";

export default {
  components: {
    IonPage,IonHeader,IonContent,IonTitle,IonToolbar,IonButtons, IonButton, IonIcon
  },
  props: ["pageTitle"],
  data() {
    return {
      menu,
      logInOutline,
      logOutOutline,
      personAddOutline
    };
  },
  computed: {
    ...mapGetters("auth", {
        currentUser: "getUser",
    }),
   },
  methods: {
    openMenu() {
      menuController.open("app-menu");
    },
    async openModal(id) {
      let modal = null;
      if(id === 1){
        modal =  await modalController
        .create({
          component: LoginModal,
        });
      } else if (id === 2){
        modal =  await modalController
        .create({
          component: RegisterModal,
        });
      }
      return modal.present();
    },
    logOut(){
      this.logoutUser();
      this.$router.push('/home');
    },
      ...mapActions("auth", {
      logoutUser: "logoutUser",
    }),
  },
};
</script>