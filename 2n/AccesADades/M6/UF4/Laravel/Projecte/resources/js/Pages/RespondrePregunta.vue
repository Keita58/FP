<script setup>
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import {useForm} from "@inertiajs/vue3";
import GuestLayout from "@/Layouts/GuestLayout.vue";
import InputLabel from "@/Components/InputLabel.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";

defineProps({
    alumnes: {
        type: Array(String)
    },
    questionaris:{
        type: Array(String)
    },
    professors:{
        type: Array(String)
    }
});

const form = useForm({
    idAlumne:0,
    idQuestionari:0,
    idProfessor:0,
});

const afegir = () => {
    form.post(route('seleccionarAlumneRespondre'));
}

</script>

<template>
    <AuthenticatedLayout>
        <GuestLayout>
            <form>
                <InputLabel for="questionari" value="Questionari a mostrar: " />
                <select id="questionari" v-model="form.idQuestionari">
                    <option v-for="(idQuestionaris) in questionaris" :value="idQuestionaris.idQuestionaris" >
                        {{idQuestionaris.idQuestionaris}}
                    </option>
                </select>
                <InputLabel for="alumne" value="Alumne a valorar: " />
                <select id="alumne" v-model="form.idAlumne">
                    <option v-for="(idAlumnes) in alumnes" :value="idAlumnes.idAlumnes" >
                        {{idAlumnes.nomComplet}}
                    </option>
                </select>
                <InputLabel for="professor" value="Professor qui respondrà: " />
                <select id="professor" v-model="form.idProfessor">
                    <option v-for="(idProfessors) in professors" :value="idProfessors.id" >
                        {{idProfessors.name}}
                    </option>
                </select>
            </form>
            <div class="flex mt-4">
                <PrimaryButton @click="afegir" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                    Contestar
                </PrimaryButton>
            </div>
        </GuestLayout>
    </AuthenticatedLayout>
</template>

<style scoped>

</style>
