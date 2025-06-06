<script setup>
import {Head, useForm} from "@inertiajs/vue3";
import GuestLayout from "@/Layouts/GuestLayout.vue";
import InputLabel from "@/Components/InputLabel.vue";
import TextInput from "@/Components/TextInput.vue";
import PrimaryButton from "@/Components/PrimaryButton.vue";

defineProps({
    respostes: {
        type: Array(String)
    },
    preguntes:{
        type: Array(String)
    }
})

const form = useForm({
    valorNumeric: Array(),
    idRespostes: Array()
})

const checkDigit = (KeyboardEvent) => {
    if (event.key.length === 1 && isNaN(Number(event.key))) {
        event.preventDefault();
    }
}

const afegirResposta = (ids) => {
    form.idRespostes = ids;
    form.post(route("sendQuestionari"))
}
</script>

<template>
    <GuestLayout>
        <Head title="Respondre qüestionari" />
        <form>
            <div v-for="(pregunta, index) in preguntes"  :key="pregunta.idPreguntes">
                <p class="font-bold">{{pregunta.descripcio}}</p>
                <TextInput
                    id="resposta"
                    type="number"
                    class="mt-1 block w-full"
                    v-model="form.valorNumeric[index]"
                    required
                    @keydown="checkDigit"
                    autofocus
                    autocomplete="off"
                />
                <br>
            </div>
        </form>
        <div class="flex mt-4">
            <PrimaryButton @click="afegirResposta(respostes)" :class="{ 'opacity-25': form.processing }" :disabled="form.processing">
                Respondre
            </PrimaryButton>
        </div>
    </GuestLayout>
</template>

<style scoped>

</style>
