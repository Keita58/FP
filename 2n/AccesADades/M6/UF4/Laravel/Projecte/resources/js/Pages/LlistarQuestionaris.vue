<script setup>
import 'bootstrap/dist/css/bootstrap.css';
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";
import {useForm} from "@inertiajs/vue3";

defineProps({
    questionaris:{
        type: Array(String)
    }
});

const form = useForm({
    idQuestionaris:0,
})

const afegirQuestionari=()=>{
    form.get(route('AfegirQuestionari'));
}

const eliminarQuest = (id) => {
    form.idQuestionaris = id;
    form.post(route('deleteQuest'));
}

const editarQuest = (id) => {
    form.idQuestionaris = id;
    form.get(route('editQuest'));
}


</script>

<template>
    <AuthenticatedLayout>
        <template #header>
            <h2
                class="text-xl font-semibold leading-tight text-gray-800"
            >
                Llista qüestionaris
            </h2>
        </template>
        <div class="container mt-3">
            <table class="table table-striped-columns table-bordered">
                <tr class="row-cols-5">
                    <th>ID Questionari</th>
                    <th>ID Empresa</th>
                    <th>Descripció</th>
                    <th>Acció</th>
                </tr>
                <tr v-for="(idQuestionaris) in questionaris" :key="idQuestionaris">
                    <td>{{idQuestionaris.idQuestionaris}}</td>
                    <td>{{idQuestionaris.idEmpresa}}</td>
                    <td>{{idQuestionaris.descripcio}}</td>
                    <td>
                        <div class="flex items-center">
                            <PrimaryButton @click="editarQuest(idQuestionaris.idQuestionaris)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                                Editar Qüestionari
                            </PrimaryButton>
                            <PrimaryButton @click="eliminarQuest(idQuestionaris.idQuestionaris)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                                Esborrar Qüestionari
                            </PrimaryButton>
                        </div>
                    </td>
                </tr>
            </table>
            <PrimaryButton @click="afegirQuestionari" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                Afegir Qüestionari
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
