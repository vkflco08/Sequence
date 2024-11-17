#!/bin/bash

# 변수 설정
PROJECTS=("login_logout" "notice_board")  # 프로젝트 디렉토리 이름
DOCKER_HUB_USERNAME="seungbinjo"           # Docker Hub 사용자 이름
VERSION=$1                                 # 버전 입력받기

# 버전 입력 확인
if [ -z "$VERSION" ]; then
  echo "Usage: ./build-and-push.sh <version>"
  exit 1
fi

# 각 프로젝트 빌드 및 푸시
for PROJECT in "${PROJECTS[@]}"; do
  echo "Building JAR file for $PROJECT..."

  # 프로젝트 디렉토리로 이동
  cd $PROJECT || { echo "Directory $PROJECT not found!"; exit 1; }

  # Gradle 빌드
  ./gradlew clean build -x test || { echo "Gradle build failed for $PROJECT"; exit 1; }

  echo "Building Docker image for $PROJECT..."

  # Docker 빌드
  docker build -t $DOCKER_HUB_USERNAME/$PROJECT:$VERSION .

  # Docker Hub로 푸시
  echo "Pushing Docker image for $PROJECT..."
  docker push $DOCKER_HUB_USERNAME/$PROJECT:$VERSION

  # 원래 디렉토리로 복귀
  cd ..

  echo "Done for $PROJECT! Image pushed as $DOCKER_HUB_USERNAME/$PROJECT:$VERSION"
done
