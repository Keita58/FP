<?php

namespace Database\Seeders;

use Carbon\Carbon;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('users')->insert([
            'name' => 'eloi',
            'password' => Hash::make('super3'),
            'idRols' => 1,
            'email' => 'eloi@correu.cat',
            'email_verified_at' =>  Carbon::now()->format('d/m/y H:i'),
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('users')->insert([
            'name' => 'demo',
            'password' =>Hash::make('super3'),
            'idRols' => 2,
            'email' => 'demo@correu.cat',
            'email_verified_at' =>  Carbon::now()->format('d/m/y H:i'),
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('users')->insert([
            'name' => 'admin',
            'password' =>Hash::make('super3'),
            'idRols' => 3,
            'email' => 'admin@correu.cat',
            'email_verified_at' =>  Carbon::now()->format('d/m/y H:i'),
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);

    }
}
