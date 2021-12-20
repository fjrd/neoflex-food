import axios from 'axios';

const LOGIN_API_URL = 'http://localhost:8080/api/login/';
const REGISTER_API_URL = 'http://localhost:8080/auth/register/';

class AuthService {
    login(user) {
        return axios
          .post(LOGIN_API_URL,
            {
            phone: user.phone,
            password: user.password
          })
          .then(response => {
            if (response.data.jwtToken) {
              localStorage.setItem('user', JSON.stringify(response.data));
            }
    
            return response.data;
          });
      }
    
      logout() {
        localStorage.removeItem('user');
      }
    
      register(user) {
        return axios.post(REGISTER_API_URL, {
          name: user.name,
          phone: user.phone,
          password: user.password
        });
      }
}

export default new AuthService();