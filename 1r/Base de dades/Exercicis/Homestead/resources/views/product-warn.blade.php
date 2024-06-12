<x-app-layout>
    <div>
        <p>De debò vols eliminar aquest producte? -> {{ $product->name }}</p>
        <button onclick="location.href='{{ url('/product/'.$product->id.'/delete') }}'" style="background-color: green; color: white; border-radius: 5px; border: 1px solid black; font-weight: bold;"><span class="m-2">Sí</span></button>
        <button onclick="location.href='{{ url('/products') }}'" style="border: 1px solid black; border-radius: 5px; background-color: red; color: white; font-weight: bold"><span class="m-2">No</span></button>
    </div>
</x-app-layout>

