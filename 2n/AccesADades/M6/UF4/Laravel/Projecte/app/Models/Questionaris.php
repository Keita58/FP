<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Questionaris extends Model
{
    protected $table = 'questionaris';
    protected $primaryKey = 'idQuestionaris';
    protected $fillable = ["idQuestionaris", "idEmpresa", "descripcio"];

    public function preguntes()
    {
        return $this->hasMany(Preguntes::class);
    }


}
