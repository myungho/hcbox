package com.hcbox.api.message

import com.hcbox.api.dto.kafka.serializer.DateSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class Event<K, T> {
    private var eventType: Type?
    private var key: K?
    private var data: T?

    @Serializable(DateSerializer::class)
    private var eventCreatedAt: Date?

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
        eventCreatedAt = Date()
    }

    enum class Type {
        CREATE, DELETE
    }
}
