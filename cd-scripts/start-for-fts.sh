#!/bin/bash

# Script to start app and mocks for running functional tests
# This script starts only the necessary services (mocks and app) without the FT container

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"

echo "Starting services for functional tests..."

# Navigate to project directory
cd "$PROJECT_DIR"

# Build the application JAR if it doesn't exist or is outdated
echo "Building application..."
./gradlew :app:bootJar -x test

# Start only the mocks and app services (not the FT container)
echo "Starting WireMock and Pokedex App..."
docker-compose up -d pokeapi-mock pokedex-app

# Wait for services to be ready
echo "Waiting for services to be ready..."
sleep 5

# Check if WireMock is ready
echo "Checking WireMock health..."
until curl -s http://localhost:8081/__admin/health > /dev/null 2>&1; do
    echo "Waiting for WireMock to be ready..."
    sleep 2
done
echo "✓ WireMock is ready"

# Check if app is ready
echo "Checking Pokedex App health..."
until curl -s http://localhost:8080/actuator/health > /dev/null 2>&1; do
    echo "Waiting for Pokedex App to be ready..."
    sleep 2
done
echo "✓ Pokedex App is ready"

echo ""
echo "=========================================="
echo "Services are ready for functional tests!"
echo "=========================================="
echo "WireMock:     http://localhost:8081"
echo "Pokedex App:  http://localhost:8080"
echo ""
echo "To run functional tests, execute:"
echo "  ./gradlew :ft:test"
echo ""
echo "To stop services, run:"
echo "  docker-compose down"
echo "=========================================="

