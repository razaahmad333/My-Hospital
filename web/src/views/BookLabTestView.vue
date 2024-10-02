<template>
  <div class="container mt-5">
    <h2 class="mb-4 text-center">Book Lab Test</h2>

    <!-- Select Patient -->
    <div class="form-group mb-2">
      <label for="patient">Select Patient:</label>
      <div class="input-group">
        <select class="form-control" v-model="selectedPatient" @change="fetchPatientDetails">
          <option value="" disabled>Select a patient</option>
          <option v-for="patient in patients" :key="patient.id" :value="patient">
            {{ patient.name }} - {{ patient.uhid }}
          </option>
        </select>
      </div>
      <div v-if="selectedPatient" class="mt-3 card p-3">
        <p><strong>Phone:</strong> {{ selectedPatient.phone }}</p>
        <p><strong>Date of Birth:</strong> {{ selectedPatient.dob }}</p>
      </div>
    </div>

    <!-- Select Lab Tariff -->
    <div class="form-group mb-2">
      <label for="labTariff">Select Lab Tariff:</label>
      <div class="input-group">
        <select class="form-control" v-model="selectedLabTariff">
          <option value="" disabled>Select a lab tariff</option>
          <option v-for="tariff in labTariffs" :key="tariff.id" :value="tariff">
            {{ tariff.name }} - {{ tariff.price }} INR
          </option>
        </select>
      </div>
      <div v-if="selectedLabTariff" class="mt-3 card p-3">
        <p><strong>Code:</strong> {{ selectedLabTariff.code }}</p>
        <p><strong>Price:</strong> {{ selectedLabTariff.price }} INR</p>
      </div>
    </div>

    <!-- Adjust Price and Discount -->
    <div class="form-row">
      <div class="form-group mb-2 col-md-6">
        <label for="adjustedPrice">Adjust Price:</label>
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text">INR</span>
          </div>
          <input type="number" class="form-control" v-model="adjustedPrice" :min="0" />
        </div>
      </div>
      <div class="form-group mb-2 col-md-6">
        <label for="discount">Discount (%):</label>
        <div class="input-group">
          <input type="number" class="form-control" v-model="discount" :min="0" :max="100" />
        </div>
      </div>
    </div>
    <div class="alert alert-info">
      <strong>Total Price after Discount:</strong> {{ finalPrice }} INR
    </div>

    <!-- Select Prescribing Doctor -->
    <div class="form-group mb-2">
      <label for="doctor">Select Prescribing Doctor:</label>
      <div class="input-group">
        <select class="form-control" v-model="selectedDoctor">
          <option value="" disabled>Select a doctor</option>
          <option v-for="doctor in doctors" :key="doctor.id" :value="doctor">
            {{ doctor.name }} - {{ doctor.department }}
          </option>
        </select>
      </div>
      <div v-if="selectedDoctor" class="mt-3 card p-3">
        <p><strong>Qualification:</strong> {{ selectedDoctor.qualification }}</p>
        <p><strong>Fee:</strong> {{ selectedDoctor.fee }} INR</p>
      </div>
    </div>

    <div class="form-group mb-2 col-md-6">
      <label for="paidAmount">Paid Amount:</label>
      <div class="input-group">
        <input type="number" class="form-control" v-model="paidAmount" :min="0" />
      </div>
    </div>

    <!-- Book Lab Test Button -->
    <div class="text-center">
      <button class="btn btn-success btn-lg" @click="bookLabTest">
        <i class="fas fa-book-medical"></i> Book Lab Test
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      patients: [],
      labTariffs: [],
      doctors: [],
      selectedPatient: null,
      selectedLabTariff: null,
      selectedDoctor: null,
      adjustedPrice: 0,
      discount: 0,
      paidAmount: 0
    }
  },
  watch: {
    selectedLabTariff(newTariff) {
      if (newTariff) {
        this.adjustedPrice = newTariff.price
      } else {
        this.adjustedPrice = 0
      }
    }
  },
  computed: {
    finalPrice() {
      if (!this.adjustedPrice) return 0
      return this.adjustedPrice - (this.adjustedPrice * this.discount) / 100
    }
  },
  methods: {
    async fetchPatients() {
      const response = await fetch('http://localhost:8080/patient')
      this.patients = await response.json()
    },
    async fetchLabTariffs() {
      const response = await fetch('http://localhost:8080/lab-tariff')
      this.labTariffs = await response.json()
    },
    async fetchDoctors() {
      const response = await fetch('http://localhost:8080/doctor')
      this.doctors = await response.json()
    },
    async bookLabTest() {
      if (!this.selectedPatient || !this.selectedLabTariff || !this.selectedDoctor) {
        alert('Please fill all the details')
        return
      }

      const payload = {
        patientId: this.selectedPatient.id,
        labTariffId: this.selectedLabTariff.id,
        discount: this.discount,
        price: this.finalPrice,
        doctorId: this.selectedDoctor.id,
        paidAmount: this.paidAmount
      }

      try {
        const response = await fetch('http://localhost:8080/lab-test/book', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload)
        })

        if (response.ok) {
          alert('Lab test booked successfully!')
        } else {
          console.error('Error booking lab test')
        }
      } catch (error) {
        console.error('Error:', error)
      }
    }
  },
  mounted() {
    this.fetchPatients()
    this.fetchLabTariffs()
    this.fetchDoctors()
  }
}
</script>

<style scoped>
.container {
  max-width: 700px;
}
</style>
