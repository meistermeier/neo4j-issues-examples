package com.meistermeier.discourse72421;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

/**
 * @author Gerrit Meier
 */
@RelationshipProperties
public class DeviceManagerRelationship {

    @RelationshipId
    public String id;

    @TargetNode
    public DeviceManager deviceManager;

    public DeviceManagerRelationship(DeviceManager deviceManager) {
        this.deviceManager = deviceManager;
    }
}
