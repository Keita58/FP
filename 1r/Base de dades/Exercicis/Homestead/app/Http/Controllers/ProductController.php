<?php

namespace App\Http\Controllers;

use App\Models\Product;
use Illuminate\Http\Request;
use Illuminate\View\View;

class ProductController extends Controller
{
    public function get()
    {
        $products = Product::all();
        return view('products', compact('products'));
    }
}
