<script setup>
import AuthenticatedLayout from "@/Layouts/AuthenticatedLayout.vue";
import GuestLayout from "@/Layouts/GuestLayout.vue";

defineProps({
    questionaris:{
        type:Array(String)
    },
    preguntes:{
        type:Array(String)
    },
    respostes:{
        type:Array(String)
    }
})
</script>

<template>
    <AuthenticatedLayout>
        <GuestLayout>
            <div v-for="(questionari, index) in questionaris" :key="questionari.idQuestionaris">
                <br>
                <p style="font-weight: bold">Id del Qüestionari: {{questionari.idQuestionaris}}</p>
                <br>
                <div v-for="(pregunta, index) in preguntes" :key="pregunta.idPreguntes">
                    <p v-if="questionari.idQuestionaris === pregunta.idQuestionaris" class="font-bold">{{pregunta.descripcio}}</p>
                    <div v-if="questionari.idQuestionaris === pregunta.idQuestionaris" v-for="(resposta, index) in respostes" :key="respostes.idRespostes">
                        <p v-if="resposta.idPregunta === pregunta.idPreguntes">Resposta: {{resposta.valoracio}}</p>
                    </div>
                </div>
            </div>
        </GuestLayout>
    </AuthenticatedLayout>
</template>

<style scoped>

</style>
