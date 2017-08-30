# Gradle Application Plugin

## Overall goal

The propose of this plugin is to provide a mechanism to strongly model a software application and to provide a set 
of gradle tasks to:

1. Provision or configure the infrastructure, environments and runtimes to run the application.
2. Deploy the application to one or more environments.
3. Create or configure the continuous integration environment used by the application.

This plugin also aims to be extendable to to facilitate different, application types, distribution types, 
infrastructure providers and runtimes.


## Nomenclature

With the overall goals from the previous section in mind, there are a number of terms which are widely used in the software 
industry but often mean different things to different people and are often used in different contexts. This section aims 
to remove the ambiguity around those terms and to define what those terms mean in the context of this Gradle plugin. 

### Application

An application is considered any piece of software that can be deployed to one or more location(s) and is subsequently 
run on some piece of hardware.

An application:

- Always has a _distribution_.
- Can be deployed to one or more _environment_(s).
- May define and configure the _infrastructure_ it runs on.
- Can have one or more _deployment steps_.
- May have one or more _infrastructure provisioning_ steps. 
- May be deployed to various _infrastructure providers_ and may be run on various _runtimes_. 
- May require a specific _continuous integration profile_ or environment to build and deploy.

Here are some examples of applications:

- A web application running on Heroku.
- A web application deployed via a Kubernetes cluster.
- A web application deployed to an Amazon EC2 Container Service.
- An aws lambda deployed an running on AWS.
- A web application running on a local application server e.g tomcat.
- An android application deployed to the play store.


A very specific example of an application and the one that motivated the development of this plugin is as follows: 

- A java web application
    + Runs on 4 logical environments: `dev`, `test`, `stage` and `production`.
    + Has a _continuous integration profile_ consisting of a distribution containing an AWS cloudformation template and 
      supporting scripts to create and run one or more jenkins agents to build and deploy the application.
    + Has a _distribution_ containing a binary (war file) and some cloudformation templates to create both an AWS RDS 
      datasource and an AWS elastic beanstalk environment.
    + Gets deployed to a "test" environment on AWS elastic beanstalk backed by an AWS RDS datasource. 
    + Has it's non production environments regularly torn down and recreated. 


## Distribution  

A distribution is anything that encapsulates the artifacts needed to deploy and optionally provision or configure an 
applications infrastructure. A distribution could take many forms but a compressed archive is one form. A `distribution` 
can be sourced from, stored or moved to various locations such as an maven or ivy repository, an AWS S3 bucket, github, etc.

A distribution could contain:
- One or more deployable binaries (.jar, .war, .ear, etc.)
- A docker image.
- A docker file.
- The coordinates of a doker image in a remote docker repository.
- The scripts and configuration to [bake an AMI](https://medium.com/netflix-techblog/ami-creation-with-aminator-98d627ca37b0), 
to run the application on AWS.
- Multiple variants of an android application (.apk's targeting different devices)
- Configuration scripts or instructions to provision the applications infrastructure and runtime
- Configuration scripts or instructions to provision the "continuous integration profile" associated with the application. 


**Specific example**:

The distribution for an application consisting of 2 AWS cloudformation stacks and a deployable binary is stored 
in an AWS s3 bucket `distributions` and contains the following objects:  
  - `mydist-yyyyMMddddHHmmss/bin/my-app.war` 
  - `mydist-yyyyMMddddHHmmss/infrastructure/cf/datasource.yaml` 
  - `mydist-yyyyMMddddHHmmss/infrastructure/cf/application.yaml`

At some point in the build process, this same distribution may have been a compressed file which was expanded and 
uploaded to S3. 

## Infrastructure

Can be the network, hardware and machines, either virtual or physical, required to run and/or build an application. 
Typically, infrastructure is provided by an "infrastructure provider", AWS, GCE and Azure are well know infrastructure 
providers. Infrastructure is probably the most expansive concept here.

**Examples**:

- AWS CloudFormation: The cloudformation stacks which contain the AWS resources (RDS, Elastic Beanstalk environment) 
to run an application.
- Heroku: A Heroku application to run an application.
- Kubernetes: A kubernetes cluster used to deploy and scale an application.     

## Runtimes

A runtime is what the application runs on.

Examples:
- AWS Elastic Beanstalk: The solution stack on which an application is run e.g. `Java 8 with Tomcat 8 version 2.6.4`
- Heroku: The Heroku dyno on which the application is ran.
- Standalone Java: The SDK used to run the application (assuming the application is an executable jar)     

## Deployment steps 

An application can have several deployment steps which are the logical pieces of work required to deploy and run 
the application. Not all deployments are the same across all environments and not all possible deployment steps will 
be run every time an application is deployed. Depending on the state and lifecycle of an application environment, the 
number of and types of deployment steps can vary. 

For a new (not yet deployed application), the steps to deploy the application may require the following:

- Create infrastructure resources.
- Configure infrastructure resources.
- Create the application database.
- Seed the application database.
- Configure the application database.
- Create application runtime.
- Configure application runtime.
- Deploy application binary.
- Verify application health.
- Send a slack notification.

The same application may have a different set of deployment steps in a different context, e.g. a new version of the 
application is released and is to be deployed to a mutable environment 
(which may happen in an immutable fashion depending on where it is deployed):

- Configure application runtime.
- Migrate the application database.
- Deploy application binary. 
- Verify application health.
- Send a slack notification.

Yet another context would be a configuration-only deployment:

- Configure application runtime.
- Verify application health.
- Send a slack notification.
   

## continuous integration profile
Is a way to define the continuous integration requirements of an application, this is not always relevant but is useful 
when bespoke infrastructure and configuration is required to set up the continuous integration environment for an application.

**Example**
1. An android application requires 4 jenkins build agents, created with AWS cloudformation templates, running on AWS EC2 
instances with:
- A version of the Ubuntu OS
- The HAXM accelerator
- A particular version of the Android sdk
- Pre-configured android AVD's
- Java 8
