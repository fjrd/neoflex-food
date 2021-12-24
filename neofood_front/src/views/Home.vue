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
    <div class="ion-text-center">
      <ion-button @click="giveMeFormPlease()">MAKE ORDER</ion-button>
    </div>
  </master-layout>
</template>

<script>
import {
  IonButton,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardTitle,
  toastController
} from "@ionic/vue";
import { useForm } from "vee-validate";
import OrderForm, { orderInfoSchema } from "../components/OrderForm.vue"
import { mapActions } from "vuex";
import { informationCircle } from 'ionicons/icons';

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
    const {errors, values, resetForm, validate} = useForm({
      validationSchema: orderInfoSchema
    });
    // function viewForm(){
    //   console.log(the)
    // }

    async function handleForm() {
        const resp = await validate();
        if (resp.valid) {
          return values;
        }
      }
    
    return {
      handleForm,
      // viewForm,
      resetForm,
      errors
    }
  },
  methods: {
    ...mapActions("orderStore", {
    makeOrder: "makeOrder",
    }),
     async openToast(message) {
      const toast = await toastController
        .create({
          message,
          icon: informationCircle,
          duration: 4000,
          position: "middle",
          color: "light"
        })
      return toast.present();
    },
    async giveMeFormPlease(){
        this.handleForm()
        .then(result => this.makeOrder(JSON.parse(JSON.stringify(result))))
        .then(
          () => {
            this.openToast('Order successful');
            this.resetForm();
          },
          ( error ) => {
            console.log(error.response.status);
            console.log(error.message);
            if (error.response.status === 401) {
              this.resetForm();
              this.$refs.observer.reset();
              console.log(this.errors);
              this.openToast('Login, please to make an order');
            }
          }
        ); 
      }
    } 
  };
</script>