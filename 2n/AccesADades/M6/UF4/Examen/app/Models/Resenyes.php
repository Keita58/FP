<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\Pivot;

class Resenyes extends Pivot
{
    protected $table = 'resenyes';
    protected $primaryKey = 'idResenyes';
    protected $fillable = ["idResenyes", "idVideojoc", "idJugador", "resenya", "valoracio", "resenya_no_editable", "PDF"];

    public function videojocs()
    {
        $this->belongsTo(Videojocs::class, "idVideojoc", "idVideojocs");
    }

    public function jugadors()
    {
        $this-> belongsTo(User::class, "idJugador", "id");
    }
}
