<?php

namespace Database\Seeders;

use Carbon\Carbon;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class QuestionarisSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('questionaris')->insert([
            'idEmpresa'=>1,
            'descripcio'=>'Soy una patata',
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('questionaris')->insert([
            'idEmpresa'=>2,
            'descripcio'=>'Viva Eloi',
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('questionaris')->insert([
            'idEmpresa'=>33,
            'descripcio'=>'Danos un 10 pls',
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);
    }
}
