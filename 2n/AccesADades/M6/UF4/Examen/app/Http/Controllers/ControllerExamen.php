<?php

namespace App\Http\Controllers;

use App\Models\Resenyes;
use App\Models\User;
use App\Models\Videojocs;
use Illuminate\Foundation\Application;
use Illuminate\Http\Request;
use Illuminate\Routing\Route;
use Illuminate\Support\Facades\Auth;
use Inertia\Inertia;

class ControllerExamen extends Controller
{
    public function ControladorInici()
    {
        if(User::find(Auth::id()) != null)
        {
            $idJugador = Auth::id();
            $resenyes = Resenyes::where('idJugador', $idJugador)->get();
            foreach ($resenyes as $resenya) {
                $jocs = Videojocs::where('idVideojocs', $resenya->idVideojoc)->get();
            }
            return Inertia::render('LlistarResenyesUsuari', ["jocs" => $jocs]);
        }
        else {
            return Inertia::render('Welcome', ['canLogin' => Route::has('login'),
                'canRegister' => Route::has('register'),
                'laravelVersion' => Application::VERSION,
                'phpVersion' => PHP_VERSION]);
        }
    }
    public function veureResenya(Request $request) {
        $resenyes = Resenyes::where('idVideojoc', $request->idVideojoc)->get();
        return Inertia::render('LlistarResenyes', ['resenyes' => $resenyes]);
    }

    public function afegirResenya(Request $request) {
        return Inertia::render("AfegirResenya", ["videojoc" => $request->idVideojoc]);
    }

    public function afegirResenyaPOST(Request $request) {

    }

    public function finResenya() {

    }
}
