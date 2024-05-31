<?php

namespace App\Http\Controllers;

use App\Models\Client;
use App\Models\Product;
use Illuminate\Http\Request;

class InvoiceController extends Controller
{
    public function invoice(Request $request) {
        $products = Product::all();
        $clients = Client::all();
        //print($clients);
        return view('invoices')->with('clients', $clients)->with('products', $products);
    }

    public function create(Request $request) {
        $request->validate([
            'name' => 'required|string|max:255',
            'description' => 'required|string|max:255',
            'price' => 'required|integer|max:99999',
            'quantity' => 'required|integer|max:255'
        ]);
        $products = new Product();
        $products->name = $request->input('name');
        $products->description = $request->input('description');
        $products->price = $request->input('price');
        $products->quantity = $request->input('quantity');
        //$products->display_order = $products->id;
        $products->save();
        return redirect('products');
    }
}
