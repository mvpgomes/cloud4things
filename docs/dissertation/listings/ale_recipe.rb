# Pull latest image
docker_image 'cloud4things/fosstrak_ale'

# Run container exposing port 8080 and remaping to port 8080
docker_container 'cloud4things/fosstrak_ale' do
  detach true
  container_name 'fosstrak_ale'
  link 'fosstrak_capture:capture'
  port ["8080:8080", "5556:5556"]
end
