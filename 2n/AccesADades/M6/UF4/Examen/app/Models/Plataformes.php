<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Plataformes extends Model
{
    protected $table = 'plataformes';
    protected $primaryKey = 'idPlataformes';
    protected $fillable = ["idPlataformes", "nom"];
}
