<link rel="stylesheet" type="text/css" href="{{ url('/resources/css/menu.css') }}" />
<nav class="flex flex-1 justify-begin mr-1 background">
    <a href="{{ route('inici') }}" class="lletraMenu primer">
        Home
    </a>
    <a href="{{ route('clients.list') }}" class="lletraMenu">
        Clients
    </a>
</nav>
<div>
    @foreach ($products as $product)
        <div>
            <div>{{ $product->name }}, {{ $product->price }}, {{ $product->description }}, {{ $product->quantity }}</div>
            <button onclick="location.href='{{ url('/product/'.$product->id.'/warn') }}'">Delete</button>
            <!-- Aquest botó ens porta directament al link d'eliminació del producte -->
        </div>
    @endforeach
    <br>
    <div>
        <label>Click here to insert a new product</label>
        <br>
        <button onclick="location.href='{{ url('/product/insert') }}'">Insert</button>
    </div>
</div>
