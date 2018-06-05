# Tim8NWT
Repozitorij za projekat iz predmeta NWT

Docker:
mvn clean package
docker build -t springio/naziv .
docker run -p brojPorta:brojPorta -t springio/naziv
docker-compose up
