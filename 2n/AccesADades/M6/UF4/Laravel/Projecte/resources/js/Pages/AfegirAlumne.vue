<script setup>
import {useForm} from "@inertiajs/vue3";
import TextInput from "@/Components/TextInput.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import InputLabel from "@/Components/InputLabel.vue";
import Checkbox from "@/Components/Checkbox.vue";
import GuestLayout from "@/Layouts/GuestLayout.vue";

const form = useForm({
    nomComplet:'',
    img:null,
    questionari:false
})


const afegir = () => {
    form.post(route('addAlumne'));
};

const afegirImatge = (event) => {
    form.img = event.target.files[0];
}
</script>

<template>
    <GuestLayout>
        <Head title="Afegir Rols" />
        <form>
            <div>
                <InputLabel for="nom" value="Nom:" />
                <TextInput
                    id="nom"
                    type="text"
                    class="mt-1 block w-full"
                    v-model="form.nomComplet"
                    required
                    autofocus
                    autocomplete="nom"
                />
                <Input-label for="imagen" value="Foto:"/>
                <input id="imagen" type="file" accept="image/*" @change="afegirImatge">
                <p>El qüestionari està tancat?
                    <checkbox v-model="form.questionari" checked="false"/>
                </p>
            </div>
            <div class="flex items-center justify-end mt-4">
                <PrimaryButton @click="afegir" class="ms-4" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                    Afegir
                </PrimaryButton>
            </div>
        </form>
    </GuestLayout>
</template>

<style scoped>

</style>
