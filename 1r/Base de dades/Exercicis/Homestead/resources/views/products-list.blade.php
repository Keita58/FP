<div>
    @foreach ($products as $product)
        <div>
            <div>{{ $product->name }}, {{ $product->price }}, {{ $product->description }}, {{ $product->quantity }}</div>
        </div>
    @endforeach
</div>
