<script setup>
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import {useForm} from "@inertiajs/vue3";
import InputLabel from "@/Components/InputLabel.vue";
import TextInput from "@/Components/TextInput.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";

const form = useForm({
    nom:'',
})
const afegir = () => {
    form.post(route('addRol'));
};

</script>

<template>
    <AuthenticatedLayout>
        <Head title="Afegir Rols" />
        <form>
            <div>
                <InputLabel for="nom" value="Nom:" />
                <TextInput
                    id="nom"
                    type="text"
                    class="mt-1 block w-full"
                    v-model="form.nom"
                    required
                    autofocus
                    autocomplete="nom"
                />
            </div>
            <div class="flex items-center justify-end mt-4">
                <PrimaryButton @click="afegir" class="ms-4" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                    Afegir
                </PrimaryButton>
            </div>
        </form>
    </AuthenticatedLayout>

</template>

<style scoped>

</style>
