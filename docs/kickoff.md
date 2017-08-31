## Initial release

A sample application on github (spring boot java 8) 
 - Builds a deployable war `application-<version>.war`
 - Builds a zip distribution `application-dist-<version>.zip` with the following contents:
    
    ```
    bin/application-<version>.war
    infrastructure/cf/AppServer.yaml //cloudformation template which can be parameterised for each environment
    ``` 
 - Applies both the `deployment-plugin` and the `aws-deployment-plugin` 
 - Can deploy the app to one or more logical environments(`dev`, `test`, etc.)
    - For a given environment, the app (and AWS infrastructure) can be deployed to one or more AWS regions. 

## Release 1.1
- Using the same sample application and logical environments, deploy the application to Google 
container engine (using a kuberneates cluster)

- Flesh out an example of doing a blue-green deployment with a load balancer (or maybe "ingress")
    - SSL termination and load balanced
  
## Release 1.2
- Google App Engine

## Release 1.3

- Docker image stored to a docker registry
- Deployed to AWS ECS


##Gradle plugin implementation

- **language**: Java 1.8. Kotlin would be ideal but as of Gradle 4.1 plugin classes written in Kotlin are not seamlessly 
supported by the IDE it's not a good build authoring experience.

- Core model classes must be immutable and should not be exposed or configured in build scripts. 

- Configuration classes will be in java, despite the fact that both Kotlin and groovy provide a more
configuration friendly syntax with labeled constructor arguments. These will take the form of builder
 classes.

- Initial plugin architecture
    - `deployment-plugin`
    - `aws-deployment-plugin`

**Testing Guidelines**

- Use spock
- Unit test as much as possible
- Integration test should use real infrastructure, mocking an stubbing is just not worth it for now.

**Gradle concerns** 

- Avoid the use of internal/private gradle classes.
- Target gradle 4.x + 

