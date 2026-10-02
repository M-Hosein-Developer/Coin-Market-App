package ir.androidcoder.usecases.main

import ir.androidcoder.repository.IMainRepository
import javax.inject.Inject

class MainUsecase @Inject constructor(private val repository: IMainRepository) {

    fun getCryptoList() = repository.getCryptoList()

    fun getDollarPrice() = repository.getDollarPrice()
}