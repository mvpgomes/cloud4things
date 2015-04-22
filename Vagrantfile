Vagrant.configure("2") do |config|
  config.vm.provision "chef_client" do |chef|
    chef.chef_server_url = "http://your.chef.server.url.com"
    chef.validation_key_path = "/path/to/your/chef/validation/key"
    chef.environment = "dev"
  end
end
