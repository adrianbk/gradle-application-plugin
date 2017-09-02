package com.adrianbk.deployment.model

import spock.lang.Specification
import spock.lang.Unroll

import static com.adrianbk.deployment.model.ApplicationEnvironmentBuilder.createEnvironment

class ApplicationBuilderTest extends Specification {

    @Unroll
    def "can not have an empty or blank application name"() {
        when:
          ApplicationBuilder.application(name)
                  .distribution(Mock(Distribution))
                  .build()

        then:
          def ex = thrown(RuntimeException)
          ex.message == "'application name' must not be null or blank"

        where:
          name << ['       ', '']
    }

    def "has a distribution"() {
        Distribution distribution = Mock(Distribution)
        Application application = ApplicationBuilder.application("name")
                .distribution(distribution)
                .build()
        expect:
          application.name == "name"
          application.distribution == distribution
    }

    def "must have a distribution"() {
        when:
          ApplicationBuilder.application("name").build()

        then:
          def ex = thrown(RuntimeException)
          ex.message == "'distribution' must not be null"
    }

    def "can not have duplicate environment names"() {
        ApplicationBuilder appBuilder = ApplicationBuilder.application("name")
                .distribution(Mock(Distribution))
                .environment(createEnvironment('test').build())
                .environment(createEnvironment('test').build())

        when:
          appBuilder.build()

        then:
          def ex = thrown(RuntimeException)
          ex.message == "There cannot be more than one environment with the name 'test'"
    }

    def "can have multiple environments"() {
        ApplicationBuilder appBuilder = ApplicationBuilder.application("name")
                .distribution(Mock(Distribution))
                .environment(createEnvironment('dev').build())
                .environment(createEnvironment('test').build())

        expect:
          appBuilder.build().environments.spliterator().getExactSizeIfKnown() == 2
    }
}
