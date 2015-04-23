# Pull latest image
docker_image 'cloud4things/fosstrak_ale'

# Run container exposing port 8081 and remaping to port 8080
docker_container 'cloud4things/fosstrak_ale' do
  detach true
  container_name 'fosstrak_ale'
  port '8081:8080'
end
