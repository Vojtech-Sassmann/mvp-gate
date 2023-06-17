package cz.tyckouni.mvpgate.party.application.configuration.usecase

import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.business.gateway.GuidProvider
import cz.tyckouni.mvpgate.party.business.gateway.storage.idp.IdpExistsByName
import cz.tyckouni.mvpgate.party.business.gateway.storage.idp.IdpFind
import cz.tyckouni.mvpgate.party.business.gateway.storage.idp.IdpSave
import cz.tyckouni.mvpgate.party.business.gateway.storage.sep.SepExistsByName
import cz.tyckouni.mvpgate.party.business.gateway.storage.sep.SepFind
import cz.tyckouni.mvpgate.party.business.gateway.storage.sep.SepSave
import cz.tyckouni.mvpgate.party.business.usecase.create.CreateIdpInteractor
import cz.tyckouni.mvpgate.party.business.usecase.create.CreateIdpUseCase
import cz.tyckouni.mvpgate.party.business.usecase.create.CreateSepInteractor
import cz.tyckouni.mvpgate.party.business.usecase.create.CreateSepUseCase
import cz.tyckouni.mvpgate.party.business.usecase.list.ListIdpsInteractor
import cz.tyckouni.mvpgate.party.business.usecase.list.ListSepsInteractor
import cz.tyckouni.mvpgate.party.business.usecase.list.ListUseCase
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.IdpSort
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.SepSort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AdminUseCaseConfiguration {
    @Bean
    fun listIdpsUseCase(idpFind: IdpFind): ListUseCase<Idp, IdpSort> = ListIdpsInteractor(idpFind)

    @Bean
    fun listSepsUseCase(sepFind: SepFind): ListUseCase<Sep, SepSort> = ListSepsInteractor(sepFind)

    @Bean
    fun createIdpUseCase(idpSave: IdpSave, idpExistsByName: IdpExistsByName, guidProvider: GuidProvider): CreateIdpUseCase =
        CreateIdpInteractor(idpSave, idpExistsByName, guidProvider)

    @Bean
    fun createSepUseCase(sepSave: SepSave, sepExistsByName: SepExistsByName, guidProvider: GuidProvider): CreateSepUseCase =
        CreateSepInteractor(sepSave, sepExistsByName, guidProvider)
}
