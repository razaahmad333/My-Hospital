<template>
  <div class="container-fluid mt-5">
    <div class="row">
      <div class="col-8">
        <!-- Lab Tariff List -->
        <div class="card">
          <div class="card-header d-flex justify-content-between align-items-center">
            <h3 class="card-title">Lab Tariff List</h3>
            <button class="btn btn-secondary" @click="fetchLabTariffs()">
              <i class="bx bx-refresh"></i> Refresh
            </button>
          </div>
          <div class="card-body">
            <table class="table table-hover">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Code</th>
                  <th>Price</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="tariff in labTariffs" :key="tariff.id">
                  <td>{{ tariff.name }}</td>
                  <td>{{ tariff.code }}</td>
                  <td>{{ tariff.price }}</td>
                  <td>
                    <button class="btn btn-sm btn-warning me-2" @click="editLabTariff(tariff)">
                      <i class="bx bx-edit"></i> Edit
                    </button>
                    <button class="btn btn-sm btn-danger" @click="deleteLabTariff(tariff.id)">
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
        <!-- Form for Creating/Editing Lab Tariffs -->
        <div class="card mb-4">
          <div class="card-header">
            <h3 class="card-title">{{ editMode ? 'Edit' : 'Create' }}</h3>
          </div>
          <div class="card-body">
            <form @submit.prevent="editMode ? updateLabTariff() : createLabTariff()">
              <div class="form-group mb-3">
                <label for="name">Name:</label>
                <input type="text" class="form-control" v-model="labTariffForm.name" required />
              </div>
              <div class="form-group mb-3">
                <label for="code">Code:</label>
                <input type="text" class="form-control" v-model="labTariffForm.code" />
              </div>
              <div class="form-group mb-3">
                <label for="price">Price:</label>
                <input type="number" class="form-control" v-model="labTariffForm.price" />
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
      labTariffs: [],
      labTariffForm: {
        name: '',
        code: '',
        price: 0
      },
      editMode: false,
      editingId: null
    }
  },
  methods: {
    async fetchLabTariffs() {
      try {
        const response = await fetch('http://localhost:8080/lab-tariff')
        this.labTariffs = await response.json()
      } catch (error) {
        console.error('Error fetching lab tariffs:', error)
      }
    },
    async createLabTariff() {
      try {
        const response = await fetch('http://localhost:8080/lab-tariff', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(this.labTariffForm)
        })
        const newTariff = await response.json()
        this.labTariffs.push(newTariff)
        this.resetForm()
      } catch (error) {
        console.error('Error creating lab tariff:', error)
      }
    },
    editLabTariff(tariff) {
      this.editMode = true
      this.editingId = tariff.id
      this.labTariffForm = { ...tariff }
    },
    async updateLabTariff() {
      try {
        await fetch(`http://localhost:8080/lab-tariff/${this.editingId}`, {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(this.labTariffForm)
        })
        this.fetchLabTariffs()
        this.resetForm()
      } catch (error) {
        console.error('Error updating lab tariff:', error)
      }
    },
    async deleteLabTariff(id) {
      try {
        await fetch(`http://localhost:8080/lab-tariff/${id}`, {
          method: 'DELETE'
        })
        this.fetchLabTariffs()
      } catch (error) {
        console.error('Error deleting lab tariff:', error)
      }
    },
    resetForm() {
      this.labTariffForm = { name: '', code: '', price: '' }
      this.editMode = false
      this.editingId = null
    }
  },
  mounted() {
    this.fetchLabTariffs()
  }
}
</script>
