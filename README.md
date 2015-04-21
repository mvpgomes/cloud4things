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
