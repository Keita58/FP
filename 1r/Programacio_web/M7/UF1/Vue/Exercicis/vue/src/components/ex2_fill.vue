<script setup lang="ts">
import {ref} from "vue";
defineProps({message:{type:String, required:true, default:"prova"}});

const valor = defineModel({default: ""});
const opcio = ref(""); // Ho posem a ref perquè no es pot tenir dos defineModel
const res = ref("");
function calc(event : Event) {
  event.preventDefault();
  if(opcio.value == "Atm") {
    res.value = "S'han passat de " + valor.value + " atmòsferes a " + ((valor.value as unknown as number)*1.01325).toFixed(3) + " bars.";
  }
  else {
    res.value = "S'han passat de " + valor.value + " bars a " + ((valor.value as unknown as number)*0.986923267).toFixed(3) + " atmosferes.";
  }
}
</script>

<template>
  <form>
    <label>Pressió:</label>
    <br>
    <input v-model="valor">
    <select v-model="opcio">
      <option disabled value="">Escull</option>
      <option>Bar</option>
      <option>Atm</option>
    </select>
    <br>
    <button @click="calc">Calcular</button>
  </form>
  <br>
  <p>{{res}}</p>
</template>

<style scoped>

</style>