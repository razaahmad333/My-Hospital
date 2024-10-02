import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },

    {
      path: '/master-page/doctor',
      name: 'doctor',
      component: () => import('../views/DoctorView.vue')
    },

    {
      path: '/master-page/patient',
      name: 'patient',
      component: () => import('../views/PatientView.vue')
    },
    {
      path: '/master-page/lab-tariff',
      name: 'lab tariff',
      component: () => import('../views/LabTariffView.vue')
    },
    {
      path: '/master-page',
      name: 'master page',
      component: () => import('../views/MasterView.vue')
    },
    {
      path: '/book-lab-test',
      name: 'book lab test',
      component: () => import('../views/BookLabTestView.vue')
    }
  ]
})

export default router
