<script setup>
import {useForm} from "@inertiajs/vue3";
import PrimaryButton from "@/Components/PrimaryButton.vue";
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import 'bootstrap/dist/css/bootstrap.css';

defineProps({
    alumnes:{
        type: Array(String)
    }
});

const form = useForm({
    idAlumne:0,
    questionari:false
})

const afegir = ()=>{
    form.get(route('AfegirAlumne'));
}

const eliminar = (idAlumne) => {
    form.idAlumne = idAlumne;
    form.post(route("deleteAlumne"));
}

const editar = (idAlumne, questionari) => {
    form.idAlumne = idAlumne;
    form.questionari = questionari;
    form.get(route("editAlumne"));
}

</script>

<template>
    <AuthenticatedLayout>
        <template #header>
            <h2
                class="text-xl font-semibold leading-tight text-gray-800"
            >
                Llista alumnes
            </h2>
        </template>
        <div class="container mt-3">
            <table class="table table-striped-columns table-bordered">
                <tr class="row-cols-5">
                    <th>ID Alumne</th>
                    <th>Nom complet</th>
                    <th>Ha completat els qüestionaris?</th>
                    <th>Imatge</th>
                    <th>Acció</th>
                </tr>
                <tr v-for="(alumne) in alumnes" :key="idAlumnes">
                    <td>{{alumne.idAlumnes}}</td>
                    <td>{{alumne.nomComplet}}</td>
                    <td v-if="alumne.questionari">Sí</td>
                    <td v-else>No</td>
                    <td><img v-if="alumne.img !=null" :src="`/uploads/${alumne.img}`"/></td>
                    <td>
                        <div class="flex items-center">
                            <PrimaryButton @click="editar(alumne.idAlumnes, alumne.questionari)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                                Editar Alumne
                            </PrimaryButton>
                            <PrimaryButton @click="eliminar(alumne.idAlumnes)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                                Esborrar Alumne
                            </PrimaryButton>
                        </div>
                    </td>
                </tr>
            </table>
            <PrimaryButton @click="afegir" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                Afegir Alumne
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
