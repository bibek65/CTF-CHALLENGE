#!/bin/sh
set -e

SPRING_PROFILE=${SPRING_PROFILES_ACTIVE:-local}
echo "Starting with profile: $SPRING_PROFILE"

exec java \
  -Djava.security.egd=file:/dev/./urandom \
  -Dspring.profiles.active=$SPRING_PROFILE \
  -jar app.jar
