package com.liron.shut_the_fuck_up

import android.content.Context
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import android.media.AudioManager
import android.app.NotificationManager






class MyTileService: TileService() {
    fun setVolume(volume: Int) {
        Log.d("setting_volume", volume.toString())

        val audioManager: AudioManager = this.applicationContext.getSystemService(AUDIO_SERVICE) as AudioManager
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        val maxVolume = audioManager.getStreamMinVolume(AudioManager.STREAM_RING)
        audioManager.setStreamVolume(AudioManager.STREAM_RING, maxVolume, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);

    }

    fun unmute() {
        val audio = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audio.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_ACCESSIBILITY, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_DTMF, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_VOICE_CALL, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI)

        val notifications = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifications.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
    }

    fun mute() {
        val audio = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audio.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_ACCESSIBILITY, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_DTMF, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI)
        audio.adjustStreamVolume(AudioManager.STREAM_VOICE_CALL, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI)

        val notifications = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifications.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE)
    }

    fun printVolumes() {
        val manager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        Log.d("audio-music", manager.getStreamVolume(AudioManager.STREAM_MUSIC).toString())
        Log.d("audio-alarm", manager.getStreamVolume(AudioManager.STREAM_ALARM).toString())
        Log.d("audio-music", manager.getStreamVolume(AudioManager.STREAM_MUSIC).toString())
        Log.d("audio-ring", manager.getStreamVolume(AudioManager.STREAM_RING).toString())
        Log.d("audio-system", manager.getStreamVolume(AudioManager.STREAM_SYSTEM).toString())
    }

    override fun onTileAdded() {
        super.onTileAdded()

        // Update state
        qsTile.state = Tile.STATE_INACTIVE

        // Update looks
        qsTile.updateTile()
    }

    override fun onClick() {
        super.onClick()
        Log.d("tagged", "clicked")
        if(qsTile.state == Tile.STATE_INACTIVE) {
            // Turn on
            qsTile.state = Tile.STATE_ACTIVE

//            Log.d("mute", "enabled")
            mute()
//            printVolumes()
        } else {
            unmute()
            // Turn off
            qsTile.state = Tile.STATE_INACTIVE

//            Log.d("mute", "disabled")
            unmute()
//            printVolumes()
        }

        // Update looks
        qsTile.updateTile()
    }
}
