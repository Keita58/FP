<?php

namespace Database\Seeders;

use App\Models\Plataformes;
use App\Models\Resenyes;
use App\Models\User;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use App\Models\Videojocs;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        $this->call([UsersSeeder::class]);
        $this->call([PlataformesSeeder::class]);
        $this->call([VideojocsSeeder::class]);
        $this->call([ResenyesSeeder::class]);
    }
}
