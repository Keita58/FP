export class Users {
  idUser! : Number;
  nom! : string;
  password! : string;

  constructor(Nom: string, Password: string) {
    this.nom = Nom;
    this.password = Password;
  }
}
