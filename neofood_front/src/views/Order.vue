<template>
  <master-layout pageTitle="My Order Info">
    <ion-card v-if="order">
      <ion-card-header>
        <ion-card-title>{{order.orderId}}</ion-card-title>
      </ion-card-header>
      <ion-card-content>
        <ion-item>
          <ion-label>Address:</ion-label>
          <ion-label>{{ order.deliveryAddress }}</ion-label>
        </ion-item>
        <ion-item>
          <ion-label>Dishes:</ion-label>
          <ion-label>{{ order.dishesList }}</ion-label>
        </ion-item>
        <ion-item>
          <ion-label>Amount:</ion-label>
          <ion-label>{{ order.orderAmount }}</ion-label>
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
    ...mapGetters("orderStore", {
    order: "getOrder",
    }),
  },
};
</script>