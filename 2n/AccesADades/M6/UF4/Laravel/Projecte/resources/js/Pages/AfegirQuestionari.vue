<script setup>

import InputLabel from "@/Components/InputLabel.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import TextInput from "@/Components/TextInput.vue";
import {useForm} from "@inertiajs/vue3";


const form = useForm({
    idEmpresa:0,
    descripcio:''
})
const afegir = () => {
    form.post(route('addQuestionari'));
};

const checkDigit = (KeyboardEvent) => {
    if (event.key.length === 1 && isNaN(Number(event.key))) {
        event.preventDefault();
    }
}


</script>

<template>
    <AuthenticatedLayout>
        <Head title="Afegir Qüestionari" />
        <form>
            <div>
                <InputLabel for="idEmpresa" value="Id de l'empresa:" />
                <TextInput
                    id="idEmpresa"
                    type="number"
                    class="mt-1 block w-full"
                    v-model="form.idEmpresa"
                    required
                    autofocus
                    autocomplete="nom"
                />
                <InputLabel for="descripcio" value="Descripció:" />
                <TextInput
                    id="descripcio"
                    type="number"
                    class="mt-1 block w-full"
                    v-model="form.descripcio"
                    required
                    @keydown="checkDigit"
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
