@ECHO OFF

SET /p num1="Escriu el primer nombre: "
SET /p num2="Escriu el segon nombre: "

IF %num1% GEQ %num2% (
    echo %num1% 
) ELSE (
    echo %num2%
)

PAUSE