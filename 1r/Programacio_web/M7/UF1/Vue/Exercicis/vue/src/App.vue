<script setup lang="ts">
import ex1 from "@/components/ex1.vue";
import ex2 from "@/components/ex2.vue";
import ex3 from "@/components/ex3.vue";
import ex4 from "@/components/ex4.vue";
</script>

<template>
  <ex4></ex4>
  <!--<ex1></ex1> <br>
  <ex2></ex2> <br>
  <ex3></ex3>-->
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
