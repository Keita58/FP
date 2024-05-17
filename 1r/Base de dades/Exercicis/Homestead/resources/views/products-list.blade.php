<div>
    @foreach ($products as $product)
        <div>
            <div>{{ $product->name }}, {{ $product->price }}, {{ $product->description }}, {{ $product->quantity }}</div>
            <button onclick="location.href='{{ url('/product/'.$product->id.'/delete') }}'">Delete</button>
            <!-- Aquest botó ens porta directament al link d'eliminació del producte -->
        </div>
    @endforeach
</div>
