package id.rizmaulana.movie.di

import dagger.Component
import id.rizmaulana.movie.di.component.AppComponent
import javax.inject.Singleton

/**
 * Created by Rizki Maulana on 10/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
@Singleton
@Component(modules = [ApplicationTestModule::class])
interface TestComponent : AppComponent
