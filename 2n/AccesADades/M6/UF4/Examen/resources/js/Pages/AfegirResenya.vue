<script setup>
import 'bootstrap/dist/css/bootstrap.css';
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import {useForm} from "@inertiajs/vue3";
import InputLabel from "@/Components/InputLabel.vue";
import TextInput from "@/Components/TextInput.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";
import GuestLayout from "@/Layouts/GuestLayout.vue";

defineProps({
    videojoc:{
        type: String
    }
});

const form = useForm({
    resenya:'',
    valoracio:0,
    idVideojoc:0,
    PDF:''
})
const afegir = () => {
    form.post(route('afegirResenyaPOST'));
};

const checkDigit = (KeyboardEvent) => {
    if (event.key.length === 1 && isNaN(Number(event.key))) {
        event.preventDefault();
    }
};

const afegirImatge=(event)=>{
    form.PDF = event.target.files[0]
};

</script>

<template>
    <AuthenticatedLayout>
        <GuestLayout>
        <Head title="Afegir Pregunta" />
            <form>
                <div>
                    <InputLabel for="resenya" value="Descripció:" />
                    <TextInput
                        id="resenya"
                        type="text"
                        class="mt-1 block w-full"
                        v-model="form.resenya"
                        required
                        autofocus
                        autocomplete="resenya"
                    />
                    <InputLabel for="val" value="Valoració: " />
                    <input
                        id="val"
                        type="number"
                        class="mt-1 block w-full"
                        v-model="form.valoracio"
                        required
                        autofocus
                        @keydown="checkDigit"
                        autocomplete="Valoracio"
                    />
                </div>
                <Input-label for="imagen" value="PDF:"/>
                <input id="imagen" type="file" accept="image/*" @change="afegirImatge">
                <div class="flex items-center justify-end mt-4">
                    <PrimaryButton @click="afegir" class="ms-4" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                        Afegir
                    </PrimaryButton>
                </div>
            </form>
        </GuestLayout>
    </AuthenticatedLayout>

</template>

<style scoped>

</style>
