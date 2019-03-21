package id.rizmaulana.movie.utils

import io.reactivex.Scheduler

/**
 * Created by amitshekhar on 07/07/17.
 */

interface SchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}
