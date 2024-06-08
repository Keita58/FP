<?php

namespace App\Http\Controllers;

use App\Models\Product;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\View\View;

class ProductController extends Controller
{
    // * Llista de tots els productes de la base de dades
    public function list() {
        $products = Product::all();
        return view('products-list')->with('products', $products); // El mateix -> return view('products', ['products', $products]);
    }

    public function warn(Request $request, Product $product) {
        return view('product-warn')->with('product', $product);
    }

    public function delete(Request $request, Product $product) {
        $product->delete();
        return redirect('products');
    }

    public function insert(Request $request) {
        return view('product-insert');
    }

    public function create(Request $request) {
        $request->validate([
            'name' => 'required|string|max:255',
            'description' => 'required|string|max:255',
            'price' => 'required|integer|max:99999',
            'quantity' => 'required|integer|max:255',
            'iva' => 'required|integer|max:100',
        ]);
        $products = new Product();
        $products->name = $request->input('name');
        $products->description = $request->input('description');
        $products->price = $request->input('price');
        $products->quantity = $request->input('quantity');
        $products->iva = $request->input('iva');
        $products->save();
        return redirect('products');
    }
}
