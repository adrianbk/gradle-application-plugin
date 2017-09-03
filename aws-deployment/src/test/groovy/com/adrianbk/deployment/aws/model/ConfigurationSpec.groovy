package com.adrianbk.deployment.aws.model

import com.adrianbk.deployment.model.Application
import com.adrianbk.deployment.model.ApplicationBuilder
import com.adrianbk.deployment.model.ApplicationEnvironment
import com.adrianbk.deployment.model.Deployment
import spock.lang.Specification

import static com.adrianbk.deployment.aws.model.S3DistributionBuilder.s3Distribution
import static com.adrianbk.deployment.model.ApplicationEnvironmentBuilder.createEnvironment
import static com.adrianbk.deployment.model.DeploymentBuilder.deployment

class ConfigurationSpec extends Specification {

    def "can model an application with an s3 distribution"() {
        expect:
          S3Distribution s3Distribution = s3Distribution("", "").build()
          Deployment applicationCfStack = deployment(s3Distribution).infrastructure(new CloudFormationStack()).build()

          ApplicationEnvironment devEnvironment = createEnvironment('dev').deployment(applicationCfStack).build()

          Application app = ApplicationBuilder.application("name")
                  .distribution(s3Distribution)
                  .environment(devEnvironment)
                  .build()

    }
}
