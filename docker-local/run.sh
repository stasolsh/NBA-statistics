#!/usr/bin/env bash

cp -r ./docker-compose.yml ../../

ORIGIN=$(pwd)
cd ../../
ROOT=$(pwd)

docker-compose -f docker-compose.yml stop

cd "$ROOT"/nba-statistics
mvn -T 1C clean install -DskipTests=true

cd "$ROOT"/
docker-compose -f docker-compose.yml up --build -d --no-deps

cd "$ORIGIN"