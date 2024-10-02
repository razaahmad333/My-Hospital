<template>
  <div class="container text-center my-5">
    <h1 class="mb-4">Masters</h1>

    <div class="row justify-content-center">
      <!-- Doctor Card -->
      <div class="col-md-4 mb-3">
        <div class="card shadow-sm h-100">
          <div class="card-body">
            <i class="bx bx-plus-medical bx-lg mb-3"></i>
            <h5 class="card-title">Doctor</h5>
            <RouterLink to="/master-page/doctor" class="btn btn-primary mt-3">
              Manage Doctors
            </RouterLink>
          </div>
        </div>
      </div>

      <!-- Patient Card -->
      <div class="col-md-4 mb-3">
        <div class="card shadow-sm h-100">
          <div class="card-body">
            <i class="bx bxs-user bx-lg mb-3"></i>
            <h5 class="card-title">Patient</h5>
            <RouterLink to="/master-page/patient" class="btn btn-primary mt-3">
              Manage Patients
            </RouterLink>
          </div>
        </div>
      </div>

      <!-- Lab Tariff Card -->
      <div class="col-md-4 mb-3">
        <div class="card shadow-sm h-100">
          <div class="card-body">
            <i class="bx bxs-flask bx-lg mb-3"></i>
            <h5 class="card-title">Lab Tariff</h5>
            <RouterLink to="/master-page/lab-tariff" class="btn btn-primary mt-3">
              Manage Lab Tariffs
            </RouterLink>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { RouterLink } from 'vue-router'
</script>

<style scoped>
h1 {
  font-weight: bold;
}

.card {
  border-radius: 12px;
  transition: transform 0.3s ease-in-out;
}

.card:hover {
  transform: scale(1.05);
}
</style>
