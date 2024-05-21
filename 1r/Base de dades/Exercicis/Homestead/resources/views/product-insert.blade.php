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
        <button type="submit" class="btn btn-primary">Create</button>
    </form>
</div>
