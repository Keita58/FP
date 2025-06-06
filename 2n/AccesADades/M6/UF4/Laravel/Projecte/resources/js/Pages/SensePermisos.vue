<script setup>
import GuestLayout from "@/Layouts/GuestLayout.vue";
import {Head, useForm} from "@inertiajs/vue3";
import PrimaryButton from "@/Components/PrimaryButton.vue";

const form = useForm ({
})

const tornar = () => {
    form.get(route('Inici'));
}
</script>

<template>
    <GuestLayout>
        <Head title="Sense permisos!" />
        <p>No tens els permisos correctes per accedir a aquest menú.</p>
        <PrimaryButton @click="tornar" class="ms-4">
            Tornar
        </PrimaryButton>
    </GuestLayout>
</template>

<style scoped>

</style>
