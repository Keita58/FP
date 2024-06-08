<x-app-layout>
    <div>
        <table>
            <tr>
                <th>Client</th>
                <th>Producte</th>
                <th>Quantitat</th>
                <th>IVA</th>
                <th>Preu abans IVA</th>
                <th>Preu després IVA</th>
            </tr>
            @foreach($invoices as $invoice)
                <td>{{$invoice->pivot->name}}</td>
                <td>{{$invoice->pivot->product_name}}</td>
                <td>{{$invoice->pivot->quantity_product}}</td>
                <td>{{$invoice->pivot->iva}}</td>
                <td>{{$invoice->pivot->price_product}}</td>
                <td>{{$invoice->pivot->price_after_iva}}</td>
            @endforeach
        </table>
    </div>
    <div style="margin-top: 10px">
        <label>Crea una nova comanda</label>
        <br>
        <button onclick="location.href='{{ url('/invoice') }}'" style="background-color: green; color: white; border-radius: 5px; border: 1px solid black; font-weight: bold">Insert</button>
    </div>
</x-app-layout>

