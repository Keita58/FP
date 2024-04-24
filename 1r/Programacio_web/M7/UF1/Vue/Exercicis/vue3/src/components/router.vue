<template>
    <nav class="d-flex w-100 justify-content-center align-content-center col-2 bg-warning">
    <router-link to="/" class="w-100 flex-grow-1 text-center text-decoration-none text-light">Home</router-link>
    <router-link to="/Ex1" class="w-100 flex-grow-1 text-center text-decoration-none text-light">Ex1</router-link>
    <router-link to="/Ex2" class="w-100 flex-grow-1 text-center text-decoration-none text-light">Ex2</router-link>
    <router-link to="/Ex3" class="w-100 flex-grow-1 text-center text-decoration-none text-light">Ex3</router-link>
  </nav>
  <router-view></router-view>
</template>

<script setup lang="ts">

</script>

<style scoped>

</style>