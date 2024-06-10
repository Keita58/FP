<x-app-layout>
    <div>
        <h1>Selecciona l'usuari de qui vols veure les comandes:</h1>
        <form class="form-horizontal" method="GET" action="{{ route('invoice.list') }}">
            <select id="users" name="users">
                @foreach($clients as $client)
                    <option value={{$client->id}}>{{ $client->name }}</option>
                @endforeach
            </select>
            <button type="submit" style="background-color: blue; color: white; border-radius: 5px; border: 1px solid black; font-weight: bold">Selecciona</button>
        </form>
        <br>
        <h1>Comandes:</h1>
        <table style="width: 100%">
            <tr style="text-align: left">
                <th>Client</th>
                <th>Producte</th>
                <th>Quantitat</th>
                <th>IVA</th>
                <th>Preu abans IVA</th>
                <th>Preu després IVA</th>
                <th>Data de compra</th>
            </tr>
            @foreach($invoices as $invoice)
                @foreach($invoice->products as $product)
                    <tr>
                        <td>{{$invoice->client->name}}</td>
                        <td>{{$product->name}}</td>
                        <td>{{$product->pivot->quantity_product}}</td>
                        <td>{{$product->iva}}</td>
                        <td>{{$product->pivot->price_before_iva}}</td>
                        <td>{{$product->pivot->price_after_iva}}</td>
                        <td>{{$invoice->created_at}}</td>
                    </tr>
                @endforeach
            @endforeach
        </table>
    </div>
    <div style="margin-top: 10px">
        <label>Crea una nova comanda</label>
        <br>
        <button onclick="location.href='{{ url('/invoice') }}'" style="background-color: green; color: white; border-radius: 5px; border: 1px solid black; font-weight: bold">Insert</button>
    </div>
</x-app-layout>

