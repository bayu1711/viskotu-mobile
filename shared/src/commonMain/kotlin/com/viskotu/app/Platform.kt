package com.viskotu.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform