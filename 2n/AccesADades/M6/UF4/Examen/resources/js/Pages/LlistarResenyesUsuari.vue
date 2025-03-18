<script setup>
import 'bootstrap/dist/css/bootstrap.css';
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";
import {useForm} from "@inertiajs/vue3";

defineProps({
    jocs:{
        type: Array(String)
    },
});

const form = useForm({
    idVideojoc:0,
})

const llistar = (idjoc) => {
    form.idVideojoc = idjoc;
    form.post(route("veureResenya"));
};

const editarJoc = (idjoc) => {
    form.idVideojoc = idjoc;
    form.post(route("afegirResenya"));
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
                <tr v-for="videojoc in jocs" :key="idVideojocs">
                    <td>{{videojoc.idVideojocs}}</td>
                    <td>{{videojoc.titol}}</td>
                    <td>{{videojoc.idPlataforma}}</td>
                    <td>
                        <div class="flex items-center">
                            <PrimaryButton @click="llistar(videojoc.idVideojocs)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                                Llistar Resenyes
                            </PrimaryButton>
                            <PrimaryButton @click="editarJoc(videojoc.idVideojocs)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                                Afegir Resenya
                            </PrimaryButton>
                            <PrimaryButton @click="tancarJoc(videojoc.idVideojocs)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                                Tancar edició resenya
                            </PrimaryButton>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </AuthenticatedLayout>
</template>

<style scoped>

</style>
