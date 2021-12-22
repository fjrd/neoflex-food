import axios from 'axios';
import { uuid } from 'vue-uuid'; 
import authHeader from './auth-header';

const ORDER_API_URL = 'http://localhost:8080/api/v1/orders';

class OrderService {
    makeOrder(order) {
        return axios
          .post(ORDER_API_URL,
            {
              orderId: uuid.v1(),
              deliveryAddress: order.address,
              cardDetails: {
                cardNumber: order.cardNumber,
                validDate: order.validDate,
                firstName: order.firstName,
                lastName: order.lastName,
              },
              dishesList: order.dishes,
              orderAmount: order.amount,
            },
            { headers: authHeader() },
            )
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