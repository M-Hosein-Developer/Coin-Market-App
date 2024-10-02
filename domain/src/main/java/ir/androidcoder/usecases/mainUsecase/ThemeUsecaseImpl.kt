package ir.androidcoder.usecases.mainUsecase

import ir.androidcoder.entities.DynamicThemeEntity
import ir.androidcoder.repositories.themeRepo.ThemeRepository

class ThemeUsecaseImpl(private val repository : ThemeRepository) : ThemeUsecase{


    override suspend fun insertDynamicThemeStateRep(state: DynamicThemeEntity) {
        repository.insertDynamicThemeStateRep(state)
    }

    override suspend fun getDynamicThemeState(): DynamicThemeEntity = repository.getDynamicThemeState()


}