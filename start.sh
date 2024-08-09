#start.sh
  docker_username=""
  image_name="app"
  container_name="web"
  port=80

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
