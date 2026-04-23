<template>
  <div class="container">
    <h1>Employee Directory</h1>
    <h2>This is From Jenkins</h2>

    <p v-if="loading">Loading Employees...</p>

    <ul v-else>
      <li v-for = 'employee in employees' :key = 'employee.id'>
        {{ employee.name }} - {{ employee.department }}
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const employees = ref([])
const loading = ref(true)

onMounted(async () => {
  const response = await fetch('http://13.238.201.139:8080/api/employees')
  employees.value = await response.json()
  loading.value = false
})
</script>

<style>
.container {
  padding : 24px;
  font-family: Arial, Helvetica, sans-serif;
}
</style>