<?php

namespace App\Http\Controllers;

use App\Models\Product;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\View\View;

class ProductController extends Controller
{
    public function index() {
        $products = Product::all();
        return view('products-list')->with('products', $products); // El mateix -> return view('products', ['products', $products]);
    }

    public function delete(Request $request, Product $product) {
        $product->delete();
        return view('product-delete')->with('product', $product)->redirect()->back();;
    }
}
