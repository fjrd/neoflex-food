<template>
    <ion-card>
      <ion-card-header>
        <ion-card-title color="primary">
          <ion-label>Order ID: </ion-label>
          <ion-label>{{ order.orderId }}</ion-label>
        </ion-card-title>
      </ion-card-header>
      <ion-card-content>
        <ion-item>
          <ion-label>Customer ID:</ion-label>
          <ion-label>{{ order.customerId }}</ion-label>
        </ion-item>
        <ion-item>
          <ion-label>Address:</ion-label>
          <ion-label>{{ order.deliveryAddress }}</ion-label>
        </ion-item>
        <ion-item>
          <ion-label>Status:</ion-label>
          <ion-label>{{ order.orderStatus }}</ion-label>
        </ion-item>
        <ion-item>
          <ion-label>Dishes:</ion-label>
          <ion-label>{{ order.dishesList }}</ion-label>
        </ion-item>
         <ion-item>
          <ion-label>Payment Status:</ion-label>
          <ion-label>{{ order.paymentStatus }}</ion-label>
        </ion-item>
         <ion-item>
          <ion-label>Restaurant Status:</ion-label>
          <ion-label>{{ order.restaurantStatus }}</ion-label>
        </ion-item>
         <ion-item>
          <ion-label>Delivery Status:</ion-label>
          <ion-label>{{ order.deliveryStatus }}</ion-label>
        </ion-item>
         <ion-item>
          <ion-label>Order Time:</ion-label>
          <ion-label>{{ order.orderTime }}</ion-label>
        </ion-item>
         <ion-item>
          <ion-label>Order Counter:</ion-label>
          <ion-label>{{ order.orderCounter }}</ion-label>
        </ion-item>
         <ion-item>
          <ion-label>Order Amount:</ion-label>
          <ion-label>{{ order.orderAmount }}</ion-label>
        </ion-item>
      </ion-card-content>
    </ion-card>
</template>

<script>
// import OrdersList from "../components/OrdersList.vue"
import { IonCard, IonCardTitle, IonCardHeader, IonCardContent, IonLabel, IonItem } from "@ionic/vue";

export default {
  components: {
    IonCard,
    IonCardTitle,
    IonCardHeader,
    IonCardContent,
    IonLabel,
    IonItem
  },
  name: "OrderItem",
  props: {
      order: {
          type: Object,
          required: true
      }
  }
};
</script>