#!/bin/bash

# build project
docker-compose run --rm builder ./mvnw clean package -DskipTests=true

# copy wars
rm deployments-client/*
cp client/target/*.war deployments-client/ROOT.war

rm deployments-server/*
cp server/server-ear/target/*.ear deployments-server/server-ear.ear
