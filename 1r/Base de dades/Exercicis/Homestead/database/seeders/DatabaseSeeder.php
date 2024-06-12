<?php

namespace Database\Seeders;

use App\Models\Invoice;
use App\Models\User;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        // User::factory(10)->create();
        $this->call(ProductSeed::class);
        $this->call(ClientSeed::class);
        $this->call(InvoiceSeed::class);
        $this->call(InvoiceProductSeed::class);
        $this->call(FranchiseSeed::class);
    }
}
