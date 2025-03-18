<script setup>
import 'bootstrap/dist/css/bootstrap.css';
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";
import {useForm} from "@inertiajs/vue3";

defineProps({
    jocs:{
        type:Array(String)
    },
});

const form = useForm({
    idVideojocs:0,
})

const afegirJoc = () => {

};

const editarJoc = () => {

};

const eliminarJoc = () => {

};
</script>

<template>
    <AuthenticatedLayout>
        <template #header>
            <h2
                class="text-xl font-semibold leading-tight text-gray-800"
            >
                Llista videojocs
            </h2>
        </template>
        <div class="container mt-3">
            <table class="table table-striped-columns table-bordered">
                <tr class="row-cols-5">
                    <th>Id Videojoc</th>
                    <th>Títol</th>
                    <th>Id Plataforma</th>
                    <th>Acció</th>
                </tr>
                <tr v-for="(videojoc) in jocs" :key="idVideojocs">
                    <td>{{videojoc.idVideojocs}}</td>
                    <td>{{videojoc.titol}}</td>
                    <td>{{videojoc.idPlataforma}}</td>
                    <td>
                        <div class="flex items-center">
                            <PrimaryButton @click="editarJoc(videojoc.idVideojocs)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                                Editar Videojoc
                            </PrimaryButton>
                            <PrimaryButton @click="eliminarJoc(videojoc.idVideojocs)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                                Esborrar Videojoc
                            </PrimaryButton>
                        </div>
                    </td>
                </tr>
            </table>
            <PrimaryButton @click="afegirJoc()" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                Afegir Videojoc
            </PrimaryButton>
        </div>
    </AuthenticatedLayout>
</template>

<style scoped>

</style>
