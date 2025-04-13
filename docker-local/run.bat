xcopy /s /y .\docker-compose.yml ..\..\

set ORIGIN=%cd%
cd ../../
set ROOT=%cd%

docker compose -f docker-compose.yml stop

cd %ROOT%/nba-statistics
call mvn -T 1C clean install -DskipTests=true

cd %ROOT%/
docker compose -f docker-compose.yml up --build -d --no-deps

cd %ORIGIN%