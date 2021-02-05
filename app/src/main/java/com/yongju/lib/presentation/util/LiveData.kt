package com.yongju.lib.presentation.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

private fun <T> LiveData<Event<T>>.observe(owner: LifecycleOwner, observer: Observer<T>) =
    observe(owner) { event: Event<T> -> event.call(observer::onChanged) }

fun <T> AppCompatActivity.observeEvent(liveData: LiveData<Event<T>>, observer: Observer<T>) =
    liveData.observe(this, observer)

fun <T> Fragment.observeEvent(liveData: LiveData<Event<T>>, observer: Observer<T>) =
    liveData.observe(viewLifecycleOwner, observer)

fun <T> Fragment.observe(liveData: LiveData<T>, observer: Observer<T>) =
    liveData.observe(viewLifecycleOwner, observer)

fun <T> AppCompatActivity.observe(liveData: LiveData<T>, observer: Observer<T>) =
    liveData.observe(this, observer)
