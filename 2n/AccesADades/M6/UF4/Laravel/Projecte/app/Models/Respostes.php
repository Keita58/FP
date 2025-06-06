<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Relations\Pivot;

//Taula creada per la relació ternària entre Pregunta, Alumne i Usuari
class Respostes extends Pivot
{
    protected $table = 'respostes';
    protected $primaryKey = 'idRespostes';
    protected $fillable = ["idRespostes", "idPregunta", "idAlumne", "idProfessor", "valoracio", "tancat"];

    public function preguntes(){
        return $this->belongsTo(Preguntes::class, 'idPregunta', 'idPreguntes');
    }

    public function alumnes(){
        return $this->belongsTo(Alumnes::class, 'idAlumne', 'idAlumnes');
    }

    public function professors(){
        return $this->belongsTo(User::class, 'idProfessor', 'id');
    }

}
