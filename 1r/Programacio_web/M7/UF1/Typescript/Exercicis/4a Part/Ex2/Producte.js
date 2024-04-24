"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Producte = void 0;
class Producte {
    get name() {
        return this._name;
    }
    set name(value) {
        this._name = value;
    }
    get price() {
        return this._price;
    }
    set price(value) {
        this._price = value;
    }
    get type() {
        return this._type;
    }
    set type(value) {
        this._type = value;
    }
    constructor(name, price, type) {
        this._name = name;
        this._price = price;
        this._type = type;
    }
}
exports.Producte = Producte;
