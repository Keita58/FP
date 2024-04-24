<template>
    <div :style="divPre">
        <button @click="mostra = !mostra">Toggle</button>
        <transition name="fade">
            <div v-if="mostra" :style="div"></div>
        </transition>
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const mostra = ref(true);

let div = {
    width: "100px",
    height: "100px",
    backgroundColor: "lime"
};

const divPre = {
    margin: "25px"
}
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 2s ease-out;
}

.fade-enter-from,
.fade-leave-to {
  transform: translateY(50px) rotateZ(45deg);
  opacity: 0;
}
</style>