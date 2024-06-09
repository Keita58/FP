<?php

namespace App\Http\Controllers;

use App\Models\Client;
use App\Models\Invoice;
use App\Models\Product;
use Illuminate\Http\Request;
use Illuminate\Support\Arr;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Redirect;

class InvoiceController extends Controller
{
    public function invoice(Request $request) {
        $products = Product::all();
        $clients = Client::all();
        return view('invoices')->with('clients', $clients)->with('products', $products);
    }

    public function create(Request $request) {
        $request->validate([
            'quantity.*.quantity' => 'required|integer|max:255|min:0'
        ]);
        Log::info('Request data: ', $request->all());
        $client_id = $request->input('users');
        $products = $request->input('quantity');
        Log::info('Productes: ', $products);
        $ivas = $request->input('iva');
        Log::info('IVAS: ', $ivas);
        $names = $request->input('name');
        Log::info('Names: ', $names);
        $prices = $request->input('price');
        Log::info('Prices: ', $prices);

        $invoice = Invoice::create([
            'client_id' => $client_id
        ]);
        $invoice->save();
        $num = 0;
        foreach ($products as $product) {
            $num++;
            $num_items = Product::find($num);
            //Log::info('Descripcio item: ', $num_items);
            Log::info(Arr::get($product, 'quantity'));
            if(Arr::get($product, 'quantity') > 0){
                $iva_producte = $ivas[$num];
                $invoice->products()->attach($num, [
                    'quantity_product' => Arr::get($product, 'quantity'),
                    'price_before_iva' => $prices[$num],
                    'price_after_iva' => $prices[$num]+($prices[$num]*$iva_producte)/100,
                    'applicated_iva' => $iva_producte,
                ]);
                $num_items->quantity -= Arr::get($product, 'quantity');
            }
        }
        return redirect('invoice/list');
    }

    public function list(Request $request) {
        $invoices = Invoice::find(1)->products()->orderBy('name')->get();
        print_r(Arr::get($invoices, 'id'));
        Log::info('Invoices: ', (array)$invoices);
        return view('invoice-list')->with('invoices', $invoices);
    }
}
