<?php

namespace Database\Seeders;

use Carbon\Carbon;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class VideojocsSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('videojocs')->insert([
            'titol' => 'Super Mario Galaxy',
            'idPlataforma' => 4,
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('videojocs')->insert([
            'titol' => 'Halo Infinite',
            'idPlataforma' => 3,
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('videojocs')->insert([
            'titol' => 'Astro Boy',
            'idPlataforma' => 2,
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('videojocs')->insert([
            'titol' => 'Super Mario Odyssey',
            'idPlataforma' => 1,
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('videojocs')->insert([
            'titol' => 'Sonic',
            'idPlataforma' => 5,
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);
    }
}
