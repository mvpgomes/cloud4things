# Pull latest image
docker_image 'cloud4things/fosstrak-epcis'

# Run container exposing port 8080
docker_container 'cloud4things/fosstrak-epcis' do
  detach true
  container_name 'fosstrak-epcis'
  link 'fosstrak_db:db'
  expose '8080'
end
