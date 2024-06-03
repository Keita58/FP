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
        var_dump($request->all());

        $request->validate([
            '*.quantity' => 'required|integer|max:255|min:1'
        ]);

        $client_id = $request->input('client_id');
        $products = $request->input('products');
        $invoice = new Invoice();
        $invoice->client()->associate($client_id);
        foreach ($products as $product) {
            $iva_producte = $product->iva;
            $invoice->products()->attach([
                'quantity_product' => $request->input('quantity'),
                'price_product' => $product->price,
                'price_before_iva' => $product->price,
                'price_after_iva' => ($product->price*$iva_producte)/100,
                'applicated_iva' => $iva_producte,
            ]);
            $product->save();
        }

        return redirect()->route('invoices.list');
    }

    public function list(Request $request) {
        $invoices = Invoice::with('products', 'clients')->get();
        return view('invoice-list')->with('invoices', $invoices);
    }
}
