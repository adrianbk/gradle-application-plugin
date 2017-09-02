package com.adrianbk.deployment.aws.model

import spock.lang.Specification

import static com.adrianbk.deployment.model.ApplicationEnvironmentBuilder.createEnvironment

class ConfigurationSpec extends Specification {

    def "can model an application with an s3 distribution"() {
        expect: createEnvironment('dev').build()

    }
}
