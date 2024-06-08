<x-app-layout>
    <div>
        <form class="form-horizontal" method="POST" action="{{ route('invoice.create') }}">
            @csrf
            <label for="users">Tria el teu usuari:</label>
            <select id="users" name="users">
                @foreach($clients as $client)
                    <option value={{$client->id}}>{{ $client->name }}</option>
                @endforeach
            </select>
            <br>
            <label>Tria quins productes vols comprar:</label>
            <br>
            <table>
                <tr>
                    <th>Nom</th>
                    <th>Monedes</th>
                    <th>Quantitat</th>
                    <th></th>
                </tr>
                @foreach($products as $product)
                    <tr>
                        <td>{{$product->name}}</td>
                        <td>{{$product->price}}</td>
                        <td>{{$product->quantity}}</td>
                        <td><input type="number" name="{{$product->id}}[quantity]" placeholder="Quantitat"/></td>
                    </tr>
                @endforeach
            </table>
            <button type="submit" style="background-color: green; color: white; border-radius: 5px; border: 1px solid black; font-weight: bold">Compra</button>
        </form>
    </div>
    <div style="margin-top: 10px">
        <button onclick="location.href='{{ url('/invoice/list') }}'" style="border: 1px solid black; border-radius: 5px; background-color: red; color: white; font-weight: bold">Descarta</button>
    </div>
</x-app-layout>

