
<nav class="flex flex-1 justify-begin background">
    <a href="{{ route('inici') }}" class="lletraMenu primer">
        Home
    </a>
    <a href="{{ route('products.list') }}" class="lletraMenu">
        Products
    </a>
    <a href="{{ route('clients.list') }}" class="lletraMenu">
        Clients
    </a>
</nav>
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
