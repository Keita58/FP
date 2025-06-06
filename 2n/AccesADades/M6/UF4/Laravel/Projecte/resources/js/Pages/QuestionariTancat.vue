<script setup>

import PrimaryButton from "@/Components/PrimaryButton.vue";

const redirigir=()=>{
    post(route("RedirigirQuestionariTancat"))
}

</script>

<template>
<header>
    <h1>El qüestionari està tancat i no es pot respondre</h1>
</header>
    <div class="flex mt-4">
        <PrimaryButton @click="redirigir" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
            Tancar
        </PrimaryButton>
    </div>
</template>

<style scoped>

</style>
