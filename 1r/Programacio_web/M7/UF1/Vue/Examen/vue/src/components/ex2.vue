<template>
    <fill :res="valor"></fill>
    <p :style="color">{{ valor }}</p>
</template>

<script setup lang="ts">
import fill from "@/components/ex2_fill.vue"
import { ref } from "vue";

let valor = ref();
let color = {}
valor.value *= 0.98692;
if(valor.value < 100) {
    color = {
        backgroundColor: "blue"
    }
}
else {
    color = {
        backgroundColor: "purple"
    }
}

</script>

<style scoped>

</style>