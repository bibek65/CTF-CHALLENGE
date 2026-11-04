#!/bin/bash
set -e

HEALTH_URL=http://localhost:8080/actuator/health
MAX_RETRIES=12
RETRY_INTERVAL=5

echo "Validating service health at $HEALTH_URL..."
for i in $(seq 1 $MAX_RETRIES); do
  STATUS=$(curl -s -o /dev/null -w "%{http_code}" $HEALTH_URL || true)
  if [ "$STATUS" = "200" ]; then
    echo "Service is healthy."
    exit 0
  fi
  echo "Attempt $i/$MAX_RETRIES — status $STATUS. Retrying in ${RETRY_INTERVAL}s..."
  sleep $RETRY_INTERVAL
done

echo "Service failed health check after $MAX_RETRIES attempts."
exit 1
