<!DOCTYPE html>
<html lang="cat">
    <head>
        <meta charset="UTF-8">
        <title>Projecte Laravel</title>
        <meta name="csrf-token" content="{{ csrf_token() }}">

        <!-- Fonts -->
        <link href="https://fonts.bunny.net/css?family=figtree:400,500,600&display=swap" rel="stylesheet" />
        <!-- Scripts -->
        @vite(['resources/css/app.css', 'resources/js/app.js'])
    </head>
    <body class="bg-gradient-to-br from-cyan-500 to-green-600 h-dvh text-white">
        <h1 class="flex justify-center text-3xl font-bold pt-5 shadow-xl underline decoration-black">Benvingut/da a la tenda de l'horitzó, la tenda més llunyana de les terres del Nord.</h1>
        <div class="flex flex-col justify-center text-center font-bold p-20">
            <div>
                <h2 class="text-xl pt-20">Per a nous clients, registreu-vos aquí -> <a href="client/insert" class="hover:text-yellow-400 hover:bg-black rounded"><span class="hover:m-2">Registre</span></a></h2>
                <h2 class="pb-20">(Si ja ets client/a del nostre establiment i vols veure el teu saldo, vés aquí -> <a href="clients" class="hover:text-blue-400 hover:bg-black rounded"><span class="hover:m-2">Usuaris</span></a>)</h2>
            </div>
            <h2 class="text-xl p-20 pt-0">Per veure els productes que tenim actualment, vés aquí -> <a href="products" class="hover:text-violet-400 hover:bg-black rounded"><span class="hover:m-2">Tenda</span></a></h2>
            <h2 class="text-xl p-20 pt-0">Per veure les comandes realitzades, vés aquí -> <a href="invoice/list" class="hover:text-red-400 hover:bg-black rounded"><span class="hover:m-2">Comandes</span></a></h2>
        </div>
    </body>
</html>
