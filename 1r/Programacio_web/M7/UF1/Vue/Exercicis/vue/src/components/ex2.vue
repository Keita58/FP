<script setup lang="ts">
import Fill from "./ex2_fill.vue";
import {ref} from 'vue'

const info = ref("");
</script>

<template>
  <Fill :message="info"/>
</template>

<style scoped>

</style>