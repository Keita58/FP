import React from 'react';
import './App.css';
import Suma from "./components/suma";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Menu from "./components/menu";
import Home from "./components/Home";
import Iguals from "./components/iguals";
import Formulari from "./components/form";

function App() {
  return (
    <BrowserRouter basename={"/"}>
      <div className={"App container bg-success"}>
        <Menu/>
      </div>
      {/* El menú està separat perquè sinó posàvem la info a dins del menú i no en un espai diferent */}
      <br/>
      <div className={"App container"}>
        <Routes>
          <Route path={"/"} element={<Home/>}/>
          <Route path={"/suma"} element={<Suma/>}/>
          <Route path={"/iguals"} element={<Iguals/>}/>
          <Route path={"/form"} element={<Formulari/>}/>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
