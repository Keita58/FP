<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;

class Client extends Model
{
    use HasFactory;

    public function commands(): HasMany
    {
        return $this->hasMany('App\Models\Invoices', 'client_id', 'id');
    }
}
