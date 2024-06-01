#!/bin/bash
apt update
DEBIAN_FRONTEND=noninteractive apt -y upgrade
apt install apache2 << EOF
y
EOF
mkdir /www/html
cd /www/html
touch index.html << EOF
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Test page</title>
  </head>
  <body>
    <h1>Test page</h1>
  </body>
</html>
EOF
touch /etc/apache2/sites-avaliable/newsite.conf << EOF
<VirtualHost *:8080>
	ServerAdmin webmaster@localhost
	DocumentRoot ./www/html

	ErrorLog ${APACHE_LOG_DIR}/error.log
	CustomLog ${APACHE_LOG_DIR}/access.log combined
</VirtualHost>
EOF
a2ensite newsite.conf
a2dissite 000-default.conf
nano /etc/apache2/conf-avaliable/main-directory.conf << EOF
<Directory ./www/html>
  Options Indexes FollowSymLinks
  AllowOverride All
  Require all granted
</Directory>
systemctl reload apache2