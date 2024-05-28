<style>
    table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #dddddd;
    }

    .background {
        background: darkgreen;
    }

    .lletraMenu {
        padding-right: 20px;
        color: white;
        text-decoration: none;
    }

    .primer {
        padding-left: 5px;
    }
</style>
<nav class="flex flex-1 justify-begin background">
    <a href="{{ route('inici') }}" class="lletraMenu primer">
        Home
    </a>
    <a href="{{ route('products.list') }}" class="lletraMenu">
        Products
    </a>
</nav>
<div>
    <table>
        <tr>
            <th>Name</th>
            <th>Age</th>
            <th>Address</th>
            <th>Delete</th>
        </tr>
        @foreach ($clients as $client)
            <tr style="">
                <td>{{ $client->name }}</td>
                <td>{{ $client->age }}</td>
                <td>{{ $client->address }}, {{ $client->city }}, {{ $client->country }}</td>
                <td><button onclick="location.href='{{ url('/client/'.$client->id.'/warn') }}'">Delete</button></td>
            </tr>
        @endforeach
    </table>
    <br>
    <div>
        <label>Click here to insert a new client</label>
        <br>
        <button onclick="location.href='{{ url('/client/insert') }}'">Insert</button>
    </div>
</div>
