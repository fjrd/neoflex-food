<template>
  <master-layout pageTitle="My Order Info">
    <OrdersList :list="ordersList"/>
  </master-layout>
</template>

<script>
import { mapGetters } from "vuex";
import OrdersList from "../components/OrdersList.vue"
export default {
  components: {
    OrdersList
  },
  computed: {
        ...mapGetters("orderStore", {
        ordersList: "ordersList",
    }),
  },
};
</script>