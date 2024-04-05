package com.example.stupa.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Application level declaration for hilt
@HiltAndroidApp
class StupaApp : Application()