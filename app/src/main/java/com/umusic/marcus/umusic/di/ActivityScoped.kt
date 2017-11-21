package com.umusic.marcus.umusic.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@MustBeDocumented
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScoped