package com.hcbox.api.message

import java.time.LocalDateTime

class Event<K, T> {
    private var eventType: Type?
    private var key: K?
    private var data: T?
    private var eventCreatedAt: LocalDateTime?

    constructor() {
        eventType = null
        key = null
        data = null
        eventCreatedAt = null
    }

    constructor(eventType: Type?, key: K, data: T) {
        this.eventType = eventType
        this.key = key
        this.data = data
        eventCreatedAt = LocalDateTime.now()
    }

    enum class Type {
        CREATE, DELETE
    }
}
