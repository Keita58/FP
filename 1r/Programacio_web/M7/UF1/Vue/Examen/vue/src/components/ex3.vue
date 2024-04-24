<template>
    <button @click="dades">Carrega les dades</button>
    <ul>
        <li v-for="poke in pokemon" :key="poke.name">{{ poke.name }}</li>
    </ul>
</template>

<script setup lang="ts">
import axios from "axios";
import { ref } from "vue";

let pokemon = ref();
function dades(event : Event) {
    event.preventDefault();

    axios.get('https://pokeapi.co/api/v2/pokemon')
    .then(response => {
        pokemon.value = response.data.results;
    })
    .catch(error => {
        console.log(error.data);
    })
}
</script>

<style scoped>

</style>