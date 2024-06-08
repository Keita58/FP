<x-app-layout>
    <div>
        <p>Estàs segur d'liminar aquest client? -> {{ $client->name }}</p>
        <button onclick="location.href='{{ url('/client/'.$client->id.'/delete') }}'" style="background-color: green; color: white; border-radius: 5px; border: 1px solid black; font-weight: bold">Sí</button>
        <button onclick="location.href='{{ url('/clients') }}'" style="border: 1px solid black; border-radius: 5px; background-color: red; color: white; font-weight: bold">No</button>
    </div>
</x-app-layout>

