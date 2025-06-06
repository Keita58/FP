<?php

namespace App\Http\Middleware;

use App\Models\User;
use Closure;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Symfony\Component\HttpFoundation\Response;

class MidPermisos
{
    /**
     * Handle an incoming request.
     *
     * @param  \Closure(\Illuminate\Http\Request): (\Symfony\Component\HttpFoundation\Response)  $next
     */

    /**
     * Per poder posar "middleware->("permisos") hem de posar
     *
     * $middleware->alias([
     *  'permisos'=>MidPermisos::class
     * ]);
     *
     * al fitxer d'app (bootstrap/app.php) a dins de ->withMiddleware(function (Middleware $middleware)
     */

    public function handle(Request $request, Closure $next): Response
    {
        $otrouser = User::find(Auth::id());
        if($otrouser->idRols !=2){
            return redirect('sensePermisos');
        }
        return $next($request);
    }
}
