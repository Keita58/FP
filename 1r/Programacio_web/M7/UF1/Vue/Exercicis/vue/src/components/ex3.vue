<script setup lang="ts">
import {ref} from 'vue'
defineProps({message:{type:String, required:true,default:"prova"}});

const valor = defineModel({default: ""});
const res = ref("");
let vocalsa : number;
let vocalse : number;
let vocalsi : number;
let vocalso : number;
let vocalsu : number;

function compta(event : Event) {
  event.preventDefault();
  let a : RegExpMatchArray = (valor.value as string).match(/[a]/gi) as RegExpMatchArray; //Regex per la vocal a, la g es per a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
  vocalsa = a === null ? 0 : a.length;
  let e : RegExpMatchArray = (valor.value as string).match(/[e]/gi) as RegExpMatchArray; //Regex per la vocal e, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
  vocalse = e === null ? 0 : e.length;
  let i : RegExpMatchArray = (valor.value as string).match(/[i]/gi) as RegExpMatchArray; //Regex per la vocal i, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
  vocalsi = i === null ? 0 : i.length;
  let o : RegExpMatchArray = (valor.value as string).match(/[o]/gi) as RegExpMatchArray; //Regex per la vocal o, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
  vocalso = o === null ? 0 : o.length;
  let u : RegExpMatchArray = (valor.value as string).match(/[u]/gi) as RegExpMatchArray; //Regex per la vocal u, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
  vocalsu = u === null ? 0 : u.length;

  res.value = "En el text anterior hi havia " + vocalsa + " 'a', " + vocalse + " 'e', " + vocalsi + " 'i', " + vocalso + " 'o' i " + vocalsu + " 'u'.";
}
</script>

<template>
  <form>
    <label>Introdueix una cadena de caràcters:</label> <br>
    <textarea v-model="valor"></textarea> <br>
    <button @click="compta">Comptar vocals</button>
  </form>
  <br>
  <p>{{res}}</p>
</template>

<style scoped>

</style>