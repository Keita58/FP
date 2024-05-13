export class Users {
  idUser! : Number;
  nom! : string;
  password! : string;
  punts! : number;

  constructor(Nom: string, Password: string) {
    this.nom = Nom;
    this.password = Password;
    this.punts = 0;
  }
}
