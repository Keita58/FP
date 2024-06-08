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

    public function warn(Request $request, Client $client) {
        return view('client-warn')->with('client', $client);
    }

    public function delete(Request $request, Client $client) {
        $client->delete();
        return redirect('clients');
    }

    public function insert(Request $request) {
        return view('client-insert');
    }

    public function create(Request $request) {
        $request->validate([
            'name' => 'required|string|max:255',
            'address' => 'required|string|max:1000',
            'age' => 'required|integer|max:500',
            'city' => 'required|string|max:255',
            'country' => 'required|string|max:255',
            'money' => 'required|integer|max:500',
        ]);
        $client = new Client();
        $client->name = $request->input('name');
        $client->age = $request->input('age');
        $client->address = $request->input('address');
        $client->city = $request->input('city');
        $client->country = $request->input('country');
        $client->money = $request->input('money');
        //$products->display_order = $products->id;
        $client->save();
        return redirect('clients');
    }
}
