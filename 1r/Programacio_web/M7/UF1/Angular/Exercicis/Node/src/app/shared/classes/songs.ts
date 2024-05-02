export class Songs {
    idSongs! : Number;
    Nom! : string;
    Reproduccions! : Number;
    Recaptacio! : Number;


    constructor(Nom: string, Reproduccions: Number, Recaptacio: Number) {
        this.Nom = Nom;
        this.Reproduccions = Reproduccions;
        this.Recaptacio = Recaptacio;
    }
}
