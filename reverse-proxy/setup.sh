#! /bin/bash

nginx
rm -f /etc/nginx/sites-enabled/default
nginx -s reload

sleep infinity
