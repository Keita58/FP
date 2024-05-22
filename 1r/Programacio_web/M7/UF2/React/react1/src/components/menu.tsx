import React from "react";
import {Link} from "react-router-dom";

const Menu = () => {
  return(
    <div className={"container"}>
      <div className={"d-flex row"}>
        {/* Per canviar l'estil del text podem posar en els links className i canviar-ho a com vulguem */}
        <Link className={"text-white text-decoration-none col"} to={"/"}>Inici</Link>
        <Link className={"text-white text-decoration-none col"} to={"/suma"}>Suma</Link>
        <Link className={"text-white text-decoration-none col"} to={"/iguals"}>Iguals</Link>
        <Link className={"text-white text-decoration-none col"} to={"/form"}>Formulari</Link>
      </div>
    </div>
  )
}

export default Menu;