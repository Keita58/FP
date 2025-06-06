<script setup>
import 'bootstrap/dist/css/bootstrap.css';
import GuestLayout from "@/Layouts/GuestLayout.vue";
import {Head, useForm} from "@inertiajs/vue3";
import PrimaryButton from "@/Components/PrimaryButton.vue";
import InputLabel from "@/Components/InputLabel.vue";
import TextInput from "@/Components/TextInput.vue";
import Checkbox from "@/Components/Checkbox.vue";
import {onMounted} from "vue";

const props= defineProps({
    alumnes:{
        type:String
    },
});

const form = useForm({
    idAlumne: 0,
    nomComplet:'',
    questionari:false,
    img:null,
});

const afegirImatge=(event)=>{
    form.img = event.target.files[0]
};

const editAlumne = (idAlumne) => {
    form.idAlumne = idAlumne;
    form.post(route('updateAlumne'));
};

onMounted(() => {
    if (props.alumnes) {
        form.questionari = props.alumnes.questionari;
    }
});

</script>

<template>
    <GuestLayout>
        <Head title="Actualitzar Rols" />
        <form>
            <div>
                <p>Nom: {{alumnes.nomComplet}}</p>
                <InputLabel for="nom" value="Nou nom:" />
                <TextInput
                    id="nomNou"
                    type="text"
                    class="mt-1 block w-full"
                    v-model="form.nomComplet"
                    required
                    autofocus
                    autocomplete="nom"
                />
            </div>
            <div>
                <p>Imatge: {{alumnes.img}}</p>
                <Input-label for="imagen" value="Foto:"/>
                <input id="imagen" type="file" accept="image/*" @change="afegirImatge">
            </div>
            <div>
                <p>Qüestionari: {{alumnes.questionari}}</p>
                <p>{{form.questionari}}</p>
                <InputLabel value="Ha realitzat tots els qüestionaris? "></InputLabel>
                <checkbox id="selectOption" v-model="form.questionari" v-if="form.questionari === '1'" :checked="true"></checkbox>
                <checkbox id="selectOption" v-model="form.questionari" v-else :checked="false"></checkbox>
            </div>
            <div class="flex items-center justify-end mt-4">
                <PrimaryButton @click="editAlumne(alumnes.idAlumnes)" class="ms-4" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                    Actualitzar
                </PrimaryButton>
            </div>
        </form>
    </GuestLayout>
</template>

<style scoped>

</style>
