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
        Schema::create('videojocs', function (Blueprint $table) {
            $table->bigIncrements("idVideojocs");
            $table->string('titol', 80);
            $table->foreignId('idPlataforma')->constrained('plataformes')->references('idPlataformes')->onDelete('cascade');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::table('videojocs', function (Blueprint $table) {
            $table->dropForeign('videojocs_idPlataforma_foreign');
            $table->dropColumn('idPlataforma');
        });
        Schema::dropIfExists('videojocs');
    }
};
