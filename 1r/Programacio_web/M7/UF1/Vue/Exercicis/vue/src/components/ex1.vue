<script setup lang="ts">
import {computed, ref} from "vue";

const valor = defineModel({default: ""});
const text = ref(""); // Ho posem amb ref perquè al ser un botó necessita actualitzar-se per a funcionar
function mostra(event:Event) {
  event.preventDefault();
  let rand : number = Math.floor(Math.random() * (100 - 1 + 1) + 1);
  if(valor.value as unknown as number <= rand && valor.value as unknown as number > 0) {
    text.value = "El número " + valor.value + " està dintre de l'interval " + rand + ".";
  }
  else {
    text.value = "El número " + valor.value + " NO està dintre de l'interval " + rand + ".";
  }
}
</script>

<template>
  <form>
    <label>Numero:</label> <br>
    <input v-model="valor"> <br>
    <button @click="mostra">Validar</button>
  </form>
  <br>
  <p>{{text}}</p>
</template>

<style scoped>

</style>