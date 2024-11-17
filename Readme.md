
## build-and-push.sh 스크립트를 실행하면, 지정된 프로젝트들의 Docker 이미지를 빌드한 후 Docker Hub에 푸시합니다.
## docker-compose.yml 파일에서는 각 서비스에 대해 빌드된 이미지를 사용합니다.

chmod +x build-and-push.sh
./build-and-push.sh v2.0

docker-compose down
docker-compose up 
