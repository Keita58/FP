<template>
    <p>Missatge: {{ text }}</p>
    <button @click="numeros">Calcula</button>
    <img :src="foto" :style="mida">
</template>

<script setup lang="ts">
import { ref } from 'vue';

const foto = ref("");
const text = ref("");
const mida = ref("");

function numeros(event : Event) {
    event.preventDefault();
    let num1 : number = Math.floor(Math.random() * (10 - 1 + 1) + 1);
    let num2 : number = Math.floor(Math.random() * (10 - 1 + 1) + 1);
    let num3 : number = Math.floor(Math.random() * (10 - 1 + 1) + 1);
    if(num1 != num2 && num1 != num3 && num2 != num3) {
        foto.value = "https://st3.depositphotos.com/3159197/13432/i/1600/depositphotos_134326516-stock-photo-3d-sad-emoji-crying.jpg"
        text.value = "Són diferents. A igual a " + num1 + ", B igual a " + num2 + ", C igual a " + num3 + ".";
    }
    else if(num1 == num2 && num2 == num3) {
        foto.value = "https://as2.ftcdn.net/v2/jpg/06/03/31/39/1000_F_603313918_uDhbGxRhxPALbuuKSNdsoSCFx5R5uaKf.jpg"
        text.value = "Tots són iguals. A igual a " + num1 + ", B igual a " + num2 + ", C igual a " + num3 + ".";
    }
    else {
        foto.value = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ11pFYqpHZeG5tqMTmOYtjJiJBmqgZx-9kBw&usqp=CAU"
        text.value = "Hi ha 2 iguals. A igual a " + num1 + ", B igual a " + num2 + ", C igual a " + num3 + ".";
    }
    mida.value = "width: 100px; heigth: 100px";
}
</script>

<style scoped>

</style>