<?php

namespace Database\Seeders;

use Carbon\Carbon;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class RespostesSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('respostes')->insert([
            'idPregunta' => 1,
            'idAlumne' => 1,
            'idProfessor' => 1,
            'created_at'=> Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('respostes')->insert([
           'idPregunta'=>2,
           'idAlumne'=>1,
           'idProfessor'=>1,
           'valoracio'=>33,
           'created_at'=> Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('respostes')->insert([
            'idPregunta' => 3,
            'idAlumne' => 2,
            'idProfessor' => 1,
            'tancat' => true,
            'created_at'=> Carbon::now()->format('d/m/y H:i'),
        ]);
    }
}
