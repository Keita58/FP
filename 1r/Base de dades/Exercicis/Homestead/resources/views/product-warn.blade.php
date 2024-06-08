<x-app-layout>
    <div>
        <p>De debò vols eliminar aquest producte? -> {{ $product->name }}</p>
        <button onclick="location.href='{{ url('/product/'.$product->id.'/delete') }}'" style="background-color: green; color: white; border-radius: 5px; border: 1px solid black; font-weight: bold">Sí</button>
        <button onclick="location.href='{{ url('/products') }}'" style="border: 1px solid black; border-radius: 5px; background-color: red; color: white; font-weight: bold">No</button>
    </div>
</x-app-layout>

