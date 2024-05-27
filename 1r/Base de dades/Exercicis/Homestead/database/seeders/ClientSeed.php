<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class ClientSeed extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('clients')->insert([
            ['name'=>'Marc', 'age'=>23, 'address'=>'c/Grugliasco, 61', 'city'=>'Barberà del Vallès', 'country'=>'Catalunya','created_at'=>now(), 'updated_at'=>now()]
        ]);
    }
}
