package cz.tyckouni.mvpgate.party

import cz.tyckouni.mvpgate.party.persistence.entity.Idp
import cz.tyckouni.mvpgate.party.persistence.repository.IdpRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PartyMvpGateApplicationTests(
    @Autowired var idpRepository: IdpRepository,
) {

    @Test
    fun contextLoads() {
        val idp = Idp("idpko")
        val savedIdp = idpRepository.save(idp)

        println(savedIdp)

        assertThat(savedIdp.id).isNotNull
    }
}
