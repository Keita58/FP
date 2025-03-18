<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Videojocs extends Model
{
    protected $table = 'videojocs';
    protected $primaryKey = 'idVideojocs';
    protected $fillable = ["idVideojocs", "titol", "idPlataforma"];

    public function usuaris() {
        $this->belongsToMany(User::class, 'resenyes', 'idVideojoc', 'idJugador')->using(Resenyes::class)->withPivot("resenya", "valoracio", "resenya_no_editable", "PDF");
    }
}
