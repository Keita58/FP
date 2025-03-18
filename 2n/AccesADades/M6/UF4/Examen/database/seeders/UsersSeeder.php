<?php

namespace Database\Seeders;

use Carbon\Carbon;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class UsersSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('users')->insert([
            'name' => 'marc',
            'password' => Hash::make('super3'),
            'email' => 'marc@correu.cat',
            'email_verified_at' =>  Carbon::now()->format('d/m/y H:i'),
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('users')->insert([
            'name' => 'hector',
            'password' => Hash::make('super3'),
            'email' => 'hector@correu.cat',
            'email_verified_at' =>  Carbon::now()->format('d/m/y H:i'),
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);
        DB::table('users')->insert([
            'name' => 'eric',
            'password' => Hash::make('super3'),
            'email' => 'eric@correu.cat',
            'email_verified_at' =>  Carbon::now()->format('d/m/y H:i'),
            'created_at' =>  Carbon::now()->format('d/m/y H:i'),
        ]);
    }
}
