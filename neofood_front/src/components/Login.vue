<template>
    <ion-card>
      <ion-card-header>
        <ion-card-title>SignIn</ion-card-title>
      </ion-card-header>
      <ion-card-content>
        <ion-item>
          <ion-label position="floating">Phone</ion-label>
          <ion-input v-model="userInfo.phone"></ion-input>
        </ion-item>
        <ion-item>
          <ion-label position="floating">Password</ion-label>
          <ion-input type="password" v-model="userInfo.password"></ion-input>
        </ion-item>
         <ion-item v-if="errorMessage">
          <ion-label color="danger" >{{errorMessage}}</ion-label>
        </ion-item>
        <ion-button expand="full" @click="handleLogin()">Login</ion-button>
      </ion-card-content>
    </ion-card>
</template>

<script>
import {
  IonCard, 
  IonCardHeader, 
  IonCardTitle,
  IonCardContent, 
  IonItem, 
  IonLabel, 
  IonInput,
  IonButton, 
  modalController
} from "@ionic/vue";
import { mapGetters, mapActions } from "vuex";

export default {
  components: {
    IonCard, IonCardHeader, IonCardTitle, IonCardContent, IonItem, IonLabel, IonInput, IonButton
  },
 data() {
    return {
      errorMessage: "",
      userInfo: {
        phone: "",
        password: "",
      },
    };
  },
  computed: {
    ...mapGetters("auth", {
      loginStatus: "getLoginStatus",
    }),
  },
  methods: {
    ...mapActions("auth", {
      loginUser: "loginUser",
    }),
    async closeModal(){
        await modalController.dismiss();
    },
    handleLogin() {
      if (this.userInfo.phone && this.userInfo.password) {
        this.loginUser(this.userInfo).then(
          () => {
            this.$router.push("/profile");
            this.closeModal();
          },
          (error) => {
            if (error.message) {
              this.errorMessage = "Please, enter correct login and password";
            }
          }
        )
      }
    },
  },
};
</script>