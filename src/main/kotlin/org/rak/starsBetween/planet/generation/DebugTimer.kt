package org.rak.starsBetween.planet.generation

class DebugTimer {
    private var startTime: Long = 0
    private var intervalTime: Long = 0


    /**
     * Start a new timer at the current system time.
     * Display a message including the input message.
     */
    fun start(message: String) {
        val now = System.currentTimeMillis()
        startTime = now
        intervalTime = now
        println("Starting: $message")
    }

    /**
     * Display a message that indicates the time since the last interval.
     */
    fun interval(message: String) {
        val now = System.currentTimeMillis()
        val elapsed = getElapsed(intervalTime, now)
        intervalTime = now

        println("$elapsed elapsed since interval: $message")
    }

    /**
     * Display a message that indicates the time since the timer started
     */
    fun elapsed(message: String) {
        val now = System.currentTimeMillis()
        val elapsed = getElapsed(startTime, now)

        println("$elapsed elapsed since start: $message")
    }

    private fun getElapsed(startTime: Long, endTime: Long): Long {
        return endTime - startTime
    }

}
