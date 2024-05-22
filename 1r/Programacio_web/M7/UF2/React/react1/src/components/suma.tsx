import React, {useState} from "react";
import 'bootstrap/dist/css/bootstrap.css'

const Suma = () => {
  const [num1, setNum1] = useState<number>(0);
  const [num2, setNum2] = useState<number>(0);
  const [result, setResult] = useState<number>(0);
  const handleNum1Change = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNum1(Number(e.target.value));
  };
  const handleNum2Change = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNum2(Number(e.target.value));
  };
  const handleSumaClick = () => {
    setResult(num1 + num2);
  };
  return (
    <div className="container">
      <input type="number" value={num1} onChange={handleNum1Change}/><br/>
      <input type="number" value={num2} onChange={handleNum2Change}/><br/>
      <button onClick={handleSumaClick}>Suma</button>
      <p>El resultat és: {result}</p>
    </div>
  );
}

export default Suma;