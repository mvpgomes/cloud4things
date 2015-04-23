# Pull latest image
docker_image 'cloud4things/fosstrak_epcis'

# Run container exposing port 8080
docker_container 'cloud4things/fosstrak_epcis' do
  detach true
  container_name 'fosstrak_epcis'
  link 'fosstrak_db:db'
  port '8080:8080'
end
