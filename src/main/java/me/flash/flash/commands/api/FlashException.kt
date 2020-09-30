package me.flash.flash.commands.api

class FlashException(private var messages: String) : RuntimeException("") {

    companion object {
        private const val serialVersionUID = 1L
    }
}
