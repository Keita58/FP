<x-app-layout>
    <table>
        <tr>
            <th>User</th>
            <th>Product</th>
            <th>Quantity</th>
            <th>IVA</th>
            <th>Price before iva</th>
            <th>Price after iva</th>
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
</x-app-layout>

