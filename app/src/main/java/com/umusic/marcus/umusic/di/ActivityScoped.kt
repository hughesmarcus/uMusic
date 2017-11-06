package com.umusic.marcus.umusic.di

import java.lang.annotation.*
import java.lang.annotation.Retention

import javax.inject.Scope

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScoped