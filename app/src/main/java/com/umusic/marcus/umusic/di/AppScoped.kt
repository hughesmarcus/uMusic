package com.umusic.marcus.umusic.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by Marcus on 11/21/2017.
 */
@Scope
@Retention(RetentionPolicy.CLASS)
internal annotation class AppScoped