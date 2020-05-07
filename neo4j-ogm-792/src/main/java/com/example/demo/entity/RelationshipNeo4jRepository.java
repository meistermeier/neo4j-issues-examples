/*
 *
 *  * Copyright 2011-2019 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.example.demo.entity;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Gerrit Meier
 */
@Repository
public interface RelationshipNeo4jRepository extends Neo4jRepository<RelationshipNeo4j, Long> {

//    @Query("MATCH (e:Entity {uuid:$entity1uuid}), (e2:Entity{uuid:$entity2uuid}) CREATE (e)-[r:MENTION{uuid:$relationshipUuid,type:$type,date:$date}]->(e2) RETURN e,e2,r")
//    RelationshipNeo4j createRelationshipBetweenExistingEntities(@Param("entity1uuid") String entity1uuid, @Param("entity2uuid") String entity2uuid,
//            @Param("relationshipUuid") String relationshipUuid, @Param("type") String type, @Param("date") String date);
    @Query("MATCH (e:Entity {uuid:{entity1uuid}}), (e2:Entity{uuid:{entity2uuid}}) CREATE (e)-[r:MENTION{uuid:{relationshipUuid},type:{type},date:{date}}]->(e2) RETURN e,e2,r")
    RelationshipNeo4j createRelationshipBetweenExistingEntities(String entity1uuid, String entity2uuid, String relationshipUuid, String type, String date);

}
