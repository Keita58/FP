<script setup>
import InputLabel from "@/Components/InputLabel.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";
import {Head, useForm} from "@inertiajs/vue3";
import GuestLayout from "@/Layouts/GuestLayout.vue";
import TextInput from "@/Components/TextInput.vue";

defineProps({
    pregunta: {
        type: String
    },
    questionaris:{
        type:Array(String)
    }
})

const form = useForm ({
    descripcio: '',
    selectedOption:null,
    idPreguntes:0
})

const editPregunta = (idPregunta)=>{
    form.idPreguntes=idPregunta;
    form.post(route("updatePregunta"))
}

</script>

<template>
    <GuestLayout>
        <Head title="Actualitzar Pregunta" />
        <form>
            <div>
                <p>Descripció: {{pregunta.descripcio}}</p>
                <p>ID del qüestionari: {{pregunta.idQuestionaris}}</p>
                <InputLabel for="descripcio" value="Descripció: " />
                <TextInput
                    id="descripcio"
                    type="text"
                    class="mt-1 block w-full"
                    v-model="form.descripcio"
                    required
                    autofocus
                    autocomplete="descripcio"
                />
                <label>
                    Qüestionari:
                </label>
                <select id="selectOption" v-model="form.selectedOption">
                    <option v-for="(idQuestionaris) in questionaris" :value="idQuestionaris.idQuestionaris" >
                        {{idQuestionaris.idQuestionaris}}
                    </option>
                </select>
            </div>
            <div class="flex items-center justify-end mt-4">
                <PrimaryButton @click="editPregunta(pregunta.idPreguntes)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                    Actualitzar
                </PrimaryButton>
            </div>
        </form>
    </GuestLayout>
</template>

<style scoped>

</style>
