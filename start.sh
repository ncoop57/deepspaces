#! /bin/bash

rm -fr webui-ms/models
rm -fr webui-ms/node_modules
cp -r models/ webui-ms/

cd webui-ms/
npm install

cd ..

cd docker-compose

docker-compose up --build 
