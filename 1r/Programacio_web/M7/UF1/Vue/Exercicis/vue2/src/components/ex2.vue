<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue';

const fotos = ref("");

axios.get('https://jsonplaceholder.typicode.com/photos')
.then(response => {
    fotos.value = response.data;
    console.log(fotos);
})
.catch(error => {
    console.log(error);
});

</script>

<template>
    <h3>Llista Albums</h3>
    <div v-for="foto in fotos.slice(0, 10)" :key="foto.id">
        <p><span>Títol: </span>{{ foto.title }}</p>
        <img :src="foto.thumbnailUrl">
    </div>
</template>

<style scoped>
span {
    font-weight: bold; 
    font-size: large
}
</style>