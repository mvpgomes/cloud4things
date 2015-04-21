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

- ``Vagrantfile``: Contains a description of a VM pre-configured with Chef. ***TODO***

##Requirements
In order to use Cloud4Things to deploy your Fosstrak application you have two options: deploying in a Vagrant Box or deploying in a Cloud Provider.

In both variants you need to have a workstation configured with a Chef Development Kit (ChefDK). The installation and configuration instructions are available on [Install the Chef DK](https://docs.chef.io/install_dk.html).

After you verify that the installation was successful, copy all the directories that are in the ``chef-repo`` to your local ``chef-repo``.

###Deploying in a Vagrant Box


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
