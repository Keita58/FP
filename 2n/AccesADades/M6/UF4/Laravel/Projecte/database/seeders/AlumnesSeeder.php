<?php

namespace Database\Seeders;

use Carbon\Carbon;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class AlumnesSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('alumnes')->insert([
            'nomComplet' => 'eloiAlumne',
            'img' => null,
            'questionari' => true,
            'created_at' => Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('alumnes')->insert([
            'nomComplet' => 'hector',
            'img' => null,
            'created_at' => Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('alumnes')->insert([
            'nomComplet' => 'eric',
            'img' => null,
            'created_at' => Carbon::now()->format('d/m/y H:i'),
        ]);
    }
}
