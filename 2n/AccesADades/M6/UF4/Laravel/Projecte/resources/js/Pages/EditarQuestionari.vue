<script setup>
import 'bootstrap/dist/css/bootstrap.css';
import GuestLayout from "@/Layouts/GuestLayout.vue";
import {Head, useForm} from "@inertiajs/vue3";
import PrimaryButton from "@/Components/PrimaryButton.vue";
import InputLabel from "@/Components/InputLabel.vue";
import TextInput from "@/Components/TextInput.vue";
//import {ref} from "vue";

defineProps({
    questionari:{
        type:String
    },
});

const form = useForm({
    idQuestionari: 0,
    idEmpresa:0,
    descripcio:''
});

//const selectedOption = ref(null);
const editQuestionari = (idQuestionari) => {
    form.idQuestionari = idQuestionari;
    form.post(route('updateQuest'));
};
const checkDigit = (KeyboardEvent) => {
    if (event.key.length === 1 && isNaN(Number(event.key))) {
        event.preventDefault();
    }
}
</script>

<template>
    <GuestLayout>
        <Head title="Actualitzar Rols" />
        <form>
            <div>
                <p>Id de l'empresa: {{questionari.idEmpresa}}</p>
                <p>Descripció: {{questionari.descripcio}}</p>
                <InputLabel for="idEmpresa" value="Id de l'empresa: " />
                <input
                    id="idEmpresa"
                    type="number"
                    class="mt-1 block w-full"
                    v-model="form.idEmpresa"
                    required
                    autofocus
                    @keydown="checkDigit"
                    autocomplete="Id Empresa"
                />
                <InputLabel for="descripcio" value="Descripció: " />
                <TextInput
                    id="descripcio"
                    type="text"
                    class="mt-1 block w-full"
                    v-model="form.descripcio"
                    required
                    autofocus
                    autocomplete="Descripció"
                />
            </div>
            <div class="flex items-center justify-end mt-4">
                <PrimaryButton @click="editQuestionari(questionari.idQuestionaris)" class="ms-4" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                    Actualitzar
                </PrimaryButton>
            </div>
        </form>
    </GuestLayout>
</template>

<style scoped>

</style>
