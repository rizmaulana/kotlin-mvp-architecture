package id.rizmaulana.movie

import android.content.Context
import id.rizmaulana.movie.di.TestComponent
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Created by Rizki Maulana on 10/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
class TestComponentRule(val context: Context) : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var testComponent: TestComponent

    fun dataManager() = testComponent.getDataManager()

 /*   fun setupDagerTest() {
        val application = context.applicationContext
        testComponent = DaggerTestComponent.builder()
            .applicationTestModule(ApplicationTestModule(application))
            .build();
        application.seCom
    }

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                try {
                    setupDagerTest()
                    base?.evaluate()
                } finally {
                    testComponent = null
                }
            }
        };
    }*/


}