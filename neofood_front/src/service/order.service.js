import axios from 'axios';
import { uuid } from 'vue-uuid'; 
import authHeader from './auth-header';

const ORDER_API_URL = 'http://localhost:8080/api/v1/orders';

class OrderService {
    makeOrder(order) {
        return axios
          .post(ORDER_API_URL,
            { headers: authHeader() },
            {
            orderId: uuid.v1(),
            address: order.address,
            cardNumber: order.cardNumber,
            validDate: order.validDate,
            firstName: order.firstName,
            lastName: order.lastName,
            dishes: order.dishes,
            amount: order.amount,
          })
          .then(response => {
            if (response.data.orderId) {
              localStorage.setItem('order', JSON.stringify(response.data));
            }
            return response.data;
          });
      }
    
      getOrder(id) {
        return axios.post(ORDER_API_URL + "/" + id);
      }
}

export default new OrderService();