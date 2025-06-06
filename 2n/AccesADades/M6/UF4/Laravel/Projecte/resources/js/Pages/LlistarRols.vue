<script setup>
import 'bootstrap/dist/css/bootstrap.css';
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";
import {useForm} from "@inertiajs/vue3";
import InputLabel from "@/Components/InputLabel.vue";

defineProps({
    rols:{
        type:Array(String)
    },
    rolsFull: {
        type: Array(String)
    }
});

const form = useForm({
    idRols:0,
    nom:'',
    selectedOption:0
})

const afegirRol=()=>{
    form.get(route('afegirRol'));
}

const eliminarRol = (rol) => {
    form.idRols = rol;
    form.post(route('deleteRol'));
}

const editarRol = (nom, idRol) => {
    form.nom = nom;
    form.idRols = idRol;
    form.get(route('editRol'));
}
const filtrarRol = () => {
    form.post(route('FiltrarRols'))
}

</script>

<template>
    <AuthenticatedLayout>
        <template #header>
            <h2
                class="text-xl font-semibold leading-tight text-gray-800"
            >
                Llista rols
            </h2>
            <InputLabel for="rols" value="Rols a filtrar: " />
            <select id="rols" v-model="form.selectedOption">
                <option v-for="(idRols) in rolsFull" :value="idRols.idRols">
                    {{idRols.idRols}}
                </option>
            </select>
            <PrimaryButton @click="filtrarRol" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                Filtrar Rol
            </PrimaryButton>
        </template>
        <div class="container mt-3">
            <table class="table table-striped-columns table-bordered">
                <tr class="row-cols-5">
                    <th>ID Rol</th>
                    <th>Nom Rol</th>
                    <th>Acció</th>
                </tr>
                <tr v-for="(idRols) in rols" :key="idRols">
                    <td>{{idRols.idRols}}</td>
                    <td>{{idRols.nom}}</td>
                    <td>
                        <div class="flex items-center">
                            <PrimaryButton @click="editarRol(idRols.nom, idRols.idRols)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                                Editar Rol
                            </PrimaryButton>
                            <PrimaryButton @click="eliminarRol(idRols.idRols)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                                Esborrar Rol
                            </PrimaryButton>
                        </div>
                    </td>
                </tr>
            </table>
            <PrimaryButton @click="afegirRol()" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                Afegir Rol
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
