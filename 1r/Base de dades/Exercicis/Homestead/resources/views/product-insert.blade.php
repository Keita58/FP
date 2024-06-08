<x-app-layout>
    <div>
        <form class="form-horizontal" method="POST" action="{{ route('product.create') }}">
            @csrf
            <label for="name">Nom:</label><br>
            <input type="text" id="name" name="name"><br>
            <label for="description">Descripció:</label><br>
            <input type="text" id="description" name="description"><br>
            <label for="price">Preu:</label><br>
            <input type="number" id="price" name="price"><br>
            <label for="quantity">Quantitat:</label><br>
            <input type="number" id="quantity" name="quantity"><br>
            <label for="iva">IVA del producte:</label><br>
            <input type="number" id="iva" name="iva"><br>
            <button type="submit" class="btn btn-primary" style="background-color: green; color: white; border-radius: 5px; border: 1px solid black; font-weight: bold; margin-top: 5px">Crea</button>
        </form>
        <div style="margin-top: 10px">
            <button onclick="location.href='{{ url('/products') }}'" style="border: 1px solid black; border-radius: 5px; background-color: red; color: white; font-weight: bold">Enrere</button>
        </div>
    </div>
</x-app-layout>

