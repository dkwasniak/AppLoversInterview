package com.damiankwasniak.domain.di;


import io.reactivex.Scheduler;

public interface SchedulersProvider {

    Scheduler getIOScheduler();

    Scheduler getMainThreadScheduler();

}