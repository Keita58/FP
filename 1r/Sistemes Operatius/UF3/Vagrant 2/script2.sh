#!/bin/bash
a2ensite newsite.conf
a2dissite 000-default.conf
a2enconf main-directory
systemctl reload apache2