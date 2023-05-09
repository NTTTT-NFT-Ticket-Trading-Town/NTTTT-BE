chmod +x gradlew
./gradlew bootJar
source .env
docker build --platform linux/amd64 -t pove2019/ntttt:${IMAGE_TAG} .
docker push pove2019/ntttt:${IMAGE_TAG}


