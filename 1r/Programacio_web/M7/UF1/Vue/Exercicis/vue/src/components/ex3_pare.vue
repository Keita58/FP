<script setup lang="ts">
import Fill from "@/components/ex3.vue";
import {ref} from "vue";

const info = ref("");
</script>

<template>
  <Fill :message="info"/>
</template>

<style scoped>

</style>