<template>
  <div class="container-fluid mt-5">
    <div class="row">
      <div class="col-8">
        <div class="card">
          <div class="card-header d-flex justify-content-between align-items-center">
            <h3 class="card-title">Patient List</h3>
            <button class="btn btn-secondary" @click="fetchPatients()">
              <i class="bx bx-refresh"></i> Refresh
            </button>
          </div>
          <div class="card-body">
            <table class="table table-hover">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>UHID</th>
                  <th>Phone</th>
                  <th>DOB</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="patient in patients" :key="patient.id">
                  <td>{{ patient.name }}</td>
                  <td>{{ patient.uhid }}</td>
                  <td>{{ patient.phone }}</td>
                  <td>{{ patient.dob }}</td>
                  <td>
                    <button class="btn btn-sm btn-warning me-2" @click="editPatient(patient)">
                      <i class="bx bx-edit"></i> Edit
                    </button>
                    <button class="btn btn-sm btn-danger" @click="deletePatient(patient.id)">
                      <i class="bx bx-trash"></i> Delete
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="col-4">
        <div class="card mb-4">
          <div class="card-header">
            <h3 class="card-title">{{ editMode ? 'Edit' : 'Create' }}</h3>
          </div>
          <div class="card-body">
            <form @submit.prevent="editMode ? updatePatient() : createPatient()">
              <div class="form-group mb-3">
                <label for="name">Name:</label>
                <input type="text" class="form-control" v-model="patientForm.name" required />
              </div>
              <div class="form-group mb-3">
                <label for="phone">Phone:</label>
                <input type="tel" class="form-control" v-model="patientForm.phone" required />
              </div>
              <div class="form-group mb-3">
                <label for="dob">DOB:</label>
                <input type="date" class="form-control" v-model="patientForm.dob" required />
              </div>
              <div class="form-group mb-3">
                <label for="password">Password:</label>
                <input type="text" class="form-control" v-model="patientForm.password" required />
              </div>
              <button type="submit" class="btn btn-primary">
                <i :class="editMode ? 'bx bx-edit' : 'bx bx-plus'"></i>
                {{ editMode ? 'Update' : 'Create' }}
              </button>
              <button type="button" @click="resetForm()" class="btn btn-primary mx-2">
                <i class="bx bx-reset"></i>
                Reset
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- Patient Form -->

    <!-- Patient List -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      patients: [],
      patientForm: {
        name: '',
        dob: '',
        phone: '',
        password: ''
      },
      editMode: false,
      editingId: null,
      baseURL: 'http://localhost:8080/patient'
    }
  },
  methods: {
    async fetchPatients() {
      try {
        const response = await fetch(this.baseURL)
        this.patients = await response.json()
      } catch (error) {
        console.error('Error fetching patients:', error)
      }
    },
    async createPatient() {
      try {
        await fetch(this.baseURL, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(this.patientForm)
        })
        this.fetchPatients()
        this.resetForm()
      } catch (error) {
        console.error('Error creating patient:', error)
      }
    },
    editPatient(patient) {
      this.editMode = true
      this.editingId = patient.id
      this.patientForm = { ...patient }
    },
    async updatePatient() {
      try {
        await fetch(`${this.baseURL}/${this.editingId}`, {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(this.patientForm)
        })
        this.fetchPatients()
        this.resetForm()
      } catch (error) {
        console.error('Error updating patient:', error)
      }
    },
    async deletePatient(id) {
      try {
        await fetch(`${this.baseURL}/${id}`, {
          method: 'DELETE'
        })
        this.fetchPatients()
      } catch (error) {
        console.error('Error deleting patient:', error)
      }
    },
    resetForm() {
      this.patientForm = { name: '', dob: '', phone: '', password: '' }
      this.editMode = false
      this.editingId = null
    }
  },
  mounted() {
    this.fetchPatients()
  }
}
</script>
