<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('alumnes', function (Blueprint $table) {
            $table->bigIncrements("idAlumnes");
            $table->string('nomComplet', 30);
            $table->text("img")->nullable();
            $table->boolean("questionari")->default(false);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('alumnes');
    }
};
