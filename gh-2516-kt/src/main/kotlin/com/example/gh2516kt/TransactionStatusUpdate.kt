package com.example.gh2516kt

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node
class TransactionStatusUpdate {
    @Id
    @GeneratedValue
    var id: Long? = null
    private val updateText: String? = null
}

