<link rel="stylesheet" type="text/css" href="{{ url('/resources/css/menu.css') }}" />
<nav class="flex flex-1 justify-begin background">
    <a href="{{ route('inici') }}" class="lletraMenu primer">
        Home
    </a>
    <a href="{{ route('products.list') }}" class="lletraMenu">
        Products
    </a>
    <a href="{{ route('clients.list') }}" class="lletraMenu">
        Clients
    </a>
</nav>
<div>
    <form class="form-horizontal" method="POST" action="{{ route('client.create') }}">
        @csrf
        <label for="name">Nom:</label><br>
        <input type="text" id="name" name="name"><br>
        <label for="age">Edat:</label><br>
        <input type="text" id="age" name="age"><br>
        <label for="address">Adreça:</label><br>
        <input type="text" id="address" name="address"><br>
        <label for="city">Ciutat:</label><br>
        <input type="text" id="city" name="city"><br>
        <label for="country">País:</label><br>
        <input type="text" id="country" name="country"><br>
        <button type="submit" class="btn btn-primary">Create</button>
    </form>
    <div>
        <label>Go back</label>
        <br>
        <button onclick="location.href='{{ url('/clients') }}'">Back</button>
    </div>
</div>
