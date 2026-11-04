#!/bin/bash
set -e

APP_DIR=/opt/app
CONTAINER_NAME=app

echo "Stopping existing container..."
docker stop $CONTAINER_NAME 2>/dev/null || true
docker rm $CONTAINER_NAME 2>/dev/null || true

echo "Starting application container..."
bash $APP_DIR/deploy/run_container.sh

echo "Deployment complete."
