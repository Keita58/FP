import { Rol } from "./Rol.js";
export class Pokemon extends Rol {
    get tipus() {
        return this._tipus;
    }
    set tipus(value) {
        this._tipus = value;
    }
    get numAtacs() {
        return this._numAtacs;
    }
    set numAtacs(value) {
        this._numAtacs = value;
    }
    constructor(nom, historia, posicio, tipus, numAtacs) {
        super(nom, historia, posicio);
        this._tipus = tipus;
        this._numAtacs = numAtacs;
    }
}
