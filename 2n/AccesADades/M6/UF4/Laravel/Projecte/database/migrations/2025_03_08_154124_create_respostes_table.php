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
        Schema::disableForeignKeyConstraints();
        Schema::create('respostes', function (Blueprint $table) {
            $table->bigIncrements("idRespostes");
            $table->foreignId('idPregunta')->constrained('preguntes')->references('idPreguntes')->onDelete('cascade');
            $table->foreignId('idAlumne')->constrained('alumnes')->references('idAlumnes')->onDelete('cascade');
            $table->foreignId('idProfessor')->constrained('users')->references('id')->onDelete('cascade');
            $table->integer('valoracio')->default(0);
            $table->boolean('tancat')->default(false);
            $table->timestamps();
        });
        Schema::enableForeignKeyConstraints();
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::table('respostes', function (Blueprint $table) {
            $table->dropForeign('respostes_idAlumne_foreign');
            $table->dropColumn('idAlumne');
            $table->dropForeign('respostes_idProfessor_foreign');
            $table->dropColumn('idProfessor');
            $table->dropForeign('respostes_idPregunta_foreign');
            $table->dropColumn('idPregunta');
        });
        Schema::dropIfExists('respostes');
    }
};
