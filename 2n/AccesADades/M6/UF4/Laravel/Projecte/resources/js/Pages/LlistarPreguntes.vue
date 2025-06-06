<script setup>
import 'bootstrap/dist/css/bootstrap.css';
import PrimaryButton from "@/Components/PrimaryButton.vue";
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import {router, useForm} from "@inertiajs/vue3";

defineProps({
    preguntes:{
        type: Array(String)
    }
});

const form = useForm({
    idPregunta:0,
})

const afegir = ()=>{
    form.get(route('AfegirPregunta'));
}

const eliminar = (idPregunta) => {
    form.idPregunta = idPregunta;
    form.post(route("deletePregunta"));
}

const editar = (idPregunta) => {
    form.idPregunta = idPregunta;
    form.get(route("editPregunta"));
}
</script>

<template>
    <AuthenticatedLayout>
        <template #header>
            <h2
                class="text-xl font-semibold leading-tight text-gray-800"
            >
                Llista preguntes
            </h2>
        </template>
        <div class="container mt-3">
            <table class="table table-striped-columns table-bordered">
                <tr class="row-cols-5">
                    <th>ID Pregunta</th>
                    <th>Descripció</th>
                    <th>ID Qüestionari</th>
                    <th>Acció</th>
                </tr>
                <tr v-for="(pregunta) in preguntes" :key="idPreguntes">
                    <td>{{pregunta.idPreguntes}}</td>
                    <td>{{pregunta.descripcio}}</td>
                    <td>{{pregunta.idQuestionaris}}</td>
                    <td>
                        <div class="flex items-center">
                            <PrimaryButton @click="editar(pregunta.idPreguntes)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                                Editar Pregunta
                            </PrimaryButton>
                            <PrimaryButton @click="eliminar(pregunta.idPreguntes)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                                Esborrar Pregunta
                            </PrimaryButton>
                        </div>
                    </td>
                </tr>
            </table>
            <PrimaryButton @click="afegir" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                Afegir Pregunta
            </PrimaryButton>
        </div>
    </AuthenticatedLayout>
</template>

<style scoped>
table,td,th{
    border: 1px solid yellow;
    border-collapse: collapse;
    background-color: hotpink;
}
th{
    background-color: lime;
    font-weight: bold;
}
</style>
