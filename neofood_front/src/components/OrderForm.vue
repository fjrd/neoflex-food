<template>
  <div>

    <ion-item>
      <ion-label>Delivery Address</ion-label>
      <ion-input name="address" v-model="address" type="text" />
    </ion-item>
    <p>{{ errors["address"] }}</p>

    <ion-item>
      <ion-label>Card Number</ion-label>
      <ion-input name="cardNumber" v-model="cardNumber" type="text" />
    </ion-item>
    <p>{{ errors["cardNumber"] }}</p>

    <ion-item>
      <ion-label>Valid Date</ion-label>
      <ion-input name="validDate" v-model="validDate" type="date" />
    </ion-item>
    <p>{{ errors["validDate"] }}</p>

     <ion-item>
      <ion-label>First Name</ion-label>
      <ion-input name="firstName" v-model="firstName" type="text" />
    </ion-item>
    <p>{{ errors["firstName"] }}</p>

    <ion-item>
      <ion-label>Last Name</ion-label>
      <ion-input name="lastName" v-model="lastName" type="text" />
    </ion-item>
    <p>{{ errors["lastName"] }}</p>

    <ion-item>
      <ion-label>Dishes</ion-label>
      <ion-input name="dishes" v-model="dishes" type="text" />
    </ion-item>
    <p>{{ errors["dishes"] }}</p>

    <ion-item>
      <ion-label>Amount</ion-label>
      <ion-input name="amount" v-model="amount" type="text" />
    </ion-item>
    <p>{{ errors["amount"] }}</p>

  </div>
</template>

<script>
import { useField, useFormErrors } from "vee-validate";
import { IonItem, IonInput, IonLabel } from "@ionic/vue";
import * as yup from "yup";
export const orderInfoSchema = yup
  .object({
    address: yup.string().required("Address Is Required"),
    cardNumber: yup.string().required("Card Number Is Required"),
    validDate: yup.string().required("Valid Date Is Required"),
    firstName: yup.string().required("First Name Is Required"),
    lastName: yup.string().required("Last Name Is Required"),
    dishes: yup.string().required("Dishes Is Required"),
    amount: yup.string().required("Amount Is Required")
  })
  .required();
export default {
  setup() {
    const { value: address } = useField("address");
    const { value: cardNumber } = useField("cardNumber");
    const { value: validDate } = useField("validDate");
    const { value: firstName } = useField("firstName");
    const { value: lastName } = useField("lastName");
    const { value: dishes } = useField("dishes");
    const { value: amount } = useField("amount");

    return {
      address,
      cardNumber,
      validDate,
      firstName,
      lastName,
      dishes,
      amount,
      errors: useFormErrors()
    };
  },
  components: {
    IonItem,
    IonInput,
    IonLabel,
  }
};
</script>