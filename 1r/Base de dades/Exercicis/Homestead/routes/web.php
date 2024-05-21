<?php

use App\Http\Controllers\ProfileController;
use App\Http\Controllers\ProductController;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/dashboard', function () {
    return view('dashboard');
})->middleware(['auth', 'verified'])->name('dashboard');

Route::middleware('auth')->group(function () {
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');
});

Route::get('/products', [ProductController::class, 'list'])->name('products.list');
// * La llista dels productes dins la base de dades

Route::get('/product/{product}/delete', [ProductController::class, 'delete'])->name('product.delete');
// * El nom que posem entre els {} és el nom del model!!!

Route::get('/product/{product}/warn', [ProductController::class, 'warn'])->name('product.warn');
// * Avís abans d'eliminar el producte

Route::get('/product/insert', [ProductController::class, 'insert'])->name('product.insert');
// * Creem un formulari per a inserir un nou producte (això és la vista, no la inserció)

Route::post('/product/create', [ProductController::class, 'create'])->name('product.create');
// * Inserim el producte creat abans

require __DIR__.'/auth.php';
