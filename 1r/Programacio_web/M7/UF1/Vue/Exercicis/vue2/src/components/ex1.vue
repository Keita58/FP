<script setup lang="ts">
import axios from "axios";
import { ref } from "vue";

const users = ref("");

axios.get('https://jsonplaceholder.typicode.com/users')
.then(response => {
    users.value = response.data;
    console.log(users);
})
.catch(error => {
    console.log(error);
});

</script>

<template>
    <h1>Llista usuaris</h1>
    <div>
    <table class="caption-top">
        <caption>Llista d'usuaris</caption>
        <thead>
            <tr>
                <th>userId</th>
                <th>Name</th>
                <th>UserName</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="user in users" :key="user.id">
                <td>{{ user.id }}</td>
                <td>{{ user.name }}</td>
                <td>{{ user.username }}</td>
            </tr>
        </tbody>
    </table>
    </div>
</template>

<style scoped>
* {
    box-sizing: border-box;
}

table {
    border-collapse: collapse;
    float: left;
    width: 50%;
}

table, td, th {
    border: 1px solid black;
}

th {
    background-color: blueviolet;
    color: white;
}
</style>