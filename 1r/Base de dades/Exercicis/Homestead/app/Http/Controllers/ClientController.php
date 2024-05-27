<?php

namespace App\Http\Controllers;

use App\Models\Client;
use Illuminate\Http\Request;

class ClientController extends Controller
{
    function list() {
        $clients = Client::all();
        return view('clients-list', ['clients' => $clients]); // El mateix que view('clients.list')->with('clients', $clients);
    }
}
