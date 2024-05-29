#!/bin/bash
apt update
DEBIAN_FRONTEND=noninteractive apt -y upgrade
adduser marc << EOF
1234
1234
EOF
usermod -aG sudo marc