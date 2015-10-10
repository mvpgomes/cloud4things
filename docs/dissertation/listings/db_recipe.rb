# Pull latest image
docker_image 'cloud4things/fosstrak_db'

# Run container exposing port 3306
docker_container 'cloud4things/fosstrak_db' do
  detach true
  container_name 'fosstrak_db'
  expose '3306'
  volume '/mnt/docker:/docker-storage'
end
