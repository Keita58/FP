<script setup>
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import {useForm} from "@inertiajs/vue3";
import InputLabel from "@/Components/InputLabel.vue";
import TextInput from "@/Components/TextInput.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";

defineProps({
    questionaris:{
        type: Array(String)
    }
});

const form = useForm({
    descripcio:'',
    idQuestionaris:'',
    selectedOption:null
})
const afegir = () => {
    form.post(route('addPregunta'));
};

</script>

<template>
    <AuthenticatedLayout>
        <Head title="Afegir Pregunta" />
        <form>
            <div>
                <InputLabel for="descripcio" value="Descripció:" />
                <TextInput
                    id="descripcio"
                    type="text"
                    class="mt-1 block w-full"
                    v-model="form.descripcio"
                    required
                    autofocus
                    autocomplete="nom"
                />
                <select id="selectOption" v-model="form.selectedOption">
                    <option v-for="(idQuestionaris) in questionaris" :value="idQuestionaris.idQuestionaris" >
                        {{idQuestionaris.idQuestionaris}}
                    </option>
                </select>
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
