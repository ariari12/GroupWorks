#start.sh

# Remove previous containers
echo "==> Removing previous containers..."
docker-compose down

# Build and start the new containers
echo "==> Building and starting new containers with Docker Compose..."
docker-compose up --build -d
