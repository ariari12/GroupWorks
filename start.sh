#start.sh
  docker_username=""
  image_name="app"
  container_name="web"
  port=80

  redis_container_name="redis"
  redis_image_name="redis:latest"
  redis_port=6379

  # Remove previous Redis container if it exists
  echo "==> Remove previous Redis container ..."
  docker stop ${redis_container_name}
  docker rm -f ${redis_container_name}

  # Run Redis container
  echo "==> Run Redis container"
  docker run -d -p ${redis_port}:${redis_port} --name ${redis_container_name} ${redis_image_name}

  #remove container
  echo "==> Remove previous container ..."

  docker stop ${container_name}
  docker rm -f ${container_name}

  echo "==> Remove previous image .."
  docker rmi -f ${image_name}

  echo "==> create new Image "
  docker build -t ${image_name} -f Dockerfile .

  echo "==> Run container"
  docker run -d -p ${port}:${port} --name ${container_name} ${image_name}
