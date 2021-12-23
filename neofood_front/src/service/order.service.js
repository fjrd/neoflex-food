import axios from 'axios';
import { uuid } from 'vue-uuid'; 
import authHeader from './auth-header';

const ORDER_API_URL = 'http://localhost:8080/auth/orders';

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
                cvc: order.cvc
              },
              dishesList: order.dishes,
              orderAmount: order.amount,
            },
            { headers: authHeader() },
            )
          .then(response => response.data);
      }
    
      getOrders(id) {
        return axios.get(ORDER_API_URL + "/" + id,
        { headers: authHeader() },
        ).then(response => response.data);
      }
}

export default new OrderService();