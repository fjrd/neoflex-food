<template>
  <master-layout pageTitle="MyProfile">
    <ion-card v-if="user">
      <ion-card-header>
        <ion-card-title>{{user.name}}</ion-card-title>
      </ion-card-header>
      <ion-card-content>
        <ion-item>
          <ion-label>ID:</ion-label>
          <ion-label>{{ user.id }}</ion-label>
        </ion-item>
        <ion-item>
          <ion-label>Phone:</ion-label>
          <ion-label>{{ user.phone }}</ion-label>
        </ion-item>
        <ion-item>
          <ion-label>Token:</ion-label>
          <ion-label>{{ user.jwtToken }}</ion-label>
        </ion-item>
      </ion-card-content>
    </ion-card>
  </master-layout>
</template>

<script>
import {
  IonCard,IonCardHeader,IonCardTitle,IonCardContent,IonItem,IonLabel,
} from "@ionic/vue";
import { mapGetters } from "vuex";
export default {
  components: {
    IonCard,IonCardHeader,IonCardTitle,IonCardContent,IonItem,IonLabel,
  },
  computed: {
    ...mapGetters("auth", {
      user: "getUser",
    }),
  },
  methods: {
    
  }
};
</script>