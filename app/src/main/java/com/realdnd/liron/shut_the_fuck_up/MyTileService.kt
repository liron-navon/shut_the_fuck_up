package com.liron.shut_the_fuck_up

import android.content.Context
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import android.media.AudioManager
import android.app.NotificationManager

class MyTileService: TileService() {
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
        qsTile.state = Tile.STATE_INACTIVE
        qsTile.updateTile()
    }

    override fun onClick() {
        super.onClick()
        Log.d("tagged", "clicked")
        if(qsTile.state == Tile.STATE_INACTIVE) {
            qsTile.state = Tile.STATE_ACTIVE
            mute()
        } else {
            unmute()
            qsTile.state = Tile.STATE_INACTIVE
            unmute()
        }
        qsTile.updateTile()
    }
}
