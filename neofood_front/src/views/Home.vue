<template>
  <master-layout pageTitle="Home Page">
    <ion-card>
      <img style="width:100%" src="../image/neoflex.png" />
      <ion-card-header>
        <ion-card-subtitle>Make order, please</ion-card-subtitle>
        <ion-card-title>Order form</ion-card-title>
      </ion-card-header>
      <ion-card-content>
         <OrderForm/>
      </ion-card-content>
    </ion-card>
    <ion-button @click="handleMakeOrder">MAKE ORDER</ion-button>
  </master-layout>
</template>

<script>
import {
  IonButton,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardTitle
} from "@ionic/vue";
import { useForm } from "vee-validate";
import OrderForm, { orderInfoSchema } from "../components/OrderForm.vue"
import { mapActions } from "vuex";

export default {
components: {
    IonButton,
    IonCard,
    IonCardContent,
    IonCardHeader,
    IonCardTitle,
    OrderForm
  },
  setup() {
    const theForm = useForm({
      validationSchema: orderInfoSchema
    });
    return {
      handleMakeOrder: async () => {
        const resp = await theForm.validate();
        if (resp.valid) {
          const orderInfo = theForm.values;
          this.makeOrder(orderInfo);
          this.$router.push("/order");
        }
      }
    };
  },
  methods: {
    ...mapActions("order", {
    makeOrder: "makeOrder",
    }),
  }
};
</script>