package com.adrianbk.deployment.model

import spock.lang.Specification
import spock.lang.Unroll

class ApplicationConfigTest extends Specification {

    @Unroll
    def "can not have an empty or blank application name"() {
        when:
          Application.Builder.of(name)
                  .distribution(Mock(Distribution))
                  .build()

        then:
          def ex = thrown(RuntimeException)
          ex.message == "'application name' must not be null or blank"

        where:
          name << ['       ', '']

    }

    def "has a distribution"() {
        ADistribution distribution = new ADistribution()
        Application application = Application.Builder.of("name")
                .distribution(distribution)
                .build()
        expect:
          application.name == "name"
          application.distribution == distribution
    }

    def "must have a distribution"() {
        when:
          Application.Builder.of("name").build()

        then:
          def ex = thrown(RuntimeException)
          ex.message == "'distribution' must not be null"
    }

    def "can not have duplicate environment names"() {
        Application.Builder appBuilder = Application.Builder.of("name")
                .distribution(new ADistribution())
                .environment(ApplicationEnvironment.Builder.of('test').build())
                .environment(ApplicationEnvironment.Builder.of('test').build())

        when:
          appBuilder.build()

        then:
          def ex = thrown(RuntimeException)
          ex.message == "There cannot be more than one environment with the name 'test'"
    }

    def "can have multiple environments"() {
        Application.Builder appBuilder = new Application.Builder("name")
                .distribution(Mock(Distribution))
                .environment(ApplicationEnvironment.Builder.of('dev').build())
                .environment(ApplicationEnvironment.Builder.of('test').build())

        expect:
          appBuilder.build().environments.spliterator().getExactSizeIfKnown() == 2
    }


    class ADistribution implements Distribution {
    }
}
