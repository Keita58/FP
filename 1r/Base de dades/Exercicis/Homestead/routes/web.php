<?php

use App\Http\Controllers\ControllerTest;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/test', [ControllerTest::class, 'test'])->name('test');