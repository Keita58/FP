<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Alumnes extends Model
{
    protected $table = 'alumnes';
    protected $primaryKey = 'idAlumnes';
    protected $fillable = ["idAlumnes", "nomComplet", "img", "questionari"];

    public function preguntes(){
        return $this->belongsToMany(Preguntes::class, "respostes", 'idAlumne', 'idPregunta')->using(Respostes::class)->withPivot('idProfessor', 'valoracio', 'tancat');
    }
}
