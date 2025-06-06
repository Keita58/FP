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
        Schema::create('preguntes', function (Blueprint $table) {
            $table->bigIncrements("idPreguntes");
            $table->text("descripcio");
            $table->foreignId('idQuestionaris')->constrained('questionaris')->references('idQuestionaris');
            $table->timestamps();
        });
        Schema::enableForeignKeyConstraints();
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::table('preguntes', function (Blueprint $table) {
            $table->dropForeign('preguntes_idQuestionaris_foreign');
        });


        Schema::dropIfExists('preguntes');
    }
};
