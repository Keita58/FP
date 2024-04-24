"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Roba = void 0;
const Producte_1 = require("./Producte");
class Roba extends Producte_1.Producte {
    get size() {
        return this._size;
    }
    set size(value) {
        this._size = value;
    }
    get color() {
        return this._color;
    }
    set color(value) {
        this._color = value;
    }
    constructor(name, price, type, size, color) {
        super(name, price, type);
        this._size = size;
        this._color = color;
    }
    toString() {
        console.log("Nom: " + this._name + ", Preu: " + this._price + ", Mida: " + this._size + ", Color: " + this._color + ", Tipus: " + this._type);
    }
}
exports.Roba = Roba;
