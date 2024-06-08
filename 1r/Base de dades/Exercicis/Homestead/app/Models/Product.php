<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;

class Product extends Model
{
    use HasFactory;

    public function invoices() : BelongsToMany
    {
        return $this->belongsToMany(Invoice::class)->withTimestamps()->withPivot('quantity_product', 'price_before_iva', 'price_after_iva', 'applicated_iva');
    }
}
