import OrderService from '../../service/order.service';

const order = JSON.parse(localStorage.getItem('order'));
const initialState = order
  ? {  order }
  : {  order: null };

export const auth = {
  namespaced: true,
  state: initialState,
  getters: {
     getOrder(state){
       return state.order;
     }
  },
  actions: {
    makeOrder({ commit }, order) {
      return OrderService.makeOrder(order).then(
        order => {
          commit('orderSuccess', order);
          return Promise.resolve(order);
        },
        error => {
          commit('orderFailure');
          return Promise.reject(error);
        }
      );
    },
  },
  mutations: {
    orderSuccess(state, order) {
      state.order = order;
    },
    orderFailure(state) {
      state.order = null;
    },
  }
};