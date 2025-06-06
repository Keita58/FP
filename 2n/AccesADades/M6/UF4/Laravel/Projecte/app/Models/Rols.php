<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Rols extends Model
{
    protected $table = 'rols';
    protected $primaryKey = 'idRols';
    protected $fillable = ["idRols", "nom"];

    //OneToMany
    public function users()
    {
        return $this->hasMany(User::class);
    }
}
