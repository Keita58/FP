<x-app-layout>
    <div>
        <table>
            <tr>
                <th>Nom</th>
                <th>Cost</th>
                <th>Descripció</th>
                <th>Quantitat</th>
                <th>Iva</th>
                <th></th>
            </tr>
            @foreach ($products as $product)
                <tr>
                    <td>{{ $product->name }}</td>
                    <td>{{ $product->price }}</td>
                    <td>{{ $product->description }}</td>
                    <td>{{ $product->quantity }}</td>
                    <td>{{ $product->iva }}</td>
                    <td><button onclick="location.href='{{ url('/product/'.$product->id.'/warn') }}'" style="border: 1px solid black; border-radius: 5px; background-color: red; color: white; font-weight: bold">Delete</button></td>
                    <!-- Aquest botó ens porta directament al link d'eliminació del producte -->
                </tr>
            @endforeach
        </table>
    </div>
    <div style="margin-top: 10px">
        <label>Clica aquí per crear un nou producte</label>
        <br>
        <button onclick="location.href='{{ url('/product/insert') }}'" style="background-color: green; color: white; border-radius: 5px; border: 1px solid black; font-weight: bold">Crea</button>
    </div>
</x-app-layout>


