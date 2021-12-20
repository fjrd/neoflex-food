import { createRouter, createWebHistory } from '@ionic/vue-router';
import Home from '../views/Home.vue';
import About from '../views/About.vue';
import Profile from '../views/Profile.vue';
import Order from '../views/Order.vue';

 
const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    component: Home
  },
  {
    path: '/about',
    component: About
  },
  {
    path: '/profile',
    component: Profile
  },
  {
    path: '/order',
    component: Order
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
