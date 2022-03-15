UNWIND range(0,630) as value
CREATE (m:MyNode{id:toString(value)})
WITH value, m
MATCH (n) WHERE value - 5 < toInteger(n.id) < value
MERGE (m)-[:CONTAINS]->(n)
