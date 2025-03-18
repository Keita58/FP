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
        Schema::create('resenyes', function (Blueprint $table) {
            $table->bigIncrements("idResenyes");
            $table->foreignId('idVideojoc')->constrained('videojocs')->references('idVideojocs')->onDelete('cascade');
            $table->foreignId('idJugador')->constrained('users')->references('id')->onDelete('cascade');
            $table->string('resenya')->nullable();
            $table->integer("valoracio")->default(0);
            $table->boolean("resenya_no_editable")->default(false);
            $table->text("PDF")->nullable();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::table('resenyes', function (Blueprint $table) {
            $table->dropForeign('resenyes_idVideojoc_foreign');
            $table->dropColumn('idVideojoc');
            $table->dropForeign('resenyes_idJugador_foreign');
            $table->dropColumn('idJugador');
        });
        Schema::dropIfExists('resenyes');
    }
};
