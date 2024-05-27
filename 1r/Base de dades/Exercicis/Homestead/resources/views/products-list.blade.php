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
