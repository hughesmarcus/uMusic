package com.umusic.marcus.umusic.di

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope
import java.lang.annotation.Retention
import java.lang.annotation.Target

@Scope
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE, ElementType.METHOD)
annotation class FragmentScoped