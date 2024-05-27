<nav class="flex flex-1 justify-begin">
    <a href="{{ route('products.list') }}" class="rounded-md px-3 py-2 text-black ring-1 ring-transparent transition hover:text-black/70 focus:outline-none focus-visible:ring-[#FF2D20] dark:text-white dark:hover:text-white/80 dark:focus-visible:ring-white">
        Products
    </a>
    <a href="{{ route('clients.list') }}" class="rounded-md px-3 py-2 text-black ring-1 ring-transparent transition hover:text-black/70 focus:outline-none focus-visible:ring-[#FF2D20] dark:text-white dark:hover:text-white/80 dark:focus-visible:ring-white">
        Clients
    </a>
</nav>
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
    <div>
        <label>Go back</label>
        <br>
        <button onclick="location.href='{{ url('/products') }}'">Back</button>
    </div>
</div>
