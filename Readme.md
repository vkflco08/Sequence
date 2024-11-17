
## build-and-push.sh 스크립트를 실행하면, 지정된 프로젝트들의 Docker 이미지를 빌드한 후 Docker Hub에 푸시합니다.
## docker-compose.yml 파일에서는 각 서비스에 대해 빌드된 이미지를 사용하며, 
## init-scripts 디렉토리를 MariaDB 컨테이너에 마운트하여 초기 데이터베이스 설정을 자동으로 실행합니다.

chmod +x build-and-push.sh
./build-and-push.sh v1.1

docker-compose down
docker-compose up 
