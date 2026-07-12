#!/usr/bin/env sh

# Simple gradlew wrapper for CI
# Real projects use a generated script; this is enough for basic CI with gradle-wrapper.jar

DIR="$(cd "$(dirname "$0")" && pwd)"
GRADLE_WRAPPER_JAR="$DIR/gradle/wrapper/gradle-wrapper.jar"

if [ ! -f "$GRADLE_WRAPPER_JAR" ]; then
  echo "gradle-wrapper.jar not found. This minimal wrapper expects it to exist."
  exit 1
fi

exec java -jar "$GRADLE_WRAPPER_JAR" "$@"
