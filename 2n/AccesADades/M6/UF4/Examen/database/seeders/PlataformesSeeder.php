<?php

namespace Database\Seeders;

use Carbon\Carbon;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class PlataformesSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('plataformes')->insert([
            'nom'=>"Nintendo Switch",
            'created_at'=> Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('plataformes')->insert([
            'nom'=>"PlayStation 5",
            'created_at'=> Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('plataformes')->insert([
            'nom'=>"Xbox Series",
            'created_at'=> Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('plataformes')->insert([
            'nom'=>"Nintendo Wii",
            'created_at'=> Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('plataformes')->insert([
            'nom'=>"Sega MegaDrive",
            'created_at'=> Carbon::now()->format('d/m/y H:i'),
        ]);
    }
}
