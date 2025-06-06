<?php

namespace Database\Seeders;

use App\Models\User;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void {
        $this->call([RolsSeeder::class]);
        $this->call([UserSeeder::class]);
        $this->call([AlumnesSeeder::class]);
        $this->call([QuestionarisSeeder::class]);
        $this->call([PreguntesSeeder::class]);
        $this->call([RespostesSeeder::class]);
    }
}
