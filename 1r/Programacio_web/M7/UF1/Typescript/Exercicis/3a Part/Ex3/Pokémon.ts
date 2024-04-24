import {Rol} from "./Rol.js";
import {Coord} from "./Coord";

export class Pokemon extends Rol {
    private _tipus : string;
    private _numAtacs : number;

    get tipus(): string {
        return this._tipus;
    }

    set tipus(value: string) {
        this._tipus = value;
    }

    get numAtacs(): number {
        return this._numAtacs;
    }

    set numAtacs(value: number) {
        this._numAtacs = value;
    }

    constructor(nom: string, historia: string, posicio: Coord, tipus: string, numAtacs: number) {
        super(nom, historia, posicio);
        this._tipus = tipus;
        this._numAtacs = numAtacs;
    }
}