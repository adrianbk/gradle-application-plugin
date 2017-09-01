package com.adrianbk.deployment.model

import spock.lang.Specification
import spock.lang.Unroll

class ApplicationConfigTest extends Specification {

    @Unroll
    def "can not have an empty or blank application name"() {
        when:
          new Application.Builder(name)
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
        Application application = new Application.Builder("name")
                .distribution(distribution)
                .build()
        expect:
          application.name == "name"
          application.distribution == distribution
    }

    def "must have a distribution"() {
        when:
          new Application.Builder("name").build()

        then:
          def ex = thrown(RuntimeException)
          ex.message == "'distribution' must not be null"
    }

    def "can not have duplicate environment names"() {
        Application.Builder appBuilder = new Application.Builder("name")
                .distribution(new ADistribution())
                .environment(new ApplicationEnvironment(name: 'test'))
                .environment(new ApplicationEnvironment(name: 'test'))

        when:
          appBuilder.build()

        then:
          def ex = thrown(RuntimeException)
          ex.message == "There cannot be more than one environment with the name 'test'"
    }

    def "can have multiple environments"() {
        Application.Builder appBuilder = new Application.Builder("name")
                .distribution(Mock(Distribution))
                .environment(new ApplicationEnvironment(name: 'dev'))
                .environment(new ApplicationEnvironment(name: 'test'))

        expect:
          appBuilder.build().environments.spliterator().getExactSizeIfKnown() == 2
    }


    class ADistribution implements Distribution {
    }
}
