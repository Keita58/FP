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
        $num2 = 0;
        $sum_total = 0;
        foreach ($products as $product) {
            if(Arr::get($product, 'quantity') > 0) {
                Log::info('Product: ', $product);
                $num2++;
                $iva_producte = $ivas[$num2];
                $sum_total += $prices[$num2]+($prices[$num2]*$iva_producte)/100;
            }
        }
        foreach ($products as $product) {
            $num++;
            $num_items = Product::find($num);
            $client = Client::find($client_id);
            // * Aquest Arr::get serveix per agafar la info de la quantitat de productes, ja que està en el format {quantity: 1}
            Log::info(Arr::get($product, 'quantity'));
            if(Arr::get($product, 'quantity') > 0 && $num_items->quantity > 0 && $sum_total < $client->money) {
                $iva_producte = $ivas[$num];
                $invoice->products()->attach($num, [
                    'quantity_product' => Arr::get($product, 'quantity'),
                    'price_before_iva' => $prices[$num]*Arr::get($product, 'quantity'),
                    'price_after_iva' => ($prices[$num]+($prices[$num]*$iva_producte)/100)*Arr::get($product, 'quantity'),
                    'applicated_iva' => $iva_producte,
                ]);
                $num_items->quantity -= Arr::get($product, 'quantity');
                $num_items->save();
                $client->money -= $sum_total;
                $client->save();
            }
            else if ($num_items->quantity == 0 && Arr::get($product, 'quantity') > 0) {
                return redirect('invoice')->with('fail', 'Estàs intentant comprar ítems quan no en queden o quan no tens suficients diners');
            }
        }
        return redirect('invoice/list');
    }

    public function list(Request $request) {
        // * El with està perquè així podem agafar les dues relacions que tenim en el model i poder agafar els noms dels clients i productes sense problemes
        /*if($request != null) {
            $invoices = Invoice::with('products', 'client')->when(request('name'), function ($query, $request) {
                    return $query->where('districtname', request('district'));
                })->get();
            $clients = Client::all();
            return view('invoice-list')->with('invoices', $invoices)->with('clients', $clients);
        }
        else {*/
        $invoices = Invoice::with('products', 'client')->get();
        $clients = Client::all();
        return view('invoice-list')->with('invoices', $invoices)->with('clients', $clients);
    }
}
