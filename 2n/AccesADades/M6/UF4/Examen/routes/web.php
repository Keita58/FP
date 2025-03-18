<?php

use App\Http\Controllers\ControllerExamen;
use App\Http\Controllers\ProfileController;
use Illuminate\Foundation\Application;
use Illuminate\Support\Facades\Route;
use Inertia\Inertia;

Route::get('/', [ControllerExamen::class, "ControladorInici"],)->middleware('auth')->name('Inici');

Route::get('/dashboard', function () {
    return Inertia::render('Dashboard');
})->middleware(['auth', 'verified'])->name('dashboard');

Route::middleware('auth')->group(function () {
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');
});

Route::post("/veureResenya", [ControllerExamen::class, "veureResenya"])->middleware('auth')->name("veureResenya");
Route::post("/afegirResenya", [ControllerExamen::class, "afegirResenya"])->middleware('auth')->name("afegirResenya");
Route::post("AfegirResenyaPOST", [ControllerExamen::class, "afegirResenyaPOST"])->middleware('auth')->name("afegirResenyaPOST");
Route::get("/finalitzarEdicioResenya", [ControllerExamen::class, "finResenya"])->middleware('auth')->name("finalitzarEdicioResenya");

require __DIR__.'/auth.php';
