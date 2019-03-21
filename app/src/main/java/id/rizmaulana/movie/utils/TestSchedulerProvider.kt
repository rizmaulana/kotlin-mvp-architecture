package id.rizmaulana.movie.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler



/**
 * Created by Rizki Maulana on 09/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

class TestSchedulerProvider(private val mTestScheduler: TestScheduler) : SchedulerProvider {

    override fun ui(): Scheduler {
        return mTestScheduler
    }

    override fun computation(): Scheduler {
        return mTestScheduler
    }

    override fun io(): Scheduler {
        return mTestScheduler
    }

}