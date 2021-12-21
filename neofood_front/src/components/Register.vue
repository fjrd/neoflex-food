<template>
    <ion-card>
      <ion-card-header>
        <ion-card-title>Register, please</ion-card-title>
      </ion-card-header>
      <ion-card-content>
        <ion-item>
          <ion-label position="floating">Name(3-30 symbols)</ion-label>
          <ion-input v-model="userInfo.name"></ion-input>
        </ion-item>
        <ion-item>
          <ion-label position="floating">Phone(8-12 symbols)</ion-label>
          <ion-input v-model="userInfo.phone"></ion-input>
        </ion-item>
        <ion-item>
          <ion-label position="floating">Password(3-15 symbols)</ion-label>
          <ion-input type="password" v-model="userInfo.password"></ion-input>
        </ion-item>
         <ion-item v-if="errorMessage">
          <ion-label color="danger" >{{errorMessage}}</ion-label>
        </ion-item>
        <ion-button expand="full" @click="handleRegister()">Register</ion-button>
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
        name: "",
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
    register: "registerUser",
    }),
    async closeModal() {
      await modalController.dismiss();
    },
    handleRegister() {
      if (this.userInfo.name && this.userInfo.phone && this.userInfo.password) {
        this.register(this.userInfo).then(
          () => {
            this.$router.push("/home");
            this.closeModal();
          },
          (error) => {
            if (error.message) {
              this.errorMessage = error.response.data.message;            }
          }
        )
      }
    },
  },
};
</script>