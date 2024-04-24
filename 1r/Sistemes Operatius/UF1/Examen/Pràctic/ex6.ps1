$direccio = './docs'
$casa = './'
$data = Get-Date -format "yyyy-MM-dd"
Compress-Archive -Path $direccio -DestinationPath "$($casa + $data)"

