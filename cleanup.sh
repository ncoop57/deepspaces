#! /bin/bash

sudo docker stop $(sudo docker ps -a -q)

sudo docker service rm $(docker service ls --format '{{.Name}}')

sudo docker rm $(sudo docker ps -a -q)

sudo docker rmi -f $(sudo docker images -aq)
