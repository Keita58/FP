<template>
    <h1>Component fill</h1>
    <form>
        <label>Numero 1:</label>
        <input v-model="num1">
        <br>
        <label>Numero 2:</label> 
        <input v-model="num2">
        <button @click="calcul">Calcular</button>
    </form>
</template>

<script setup lang="ts">
import { ref } from 'vue';

defineProps({res:{type: Number, default: 0}});

let num1 = ref();
let num2 = ref();

function calcul(event : Event) {
    event.preventDefault();

    if(num1.value%2 == 0) {
        res.value = ((num1.value) * (num2.value));
    }
    else {
        res.value = ((num1.value) / (num2.value));
    }
}
</script>

<style scoped>

</style>