package org.denis.expenseapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform