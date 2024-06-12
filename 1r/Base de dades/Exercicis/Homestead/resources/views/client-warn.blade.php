<x-app-layout>
    <div>
        <p>Estàs segur d'eliminar aquest client? -> {{ $client->name }}</p>
        <button onclick="location.href='{{ url('/client/'.$client->id.'/delete') }}'" style="background-color: green; color: white; border-radius: 5px; border: 1px solid black; font-weight: bold"><span class="m-2">Sí</span></button>
        <button onclick="location.href='{{ url('/clients') }}'" style="border: 1px solid black; border-radius: 5px; background-color: red; color: white; font-weight: bold"><span class="m-2">No</span></button>
    </div>
</x-app-layout>

