<x-app-layout>
    <div>
        <form class="form-horizontal" method="POST" action="{{ route('invoice.create') }}">
            @csrf
            <label for="users">Tria el teu usuari:</label>
            <select id="users" name="users">
                @foreach($clients as $client)
                    <option value={{$client->id}}>{{ $client->name }}, Diners: {{$client->money}}</option>
                @endforeach
            </select>
            <br>
            <label>Tria quins productes vols comprar:</label>
            <br>
            <table style="width: 75%">
                <tr>
                    <th>Nom</th>
                    <th>Monedes</th>
                    <th>Quantitat</th>
                    <th></th>
                </tr>
                @foreach($products as $product)
                    <tr>
                        <td>{{$product->name}}</td>
                        <td style="text-align: center">{{$product->price}}</td>
                        <td style="text-align: center">{{$product->quantity}}</td>
                        <td><input type="number" name="quantity[{{$product->id}}][quantity]" placeholder="Quantitat" value="0"/></td>
                        <input type="hidden" name="iva[{{$product->id}}]" value="{{$product->iva}}">
                        <input type="hidden" name="price[{{$product->id}}]" value="{{$product->price}}">
                        <input type="hidden" name="name[{{$product->id}}]" value="{{$product->name}}">
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

