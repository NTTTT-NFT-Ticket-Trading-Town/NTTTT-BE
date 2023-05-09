chmod +x gradlew
./gradlew bootJar
docker-compose build
#환경변수 가져오기
source .env
docker push pove2019/ntttt:${IMAGE_TAG}


