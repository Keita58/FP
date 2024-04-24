import { Rol } from "./Rol.js";
export class Magic extends Rol {
    get tipus() {
        return this._tipus;
    }
    set tipus(value) {
        this._tipus = value;
    }
    get mana() {
        return this._mana;
    }
    set mana(value) {
        this._mana = value;
    }
    get element() {
        return this._element;
    }
    set element(value) {
        this._element = value;
    }
    constructor(nom, historia, posicio, tipus, mana, element) {
        super(nom, historia, posicio);
        this._tipus = tipus;
        this._mana = mana;
        this._element = element;
    }
}
