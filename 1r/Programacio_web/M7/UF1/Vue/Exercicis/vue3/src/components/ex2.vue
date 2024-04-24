<template>
    <transition name="slide-fade">
      <div v-if="mostra">
        <p :style="estil">Efecte d'aparèixer i desaparèixer</p>
      </div>
    </transition>
    <button @click="mostra = true">Apareix</button>
    <button @click="mostra = false">Desapareix</button>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const mostra = ref(false);
const estil = {
    backgroundColor: "violet",
}
</script>

<style scoped>
.slide-fade-enter-active,
.slide-fade-leave-active {
  transition: 3s ease-out;
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  opacity: 0;
}

</style>