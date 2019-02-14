package owlsdevelopers.org.passcoder.domain.usecases

import androidx.paging.PagedList
import owlsdevelopers.org.passcoder.data.PasscodeDataSourceFactory
import owlsdevelopers.org.passcoder.domain.core.AndroidArchPagedData
import owlsdevelopers.org.passcoder.domain.core.PagedData
import owlsdevelopers.org.passcoder.domain.core.UseCase
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.repository.PasscodeRepository


class GetPasscodes(private val passcodeRepository: PasscodeRepository) : UseCase<PagedData<Passcode>, GetPasscodes.Params>() {

    data class Params(val username: String, val pagedListConfig: PagedList.Config)

    override suspend fun run(params: Params): PagedData<Passcode> {
        val sourceFactory =
            PasscodeDataSourceFactory(passcodeRepository, params.username)

        return AndroidArchPagedData.BuildPagedData(sourceFactory = sourceFactory, config = params.pagedListConfig)
    }
}