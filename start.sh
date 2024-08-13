#start.sh

  docker_username=""
  image_name="app"
  container_name="web"
  port=80

  #remove container
  echo "==> Remove previous container ..."
  docker rm -f ${container_name}

  echo "==> Remove previous image .."
  docker rmi -f ${image_name}

  echo "==> create new Image "
  docker build -t ${image_name} -f Dockerfile .

  echo "==> Run container"
#  docker run -d -p ${port}:${port} -e coolsms.encryptor.key.property=${KEY_PROPERTY} --name ${container_name} ${image_name}


# Remove previous containers
echo "==> Removing previous containers..."
docker-compose down

# 강제로 redis 및 web 컨테이너 제거
docker rm -f redis web || true

# Build and start the new containers
echo "==> Building and starting new containers with Docker Compose..."

#docker-compose up --build -d




