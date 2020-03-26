package com.example.domain.interactors.base

import io.reactivex.Scheduler

abstract class BaseInteractor(
    protected val mainThread: Scheduler,
    protected val workerThread: Scheduler
)