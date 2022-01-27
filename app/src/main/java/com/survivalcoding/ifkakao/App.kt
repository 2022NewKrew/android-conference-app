package com.survivalcoding.ifkakao

import android.app.Application
import com.survivalcoding.ifkakao.data.datasource.local.MockLocalDataSource
import com.survivalcoding.ifkakao.data.datasource.remote.RetrofitClient
import com.survivalcoding.ifkakao.data.datasource.remote.SessionRemoteDataSource
import com.survivalcoding.ifkakao.data.repository.SessionRepositoryImpl
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository
import com.survivalcoding.ifkakao.domain.repository.SessionRemoteRepository
import com.survivalcoding.ifkakao.domain.usecase.GetHighLightedUseCase
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsUseCase
import com.survivalcoding.ifkakao.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class App : Application() {
    /*
    val sessionRepository: SessionRepository by lazy {
        SessionRepositoryImpl(
            //MockRemoteDataSource(),
            SessionRemoteDataSource(RetrofitClient.apiService),
            MockLocalDataSource(),
            /*
            SessionLocalDataSource(
                Room.databaseBuilder(
                    this,
                    IfKakaoDatabase::class.java,
                    "database"
                ).build().likeDao()
            )*/
        )
    }*/

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger(
                if (BuildConfig.DEBUG) Level.ERROR else Level.NONE
            )
            //inject Android context
            androidContext(this@App)
            // use modules
            modules(
                listOf(
                    repositoryModules,
                    viewModelsModules,
                    useCaseModule,
                    dataSourceModules,
                    networkModule
                )
            )
        }
    }

    private val viewModelsModules = module {
        viewModel { MainViewModel(get()) }
    }

    val useCaseModule = module {
        //factory { FindIfLikingUseCase(get()) }
        //factory { GetLikesUseCase(get()) }
        //factory { GetRelatedSessionsUseCase(get()) }
        factory { GetSessionsUseCase(get()) }
        //factory { GetSessionByIdUseCase(get()) }
        factory { GetHighLightedUseCase(get()) }
    }

    private val repositoryModules = module {
        single { SessionRepositoryImpl(get(), get()) }
    }

    private val dataSourceModules = module {
        single<SessionRemoteRepository> {
            SessionRemoteDataSource(get())
        }
        single<SessionLocalRepository> { MockLocalDataSource() }
    }

    private val networkModule = module {
        single { RetrofitClient.apiService }
    }
}