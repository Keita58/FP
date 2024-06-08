<x-app-layout>
    <div>
        <p>Are you sure to detele this product? -> {{ $product->name }}</p>
        <button onclick="location.href='{{ url('/product/'.$product->id.'/delete') }}'">Yes</button>
        <button onclick="location.href='{{ url('/products') }}'">No</button>
    </div>
</x-app-layout>

