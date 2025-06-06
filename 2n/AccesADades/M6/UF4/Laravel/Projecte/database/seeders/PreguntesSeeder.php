<?php

namespace Database\Seeders;

use Carbon\Carbon;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class PreguntesSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        DB::table('preguntes')->insert([
            'descripcio'=>"L'alumne és a actiu a classe?",
            'idQuestionaris'=>1,
            'created_at'=> Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('preguntes')->insert([
            'descripcio'=>"L'alumne es respetuós?",
            'idQuestionaris'=>1,
            'created_at'=>  Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('preguntes')->insert([
            'descripcio'=>"L'alumne és treballador?",
            'idQuestionaris'=>2,
            'created_at'=> Carbon::now()->format('d/m/y H:i'),
        ]);
    }
}
