<?php

namespace Database\Seeders;

use Carbon\Carbon;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class RolsSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {

        DB::table('rols')->insert([
            'nom' => 'Professor',
            'created_at' => Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('rols')->insert([
            'nom' => 'Coordinador de Qualitat',
            'created_at' => Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('rols')->insert([
            'nom' => 'administrador',
            'created_at' => Carbon::now()->format('d/m/y H:i'),
        ]);
    }
}
