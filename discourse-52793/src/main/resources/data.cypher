CREATE (:MyNode{id: toString("0")}), (:MyNode{id: toString("1")})

UNWIND range(2,630) as value
CREATE (m:MyNode{id:toString(value)})
WITH value, m
MATCH (n{id:toString(value-1)}), (o{id:toString(value-2)})
WITH m, n, o
MERGE (m)-[:CONTAINS]->(n)
MERGE (m)-[:CONTAINS]->(o)
