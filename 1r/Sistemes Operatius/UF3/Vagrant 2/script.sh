#!/bin/bash
apt update
DEBIAN_FRONTEND=noninteractive apt -y upgrade
apt install apache2 << EOF
y
EOF