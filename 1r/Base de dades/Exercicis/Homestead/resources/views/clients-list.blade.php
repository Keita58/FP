<x-app-layout>
    <div>
        <table style="width: 75%">
            <tr style="text-align: left">
                <th>Nom</th>
                <th>Edat</th>
                <th>Adreça</th>
                <th>Monedes</th>
                <th></th>
            </tr>
            @foreach ($clients as $client)
                <tr>
                    <td>{{ $client->name }}</td>
                    <td>{{ $client->age }}</td>
                    <td style="width: 45%">{{ $client->address }}, {{ $client->city }}, {{ $client->country }}</td>
                    <td>{{ $client->money }}</td>
                    <td><button onclick="location.href='{{ url('/client/'.$client->id.'/warn') }}'" style="border: 1px solid black; border-radius: 5px; background-color: red; color: white; font-weight: bold">Elimina</button></td>
                </tr>
            @endforeach
        </table>
    </div>
    <div style="margin-top: 10px">
        <label>Clica aquí per a crear un nou client</label>
        <br>
        <button onclick="location.href='{{ url('/client/insert') }}'" style="background-color: green; color: white; border-radius: 5px; border: 1px solid black; font-weight: bold">Crea</button>
    </div>
</x-app-layout>

