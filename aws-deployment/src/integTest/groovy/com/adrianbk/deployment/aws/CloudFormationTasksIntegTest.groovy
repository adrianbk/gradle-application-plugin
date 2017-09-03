package com.adrianbk.deployment.aws

import com.adrianbk.deployment.aws.model.S3Distribution
import com.adrianbk.deployment.aws.model.S3DistributionBuilder
import com.adrianbk.deployment.model.Application
import com.adrianbk.deployment.model.ApplicationEnvironmentBuilder
import com.adrianbk.deployment.model.DeploymentBuilder
import com.adrianbk.deployment.plugin.DeploymentExtension
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class CloudFormationTasksIntegTest extends Specification {
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

${importAllAtSameLevel(Application)}
${importAllAtSameLevel(S3Distribution)}
${importAllAtSameLevel(DeploymentExtension)}

${importAllStatic(S3DistributionBuilder)}
${importAllStatic(ApplicationEnvironmentBuilder)}
${importAllStatic(DeploymentBuilder)}

S3Distribution s3Distribution = s3Distribution("", "").build()
Deployment applicationCfStack = deployment(s3Distribution).infrastructure(new CloudFormationStack()).build()

ApplicationEnvironment devEnvironment = createEnvironment('dev').deployment(applicationCfStack).build()

Application app = ApplicationBuilder.application("name")
      .distribution(s3Distribution)
      .environment(devEnvironment)
      .build()   
      
//TODO `deployment{applications - []}` does not work 
deployment.setApplications([app])
"""

        when:
          def result = GradleRunner.create()
                  .withProjectDir(testProjectDir.root)
                  .withPluginClasspath()
                  .withArguments('tasks', '--all', '-s')
                  .build()

          println "**********${result.output}*******"

        then:
          result.task(':tasks').getOutcome() == TaskOutcome.SUCCESS
          result.output.contains("someTask - some description")
    }

    static String importAllStatic(Class<?> clazz) {
        return "import static ${clazz.name}.*"
    }

    static String importAllAtSameLevel(Class<?> clazz) {
        return "import ${clazz.package.name}.*"
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
