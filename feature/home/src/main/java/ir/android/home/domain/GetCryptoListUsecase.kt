package ir.android.home.domain

import ir.androidcoder.Model.CryptoCurrency
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetCryptoListUsecase @Inject constructor(private val repository: IHomeRepository) {
    operator fun invoke(): Flow<List<CryptoCurrency>> {
        return repository.getCryptoList()
    }
}