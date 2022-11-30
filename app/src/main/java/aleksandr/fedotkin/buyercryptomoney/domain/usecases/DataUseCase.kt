package aleksandr.fedotkin.buyercryptomoney.domain.usecases

import aleksandr.fedotkin.buyercryptomoney.domain.repositories.DataRepository

class DataUseCase(
    private val dataRepository: DataRepository
) {

    suspend fun getBuyers() = dataRepository.getBuyers()

    suspend fun getSeller(id: Int) = dataRepository.getSeller(id)

    suspend fun getSnippets() = dataRepository.getSnippets()
}