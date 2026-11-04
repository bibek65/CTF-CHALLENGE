#!/bin/bash
set -e

CONTAINER_NAME=app
IMAGE=${ECR_REGISTRY}/${ECR_REPOSITORY}:${IMAGE_TAG:-latest}

docker run -d \
  --name $CONTAINER_NAME \
  --restart unless-stopped \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-prod} \
  $IMAGE
