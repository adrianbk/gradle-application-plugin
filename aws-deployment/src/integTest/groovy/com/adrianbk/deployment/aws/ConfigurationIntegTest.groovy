package com.adrianbk.deployment.aws

import com.adrianbk.deployment.aws.model.S3DistributionBuilder
import com.adrianbk.deployment.model.ApplicationEnvironmentBuilder
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class ConfigurationIntegTest extends Specification {
    @Rule
    final TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
    }

    def "can configure and application"() {
        given:
          buildFile << """
${applyPlugin()}

import com.adrianbk.deployment.model.ApplicationBuilder;
import com.adrianbk.deployment.aws.model.S3DistributionBuilder;
import static ${ApplicationEnvironmentBuilder.name}.createEnvironment
import static ${S3DistributionBuilder.name}.create3Distribution

Application app = ApplicationBuilder
      .application("name")
        .distribution(create3Distribution('someBucket', 'someKey').build())
        .environment(createEnvironment('dev').build())
        .build()

println app

deployment {
    applications = [app]
}
"""

        when:
          def result = GradleRunner.create()
                  .withProjectDir(testProjectDir.root)
                  .withPluginClasspath()
                  .withArguments('tasks', '--all')
                  .build()

          println "**********${result.output}*******"

        then:
          result.task(':tasks').getOutcome() == TaskOutcome.SUCCESS
    }

    def applyPlugin() {
        """
        plugins {
            id 'com.adrianbk.aws-deployment'
        }
        import com.adrianbk.deployment.model.* 
        """
    }
}
