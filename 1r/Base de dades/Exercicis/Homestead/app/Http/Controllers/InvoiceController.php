<?php

namespace App\Http\Controllers;

use App\Models\Client;
use App\Models\Invoice;
use App\Models\Product;
use Illuminate\Http\Request;
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
        /*$request->validate([
            '*.quantity' => 'required|integer|max:255|min:0'
        ]);*/
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
        $invoice->client()->associate($client_id);
        $num = 0;
        foreach ($products as $product => $quantity_product) {
            $num++;
            $product = Product::find($num);
            Log::info('Quantitat: ', $quantity_product);
            if($quantity_product > 0){
                $iva_producte = $ivas[$num];
                $invoice->products()->attach($num, [
                    'quantity_product' => $quantity_product,
                    'price_before_iva' => $prices[$num],
                    'price_after_iva' => $prices[$num]+($prices[$num]*$iva_producte)/100,
                    'applicated_iva' => $iva_producte,
                ]);
                $product->quantity -= $quantity_product;
            }
        }
        $invoice->save();
        return redirect()->back();
    }

    public function list(Request $request) {
        $invoices = Invoice::with('products', 'clients')->get();
        return view('invoice-list')->with('invoices', $invoices);
    }
}
