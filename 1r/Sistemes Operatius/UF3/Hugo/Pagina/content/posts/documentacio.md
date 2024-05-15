+++
title = 'Documentacio'
date = 2024-05-15T20:10:47+02:00
draft = true
+++

##Com he posat el nou tema de Docsy

#Preliminars

He instal·lat npm install -D autoprefixer, npm install -D postcss-cli i npm install -D postcss en la carpeta principal.

#Inici

Abans d'instal·lar res, en el hugo.toml he posat uns [[module.import]] amb els mòduls que he instal·lat, en aquest cas github.com/google/docsy.
Per posar això ho he posat de la següent manera:
...
[module]
  [module.hugoVersion]
    extended = true
    min = "0.73.0"
  [[module.imports]]
    path = "github.com/google/docsy"
    disable = false

Una vegada instal·lat l'anterior (i havent instal·lat [go] [https://go.dev/doc/install]) he executat hugo mod init gitlab.com/Keita58/pagina-hugo i hugo mod get github.com/google/docsy.

Enllaços:
  - https://www.docsy.dev/docs/get-started/docsy-as-module/
  - https://www.docsy.dev/docs/get-started/docsy-as-module/installation-prerequisites/#install-postcss

