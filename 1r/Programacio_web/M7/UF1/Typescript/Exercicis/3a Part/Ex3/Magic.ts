import {Rol} from "./Rol.js";
import {Coord} from "./Coord";

export class Magic extends Rol {
    private _tipus : string;
    private _mana : number;
    private _element : string;

    get tipus(): string {
        return this._tipus;
    }

    set tipus(value: string) {
        this._tipus = value;
    }

    get mana(): number {
        return this._mana;
    }

    set mana(value: number) {
        this._mana = value;
    }

    get element(): string {
        return this._element;
    }

    set element(value: string) {
        this._element = value;
    }

    constructor(nom: string, historia: string, posicio: Coord, tipus: string, mana: number, element: string) {
        super(nom, historia, posicio);
        this._tipus = tipus;
        this._mana = mana;
        this._element = element;
    }
}