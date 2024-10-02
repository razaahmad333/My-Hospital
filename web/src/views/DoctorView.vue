<template>
  <div class="container-fluid mt-5">
    <div class="row">
      <div class="col-8">
        <div class="card">
          <div class="card-header d-flex justify-content-between align-items-center">
            <h3 class="card-title">Doctors</h3>
            <button class="btn btn-secondary" @click="fetchDoctors()">
              <i class="bx bx-refresh"></i> Refresh
            </button>
          </div>
          <div class="card-body">
            <table class="table table-hover">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Department</th>
                  <th>Qualification</th>
                  <th>Fee</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="doctor in doctors" :key="doctor.id">
                  <td>{{ doctor.name }}</td>
                  <td>{{ doctor.department }}</td>
                  <td>{{ doctor.qualification }}</td>
                  <td>{{ doctor.fee }}</td>
                  <td>
                    <button class="btn btn-sm btn-warning me-2" @click="editDoctor(doctor)">
                      <i class="bx bx-edit"></i> Edit
                    </button>
                    <button class="btn btn-sm btn-danger" @click="deleteDoctor(doctor.id)">
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
            <form @submit.prevent="editMode ? updateDoctor() : createDoctor()">
              <div class="form-group mb-3">
                <label for="name">Name:</label>
                <input type="text" class="form-control" v-model="doctorForm.name" required />
              </div>
              <div class="form-group mb-3">
                <label for="department">Department:</label>
                <select v-model="doctorForm.department" class="form-select" id="department">
                  <option value="">Select Department</option>
                  <option>Cardiology</option>
                  <option>Ophthalmology</option>
                </select>
              </div>
              <div class="form-group mb-3">
                <label for="qualification">Qualification:</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="doctorForm.qualification"
                  required
                />
              </div>
              <div class="form-group mb-3">
                <label for="fee">Fee:</label>
                <input type="number" class="form-control" v-model="doctorForm.fee" />
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
  </div>
</template>
<script>
export default {
  data() {
    return {
      doctors: [],
      doctorForm: {
        name: '',
        department: '',
        qualification: '',
        fee: 0
      },
      editMode: false,
      editingId: null,
      baseURL: 'http://localhost:8080/doctor'
    }
  },
  methods: {
    async fetchDoctors() {
      try {
        const response = await fetch(this.baseURL)
        this.doctors = await response.json()
      } catch (error) {
        console.error('Error fetching doctors:', error)
      }
    },
    async createDoctor() {
      try {
        const response = await fetch(this.baseURL, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(this.doctorForm)
        })
        const newTariff = await response.json()
        this.doctors.push(newTariff)
        this.resetForm()
      } catch (error) {
        console.error('Error creating doctor:', error)
      }
    },
    editDoctor(doctor) {
      this.editMode = true
      this.editingId = doctor.id
      this.doctorForm = { ...doctor }
    },
    async updateDoctor() {
      try {
        await fetch(`${this.baseURL}/${this.editingId}`, {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(this.doctorForm)
        })
        this.fetchDoctors()
        this.resetForm()
      } catch (error) {
        console.error('Error updating doctor:', error)
      }
    },
    async deleteDoctor(id) {
      try {
        await fetch(`${this.baseURL}/${id}`, {
          method: 'DELETE'
        })
        this.fetchDoctors()
      } catch (error) {
        console.error('Error deleting doctor:', error)
      }
    },
    resetForm() {
      this.doctorForm = { name: '', department: '', qualification: '', fee: 0 }
      this.editMode = false
      this.editingId = null
    }
  },
  mounted() {
    this.fetchDoctors()
  }
}
</script>
