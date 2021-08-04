package example.services

import spock.lang.Specification

class PersonServiceSpec extends Specification {

    void "testme" () {
        when:
        new PersonService().runMe()
        then:
        true
    }
}
