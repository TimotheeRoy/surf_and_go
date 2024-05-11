package com.example.surf_and_go

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class BackgroundSoundService : Service() {
    internal lateinit var player: MediaPlayer
    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(applicationContext, R.raw.pure_shores)
        player.isLooping = true
        player.setVolume(100f, 100f)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        player.start()
        return START_STICKY
    }

    fun onUnBind(arg0: Intent): IBinder? {
        return null
    }

    fun onStop() {
    }

    fun onPause() {
    }

    override fun onDestroy() {
        player.stop()
        player.release()
    }

    override fun onLowMemory() {
    }

    companion object {
        private val TAG: String? = null
    }
}
