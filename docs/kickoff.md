## Initial release

A sample application on github (spring boot java 8) 
 - Builds a deployable war `application-<version>.war`
 - Builds a zip distribution `application-dist-<version>.zip` with the following contents:
    
    ```
    bin/application-<version>.war
    infrastructure/cf/AppServer.yaml //cloudformation template which can be parameterised for each environment
    ``` 
 - Applies both the `application-plugin` and the `aws-application-plugin` 
 - Can configure one or more logical environments to deploy the application to
 - Results has a set of working gradle tasks as follows:
    - `uploadDistToS3`
    - `createOrUpdate<environment>AppServerStack`
    - `delete<environment>AppServerStack`
    - `deploy<environment>Binary`
    - `verify<environment>Health`
 
## Tech inception

##Gradle plugin implementation

- **language**: Java 1.8. Kotlin would be ideal but as of Gradle 4.1 plugin classes written in Kotlin are not seamlessly 
supported by the IDE it's not a good build authoring experience.

- Core model classes must be immutable and should not be exposed or configured in build scripts. 

- Configuration classes will be in java, despite the fact that both Kotlin and groovy provide a more
configuration friendly syntax with labeled constructor arguments. These will take the form of builder
 classes.

- Initial plugin architecture
    - `application-plugin`
    - `aws-application-plugin`

**Testing Guidelines**

- Use spock
- Unit test as much as possible
- Integration test should use real infrastructure, mocking an stubbing is just not worth it for now.

**Gradle concerns** 

- Avoid the use of internal/private gradle classes.
- Target gradle 4.x + 

