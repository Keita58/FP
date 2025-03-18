<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class ResenyesSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('resenyes')->insert([
            'idVideojoc' => 1,
            'idJugador' => 1,
            'resenya' => "aaaaaaaaaaaa",
            "valoracio" => 10,
            "resenya_no_editable" => true,
            "PDF" => null
        ]);
    }
}
