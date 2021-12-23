import OrderService from '../../service/order.service';

const userId = JSON.parse(localStorage.getItem('user'))
  ? JSON.parse(localStorage.getItem('user')).id
  : null;

function serializeResponse(orders) {
  return orders.reduce((acc, order) => {
    acc[order.orderId] = order;
    return acc;
  }, {});
}

export const orderStore = {
  namespaced: true,
  state: {
    orders: {}
  },
  getters: {
    ordersList: ({ orders }) => orders,
  },
  actions: {
    initOrdersStore: {
      handler({ dispatch }) {
        dispatch('fetchOrders');
      },
      root: true,
    },
    async fetchOrders({ commit }) {
      if(userId){
        const response = await OrderService.getOrders(userId);
        const orders = serializeResponse(response);
        commit('orders', orders);
      }
       
    },
    makeOrder({ commit }, order) {
      return OrderService.makeOrder(order).then(
        order => {
          commit('orderSuccess', order);
          return Promise.resolve(order);
        },
        error => {
          return Promise.reject(error);
        }
      );
    },
    clearOrders({ commit }) {
      commit('clearOrders');
    }
  },
  mutations: {
    orders(state, value) {
      state.orders = value;
    },
    orderSuccess(state, order){
      state.orders[order.orderId] = order;
    },
    clearOrders(state) {
      state.orders = {};
    }
 
  }
};