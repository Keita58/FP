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
            @foreach($products as $product)
                <label>{{$product->name}}, {{$product->price}}</label>
                <input type="number" name="{{$product->id}}[quantity]" placeholder="Quantitat"/>
                <br>
            @endforeach
            <button type="submit" class="btn btn-primary">Compra</button>
        </form>
    </div>
</x-app-layout>

