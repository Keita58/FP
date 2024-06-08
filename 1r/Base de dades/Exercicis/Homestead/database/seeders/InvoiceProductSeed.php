<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class InvoiceProductSeed extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('invoice_product')->insert([
            'invoice_id' => 1,
            'product_id' => 1,
            'quantity_product' => 1,
            'price_before_iva' => 10,
            'price_after_iva' => 11,
            'applicated_iva' => 10,
            'created_at' => now(),
            'updated_at' => now()
        ]);
    }
}
