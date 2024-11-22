package ru.pingfly.todoping

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform