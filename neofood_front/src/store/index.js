import { createStore } from "vuex";
import { auth } from "./modules/auth";
import { orderStore } from "./modules/order";

const store = createStore({
  modules: {
    auth,
    orderStore
  },
});

export default store;