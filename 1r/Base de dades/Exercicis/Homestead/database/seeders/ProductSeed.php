<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class ProductSeed extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('products')->insert([
            ['name'=>'Potion', 'price'=>40, 'description'=>'Cures some life'],
            ['name'=>'Small potion', 'price'=>15, 'description'=>'Cures little life'],
            ['name'=>'Mana', 'price'=>60, 'description'=>'Replenish some mana']
        ]);
    }
}
