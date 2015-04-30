Vagrant.configure("2") do |config|

  config.vm.box = "ubuntu/trusty64"

  config.vm.provision :chef_solo do |chef|
    chef.roles_path = "~/chef-repo/roles"
    chef.cookbooks_path = "~/chef-repo/cookbooks"
    chef.add_role "webserver"
  end

end
