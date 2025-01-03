<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="csrf-token" content="{{ csrf_token() }}">

        <title>Projecte Laravel</title>

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.bunny.net">
        <link href="https://fonts.bunny.net/css?family=figtree:400,500,600&display=swap" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" href="{{ url('/resources/css/menu.css') }}" />

        <!-- Scripts -->
        @vite(['resources/css/app.css', 'resources/js/app.js'])
    </head>
    <body class="font-sans antialiased">
        <div class="min-h-screen bg-gray-100">
            <!-- Page Heading -->
            @if (isset($header))
                <header class="bg-white shadow">
                    <div class="max-w-7xl mx-auto py-6 px-4 sm:px-6 lg:px-8">
                        {{ $header }}
                    </div>
                </header>
            @endif

            <nav class="flex bg-green-900 text-white justify-around">
                <a href="{{ route('inici') }}" class="lletraMenu primer">
                    Home
                </a>
                <a href="{{ route('products.list') }}" class="lletraMenu">
                    Productes
                </a>
                <a href="{{ route('clients.list') }}" class="lletraMenu">
                    Clients
                </a>
                <a href="{{ route('invoice.list') }}" class="lletraMenu">
                    Comandes
                </a>
            </nav>

            <!-- Page Content -->
            <main style="display: flex; flex-direction: column; justify-content: center; align-content: center; margin: 10px;">
                {{ $slot }}
            </main>
        </div>
    </body>
</html>
