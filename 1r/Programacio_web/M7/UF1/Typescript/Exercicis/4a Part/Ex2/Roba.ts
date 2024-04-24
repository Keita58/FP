import {Producte} from "./Producte";

export class Roba extends Producte {
    public _size : string;
    public _color : string;

    get size(): string {
        return this._size;
    }

    set size(value: string) {
        this._size = value;
    }

    get color(): string {
        return this._color;
    }

    set color(value: string) {
        this._color = value;
    }

    constructor(name: string, price: number, type: string, size: string, color: string) {
        super(name, price, type);
        this._size = size;
        this._color = color;
    }

    toString() {
        console.log("Nom: " + this._name + ", Preu: " + this._price + ", Mida: " + this._size + ", Color: " + this._color + ", Tipus: " + this._type);
    }
}