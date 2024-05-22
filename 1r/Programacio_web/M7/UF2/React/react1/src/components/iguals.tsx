import React, {useState} from "react";
import 'bootstrap/dist/css/bootstrap.css'

const Iguals = () => {
  const [num1, setNum1] = useState<number>(0);
  const [num2, setNum2] = useState<number>(0);
  const [result, setResult] = useState<string>("");
  const handleNum1Change = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNum1(Number(e.target.value));
  };
  const handleNum2Change = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNum2(Number(e.target.value));
  };
  const handleSumaClick = () => {
    if(num1 === num2)
      setResult("Els 2 elements son iguals");
    else if (num1 > num2)
      setResult("El valor del primer input, " + num1 + ", és mes gran que el valor del segon input, " + num2);
    else
      setResult("El valor del segon input, " + num2 + ", és mes gran que el valor del primer input, " + num1);
  };
  return (
    <div className="container">
      <input type="number" value={num1} onChange={handleNum1Change}/><br/>
      <input type="number" value={num2} onChange={handleNum2Change}/><br/>
      <button onClick={handleSumaClick}>Comprova</button>
      <p>{result}</p>
    </div>
  );
}

export default Iguals;