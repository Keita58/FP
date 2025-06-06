<script setup>
import 'bootstrap/dist/css/bootstrap.css';
import GuestLayout from "@/Layouts/GuestLayout.vue";
import {Head, useForm} from "@inertiajs/vue3";
import PrimaryButton from "@/Components/PrimaryButton.vue";
import InputLabel from "@/Components/InputLabel.vue";
import TextInput from "@/Components/TextInput.vue";
//import {ref} from "vue";

defineProps({
    rol:{
        type:String
    },
});

const form = useForm({
    idRol: 0,
    nom:'',
});

//const selectedOption = ref(null);
const editRol = (idRol) => {
    form.idRol = idRol;
    form.post(route('updateRol'));
};

</script>

<template>
    <GuestLayout>
        <Head title="Actualitzar Rols" />
        <form>
            <div>
                <p>Nom: {{rol.nom}}</p>
                <InputLabel for="nom" value="Nou nom:" />
                <TextInput
                    id="nomNou"
                    type="text"
                    class="mt-1 block w-full"
                    v-model="form.nom"
                    required
                    autofocus
                    autocomplete="nom"
                />
            </div>
            <div class="flex items-center justify-end mt-4">
                <PrimaryButton @click="editRol(rol.idRols)" class="ms-4" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                    Actualitzar
                </PrimaryButton>
            </div>
        </form>
    </GuestLayout>
</template>

<style scoped>

</style>
