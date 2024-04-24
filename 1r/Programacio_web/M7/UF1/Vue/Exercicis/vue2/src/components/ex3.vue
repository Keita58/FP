<script setup lang="ts">
import { ref, watch } from 'vue';

const llista = ref("Vermell");
const color = ref("");
const text = ref("");
const p = {
    marginBottom: 0
}
enum Colors {
    Vermell = "red",
    Blau = "blue",
    Verd = "darkgreen",
    Porpra = "darkviolet",
    Groc = "yellow"
}

let constKeys: string[] = Object.keys(Colors);
let nomsColors: string[] = Object.values(Colors);

/* Això es per a que la pàgina no inicialitzi sense color*/
color.value = Colors.Vermell;
llista.value = "red";
text.value = "Vermell";

let div = {
    width: '100px',
    height: '100px',
    border: '3px solid black',
    backgroundColor: color.value
};

watch(llista, () => {
    div.backgroundColor = llista.value;
    for(let i = 0; i < nomsColors.length; i++) {
        if(nomsColors[i] == llista.value)
            text.value = constKeys[i];
    }
})
</script>

<template>
    <form>
        <label>Color seleccionat:</label>
        <select v-model="llista">
            <option v-for="(key, value) in constKeys" :value="nomsColors[value]" :key="key">{{ key }}</option>
        </select>
    </form>
    <p :style="p">{{ text }}</p>
    <div :style="div"></div>
</template>

<style scoped>

</style>