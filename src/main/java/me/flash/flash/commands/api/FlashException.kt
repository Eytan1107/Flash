package me.flash.flash.commands.api

class FlashException(msg: String) : RuntimeException("") {
    private val serialVersionUID = 1L
    private var msg: String? = msg

    fun getMsg() : String? {
        return msg
    }
}
