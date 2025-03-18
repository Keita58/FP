<script setup>
import 'bootstrap/dist/css/bootstrap.css';
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";
import {useForm} from "@inertiajs/vue3";

defineProps({
    resenyes:{
        type: Array(String)
    },
});

const form = useForm({
    idResenyes:0,
})

const tornar = () => {
    form.get(route("Inici"));
};


</script>

<template>
    <AuthenticatedLayout>
        <template #header>
            <h2
                class="text-xl font-semibold leading-tight text-gray-800"
            >
                Llista resenyes
            </h2>
        </template>
        <div class="container mt-3">
            <table class="table table-striped-columns table-bordered">
                <tr class="row-cols-8">
                    <th>Id Resenya</th>
                    <th>Id Videojoc</th>
                    <th>Id Usuari</th>
                    <th>Resenya</th>
                    <th>Valoració</th>
                </tr>
                <tr v-for="(idResenya) in resenyes" :key="idResenyes">
                    <td>{{idResenya.idResenyes}}</td>
                    <td>{{idResenya.idVideojoc}}</td>
                    <td>{{idResenya.idJugador}}</td>
                    <td>{{idResenya.resenya}}</td>
                    <td>{{idResenya.valoracio}}</td>
                </tr>
            </table>
            <PrimaryButton @click="tornar()" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                Tornar
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
