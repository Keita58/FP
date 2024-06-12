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
        /**
         * La taula pivot té:
         * - Id de la comanda.
         * - Id del producte que es compra.
         * - La quantitat del producte que es compra.
         * - El preu abans d'afegir l'IVA.
         * - El preu després d'afegir l'IVA.
         * - L'IVA afegit al producte.
         * - El moment en el que es realitza la compra.
         */
        Schema::create('invoice_product', function (Blueprint $table) {
            $table->id();
            $table->unsignedBiginteger('invoice_id');
            $table->unsignedBiginteger('product_id');
            $table->integer('quantity_product');
            $table->integer('price_before_iva')->unsigned();
            $table->integer('price_after_iva')->unsigned();
            $table->integer('applicated_iva')->unsigned();
            $table->timestamps();

            $table->foreign('invoice_id')->references('id')->on('invoices')->onDelete('cascade');
            $table->foreign('product_id')->references('id')->on('products')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('invoice_product');
    }
};
