
<nav class="flex flex-1 justify-begin background">
    <a href="{{ route('inici') }}" class="lletraMenu primer">
        Home
    </a>
    <a href="{{ route('products.list') }}" class="lletraMenu">
        Products
    </a>
</nav>
<div>
    <form class="form-horizontal" method="POST" action="{{ route('invoice.create') }}">
        <label for="users">Tria el teu usuari:</label>
        <select name="users">
            @foreach($clients as $client)
                <option value={{$client->id}}>{{ $client->name }}</option>
            @endforeach
        </select>
        <br>
        <label>Tria quins productes vols comprar:</label>
        <br>
        @foreach($products as $product)
            <label for={{$product->name}}>{{$product->name}}:</label>
            <input type="number" name={{$product->name}}>
            <br>
        @endforeach
    </form>
</div>
