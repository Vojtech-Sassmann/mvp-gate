package cz.tyckouni.mvpgate.party.application.configuration.usecase

import cz.tyckouni.mvpgate.admin.business.usecase.create.CreateIdpUseCase
import cz.tyckouni.mvpgate.admin.business.usecase.create.CreateSepInteractor
import cz.tyckouni.mvpgate.admin.business.usecase.create.CreateSepUseCase
import cz.tyckouni.mvpgate.admin.business.usecase.list.ListIdpsInteractor
import cz.tyckouni.mvpgate.admin.business.usecase.list.ListSepsInteractor
import cz.tyckouni.mvpgate.admin.business.usecase.list.ListUseCase
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.IdpSort
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.SepSort
import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.entity.Sep
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AdminUseCaseConfiguration {
    @Bean
    fun listIdpsUseCase(idpFind: cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpFind): ListUseCase<Idp, IdpSort> = ListIdpsInteractor(idpFind)

    @Bean
    fun listSepsUseCase(sepFind: cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepFind): ListUseCase<Sep, SepSort> = ListSepsInteractor(sepFind)

    @Bean
    fun createIdpUseCase(idpSave: cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpSave, idpExistsByName: cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpExistsByName, guidProvider: cz.tyckouni.mvpgate.admin.business.gateway.GuidProvider): CreateIdpUseCase =
        cz.tyckouni.mvpgate.admin.business.usecase.create.CreateIdpInteractor(idpSave, idpExistsByName, guidProvider)

    @Bean
    fun createSepUseCase(sepSave: cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepSave, sepExistsByName: cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepExistsByName, guidProvider: cz.tyckouni.mvpgate.admin.business.gateway.GuidProvider): CreateSepUseCase =
        CreateSepInteractor(sepSave, sepExistsByName, guidProvider)
}
