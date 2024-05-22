import React, {useState} from "react";
import 'bootstrap/dist/css/bootstrap.css'

const Formulari = () => {
  const [nom, setNom] = useState<string>("");
  const [cognom, setCognom] = useState<string>("");
  const [result, setResult] = useState<string>("");
  const handleNomChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNom(e.target.value);
  };
  const handleCognomChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCognom(e.target.value);
  };
  const handleNom = () => {
    if(nom !== "" && cognom !== "")
      setResult("Benvingut/da " + nom + " " + cognom + "!");
    else if (nom === "" && cognom === "")
      setResult("Has d'introduïr totes les dades!");
    else if (cognom === "")
      setResult("No has introduït el teu cognom!");
    else
      setResult("No has introduït el teu nom!");
  };
  return (
    <div className="container">
      <input type="text" placeholder={"Nom"} value={nom} onChange={handleNomChange}/><br/>
      <input type="text" placeholder={"Cognom"} value={cognom} onChange={handleCognomChange}/><br/>
      <button onClick={handleNom}>Afegeix</button>
      <p>{result}</p>
    </div>
  );
}

export default Formulari;