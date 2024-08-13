#start.sh

# Remove previous containers
echo "==> Removing previous containers..."
docker-compose down

# 강제로 redis 및 web 컨테이너 제거
docker rm -f redis web || true

# Build and start the new containers
echo "==> Building and starting new containers with Docker Compose..."
docker-compose up --build -d




