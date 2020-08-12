package com.damiankwasniak.domain.extension

import com.damiankwasniak.domain.di.SchedulersProvider
import io.reactivex.*


fun <T> Flowable<T>.applyIoSchedulers(schedulersProvider: SchedulersProvider): Flowable<T> =
    compose(applyFlowableIoSchedulers(schedulersProvider))

fun <T> Observable<T>.applyIoSchedulers(schedulersProvider: SchedulersProvider): Observable<T> =
    compose(applyObservableIoSchedulers(schedulersProvider))

fun Completable.applyIoSchedulers(schedulersProvider: SchedulersProvider): Completable =
    compose(applyCompletableIoSchedulers(schedulersProvider))

fun <T> Single<T>.applyIoSchedulers(schedulersProvider: SchedulersProvider): Single<T> =
    compose(applySingleIoSchedulers(schedulersProvider))

fun <T> Maybe<T>.applyIoSchedulers(schedulersProvider: SchedulersProvider): Maybe<T> =
    compose(applyMaybeIoSchedulers(schedulersProvider))


fun <T> applyFlowableIoSchedulers(schedulersProvider: SchedulersProvider): FlowableTransformer<T, T> {
    return FlowableTransformer { flowable ->
        flowable.subscribeOn(schedulersProvider.ioScheduler)
            .observeOn(schedulersProvider.mainThreadScheduler)
    }
}

fun <T> applyObservableIoSchedulers(schedulersProvider: SchedulersProvider): ObservableTransformer<T, T> {
    return ObservableTransformer { observable ->
        observable.subscribeOn(schedulersProvider.ioScheduler)
            .observeOn(schedulersProvider.mainThreadScheduler)
    }
}

fun applyCompletableIoSchedulers(schedulersProvider: SchedulersProvider): CompletableTransformer {
    return CompletableTransformer { completable ->
        completable.subscribeOn(schedulersProvider.ioScheduler)
            .observeOn(schedulersProvider.mainThreadScheduler)
    }
}

fun <T> applySingleIoSchedulers(schedulersProvider: SchedulersProvider): SingleTransformer<T, T> {
    return SingleTransformer { single ->
        single.subscribeOn(schedulersProvider.ioScheduler)
            .observeOn(schedulersProvider.mainThreadScheduler)
    }
}

fun <T> applyMaybeIoSchedulers(schedulersProvider: SchedulersProvider): MaybeTransformer<T, T> {
    return MaybeTransformer { maybe ->
        maybe.subscribeOn(schedulersProvider.ioScheduler)
            .observeOn(schedulersProvider.mainThreadScheduler)
    }
}