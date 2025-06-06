<script setup>

import {Head, useForm} from "@inertiajs/vue3";
import GuestLayout from "@/Layouts/GuestLayout.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";

defineProps({
    missatge:{
        type: String
    }
})

const form = useForm({
});

const tornar = () => {
    form.get(route('Inici'));
}
</script>

<template>
    <GuestLayout>
        <Head title="Sense permisos!" />
        <p>No has pogut realitzar l'acció.</p>
        <p>Error: {{missatge}}</p>
        <PrimaryButton @click="tornar" class="ms-4">
            Tornar
        </PrimaryButton>
    </GuestLayout>
</template>

<style scoped>

</style>
