# Pull latest image
docker_image 'cloud4things/fosstrak_capture'

# Run container exposing port 9999
docker_container 'cloud4things/fosstrak_capture' do
  detach true
  container_name 'fosstrak_capture'
  link 'fosstrak_epcis:epcis'
  expose '9999'
end
