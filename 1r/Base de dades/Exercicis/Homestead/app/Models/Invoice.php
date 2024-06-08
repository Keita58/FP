<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;
use Illuminate\Database\Eloquent\Relations\HasOne;
use Illuminate\Database\Eloquent\Relations\HasOneOrMany;

class Invoice extends Model
{
    use HasFactory;

    public function client() : BelongsTo
    {
        return $this->belongsTo(Client::class);
    }

    public function products() : BelongsToMany
    {
        return $this->belongsToMany(Product::class)->withTimestamps()->withPivot('product_name', 'quantity_product', 'price_product', 'price_before_iva', 'price_after_iva', 'applicated_iva');
    }
}
