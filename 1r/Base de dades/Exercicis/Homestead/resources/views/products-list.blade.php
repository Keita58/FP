<x-app-layout>
    <nav class="flex bg-green-900 text-white justify-around">
        <a href="{{ route('inici') }}" class="lletraMenu primer">
            Home
        </a>
        <a href="{{ route('clients.list') }}" class="lletraMenu">
            Clients
        </a>
    </nav>
    <table style="padding: 15px">
        <tr>
            <th>Nom</th>
            <th>Cost</th>
            <th>Descripció</th>
            <th>Quantitat</th>
            <th></th>
        </tr>
        @foreach ($products as $product)
            <tr>
                <td>{{ $product->name }}</td>
                <td>{{ $product->price }}</td>
                <td>{{ $product->description }}</td>
                <td>{{ $product->quantity }}</td>
                <td><button onclick="location.href='{{ url('/product/'.$product->id.'/warn') }}'" style="border: 1px solid black; border-radius: 5px; background-color: red; color: white; font-weight: bold">Delete</button></td>
                <!-- Aquest botó ens porta directament al link d'eliminació del producte -->
            </tr>
        @endforeach
    </table>

    <br>
    <div>
        <label>Click here to insert a new product</label>
        <br>
        <button onclick="location.href='{{ url('/product/insert') }}'" style="background-color: green; color: white; border-radius: 5px">Insert</button>
    </div>
</x-app-layout>


