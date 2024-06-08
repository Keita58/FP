<x-app-layout>
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
            <label for="money">Monedes:</label><br>
            <input type="number" id="money" name="money"><br>
            <button type="submit" style="background-color: green; color: white; border-radius: 5px; border: 1px solid black; font-weight: bold; margin-top: 5px">Crea</button>
        </form>
        <div style="margin-top: 10px">
            <button onclick="location.href='{{ url('/clients') }}'" style="border: 1px solid black; border-radius: 5px; background-color: red; color: white; font-weight: bold">Enrere</button>
        </div>
    </div>
</x-app-layout>

