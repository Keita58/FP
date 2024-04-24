@ECHO OFF

md ex2
cd ex2

for /L %%n in (1,1,10) do (
    echo %%n > %%n.txt
)