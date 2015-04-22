Cloud4Things
============

Cloud4Things is a solution that aims to automate the deployment and configuration of RFID-based EPC applications in the Cloud.
In this particular case, Cloud4Things is built around of Fosstrak, an open-source platform that implements the EPCglobal GS1 specifications.

With Cloud4Things we pretend to automate the configuration and deployment of an Cloud-based application that is running in a smart place. To achieve that, Cloud4Things will allow the automate the application deployment in the Cloud and also to describe the specification of the components of the application, i.e, the RFID readers that are inside the smart place and its configuration parameters.

At the present moment, Cloud4Things relies on Docker and Chef to perform the provisioning and deployment of the application stack. For the future we pretend to compare this approach against other possible solutions, for instance to use pre-baked VMs images instead Docker containers.

##Contents
This repository contains all the resources and documentation of Cloud4Things.

- ``chef-repo/`` : Contains the recipes and configuration files that are used to provisioning and deploy the Fosstrak software stack at the Cloud.

- ``dockerfiles/`` : Contains the Dockerfiles and scripts that are used to built the  Docker images of the Fosstrak software stack, namely a **MySQL** database, the **EPCIS** repository, the **Capturing Application** and the **ALE Filtering & Collection** server.

- ``docs/`` : Contains all the technical documentation developed until the date, namely the Master Thesis Project report and Dissertation report.

- ``Vagrantfile``: A Vagrant dev/test environment of a VM pre-configured with Chef Client.

##Requirements
In order to use Cloud4Things to deploy your Fosstrak application you have two options: deploying in a Vagrant Box or deploying in a Cloud Provider.

In both variants you need to have a workstation configured with a Chef Development Kit (ChefDK). The installation and configuration instructions are available on [Install the Chef DK](https://docs.chef.io/install_dk.html).

After you verify that the installation was successful, copy all the directories that are in the ``chef-repo`` to your local ``chef-repo``.

###Deploying in a Vagrant Box
If you want just test or extend the current implementation of Cloud4Things, p.e, modifying the cookbooks or adding new ones, we strongly recommend that you use [Vagrant](http://vagrantup.com) as your test/developing environment. Vagrant provides a consistent and isolated environment that is already configured and ready to run Cloud4Things.

In order to use Vagrant with Cloud4Things you need to have Virtual Box and Vagrant installed. The installation instructions are available at [Installing Vagrant](https://www.vagrantup.com/downloads.html). After you install Vagrant the first thing that you need to do is to add a Vagrant box, that is how Vagrant calls the base images for a VM. To add a Vagrant box to your local environment execute the following command in your terminal:

```sh
$ vagrant box add chef/ubuntu-14.04
```
This command will download the box named "hashicorp/precise32" from [HashiCorp's Atlas](https://atlas.hashicorp.com/boxes/search) box catalog, a place where you can find and host boxes. You are free to choose another image of your choice, but for dev/test purpose you can use the above image.

Now that you already added a box, is time to initialize a local repository for your Vagrant environment. To do that first ``cd`` into your ``~/chef-repo`` directory and execute the command:

```sh
$ vagrant init
```

This command will generate a ``Vagrantfile``, now edit the file to looks like the Vagrantfile that is present in this repository or simply copy the file into the ``~/chef-repo`` directory. Before to launch your Vagrant environment don't forget to update the ``  chef.chef_server_url`` and ``chef.validation_key_path`` with the values of you Chef server URL and validation key path.

To launch the Vagrant environment run the following command:

```sh
$ vagrant up
```

If is the first time that you bootstrapping the Vagrant environment it will take a while because Vagrant need to download the VM image. After the process is completed you can access the environment by executing:

```sh
$ vagrant ssh
```
###Deploying in a Cloud Provider
In order to deploy your application in a Cloud provider you need to have your workstation configured with all the resources (private keys, public keys, etc.) necessary of your provider of choice.

By using Chef it is possible to perform the deploy and provisioning of resources across different providers thankfully to **knife**, a command-line tool provided by Chef that provides an interface between your local **chef-repo** and the Chef server.

**knife** allows to perform the management of several resources, including Cloud resources such as provisioning. For that, **knife** provide plugins for different Cloud providers, such as AWS, Azure and Digital Ocean. In this project our provider of choice is Amazon Web Services, but you are free to use another Cloud provider of your choice.

### Provisioning in Amazon Web Services
The first thing that you need to do is, if you don't already have, open an account in [Amazon Web Services](http://aws.amazon.com/). We strongly recommend that you also install [AWS Command Line Tools](http://aws.amazon.com/cli/) because it makes easier the configuration of credentials in AWS.  

Before to deploy the application in a EC2 instance you need to setup some configuration details through the AWS Management Console:

1. In order to ssh to an EC2 instance you need to setup a Key Pair. Go to **Services -> EC2 -> Network & Security**. Click on **Create Key Pair**, next choose a name for you key pair and download the key for a safe place in your machine, preferable the ``~/.ssh`` directory.

2. In order to accept ssh request from a external machine you need to setup the firewall of your instances. Go to **Services -> EC2 -> Network & Security**. Click on **Security Groups**, by default there is default security group already created, we will use that but you are free to create another one. In the **Inbound** tab, click on **Edit** and then add a SSH rule, in the source field you can restrict the allowed IP's or you can allow to everyone that have the private key to access the instances in this security group. Remember that you have to create more rules if you want to allow the instances to receive external requests, such as HTTP, HTTPS, etc.  

Now, in order to launch an instance on EC2 we must to install the **knife-ec2** plugin, the installation instructions are available on the [knife-ec2 repository](https://github.com/chef/knife-ec2).

If you follow the instructions now you are able to deploy an instance on EC2. In order to do that `cd` to your local `~/chef-repo` and execute the following command on your terminal:

```sh
$ knife ec2 server create -I [image-name] -f [image-type] -N [instance-name] -x ec2-user -r "role[webserver]" -i '/path/to/your/aws/ssh/key/id'
```

Lets explain better what is happening when you execute this command:

1. As you guess when you execute ``knife ec2 server create`` you are telling to knife that you want to create a new instance on Amazon EC2.

2. The ``-I [image-name]`` parameter specifies the image type that you want to use to create the instance. The available images are listed on [AWS Images]().

3. The ``-f [image-type]`` parameter describes the instance type that you want. The The available instances types are listed on [AWS Instances Types]().

4. The ``-N [instance-name]`` parameter set the name of the instance to ``instance-name``. You can give the name of your choice to each instance, since that you don't repeat the names for the instances.

5. The ``-x ec2-user`` parameter describes the username that is used to create the instance. The username ``ec2-user`` is the default name of EC2, if you don't perform any changes in the EC2 username you don't need to change that.

6. The ``-r "role[webserver]"`` parameter describes the run list that execute by the client nodes. In this example we tell to the Chef server that we want to run the recipes that are described in the role ``webserver``, but feel free to change the run list with the roles or recipes that you want.
