<template>
  <master-layout pageTitle="Home Page">
    <ion-card>
      <img style="width:100%" src="../image/neoflex.png" />
      <ion-card-header>
        <ion-card-subtitle>Make order, please
          <ion-label v-if="errorMessage" color="danger" >{{errorMessage}}</ion-label>
        </ion-card-subtitle>
        <ion-card-title>Order form</ion-card-title>
      </ion-card-header>
      <ion-card-content>
         <OrderForm/>
      </ion-card-content>
    </ion-card>
    <ion-button @click="giveMeFormPlease()">MAKE ORDER</ion-button>
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
  data() {
    return {
      errorMessage: "",
    };
  },
  setup() {
    const theForm = useForm({
      validationSchema: orderInfoSchema
    });
    async function handleForm() {
        const resp = await theForm.validate();
        if (resp.valid) {
          return theForm.values;
        }
      }
    return {
      handleForm
      }
  },
  methods: {
    ...mapActions("orderStore", {
    makeOrder: "makeOrder",
    }),
    async giveMeFormPlease(){
        this.handleForm()
        .then(result => this.makeOrder(JSON.parse(JSON.stringify(result))))
        .then(
          () => {
            this.$router.push("/orders");
          },
          ( error ) => {
            if (error.message) {
              this.errorMessage = error.response.data.message;
          }
        }
      );   
    }
  } 
};
</script>