package com.lawal.banji.yahewa.model

import android.os.CountDownTimer

class ExpirationTimer(
    private val IintervalMilliseconds: Long = 1000L,
    private val millisecondsDuration: Long,
    private val onTickCallback: (remainingTimeMillis: Long) -> Unit,
    private val onFinishCallback: () -> Unit
) {
    private var countDownTimer : CountDownTimer? = null
    
    fun start() {
        reset()
        countDownTimer = object : CountDownTimer(millisecondsDuration, IintervalMilliseconds) {
            override fun onTick(millisecondsUntilFinished: Long) { onTickCallback(millisecondsUntilFinished) }
            override fun onFinish() { onFinishCallback() }
        }
        countDownTimer?.start()
    }
    
    fun reset() {
        countDownTimer?.cancel()
        countDownTimer = null
    }
    
    fun isRunning(): Boolean {
        return countDownTimer != null
    }
}
