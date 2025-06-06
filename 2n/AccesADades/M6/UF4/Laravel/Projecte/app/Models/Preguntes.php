<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Preguntes extends Model
{
    protected $table = 'preguntes';
    protected $primaryKey = 'idPreguntes';
    protected $fillable = ["idPreguntes", "descripcio", "idQuestionaris"];

    public function questionaris()
    {
        return $this->BelongsTo(Questionaris::class, 'idQuestionaris', 'idQuestionaris');

    }

    public function alumnes(){
        return $this->belongsToMany(Alumnes::class, "respostes", 'idPregunta', 'idAlumne')->using(Respostes::class)->withPivot('idProfessor', 'valoracio', 'tancat');
    }
}
