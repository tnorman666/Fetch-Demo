package com.tyler_norman.fetch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/** Annotated application declaration for android apps using hilt for DI. See [MainActivity] */
@HiltAndroidApp
class FetchApplication : Application()
