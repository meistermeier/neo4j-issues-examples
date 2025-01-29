package com.meistermeier.discourse72421;

import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

/**
 * @author Gerrit Meier
 */
@Node
public class Device {

    @Id
    private final String id;

    private final String name;

    @Relationship("MANAGED_BY")
    private DeviceManagerRelationship deviceManagerRelationship;

    @Version
    private Long version;

    public Device(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DeviceManagerRelationship getDeviceManagerRelationship() {
        return deviceManagerRelationship;
    }

    public void setDeviceManagerRelationship(DeviceManagerRelationship deviceManagerRelationship) {
        this.deviceManagerRelationship = deviceManagerRelationship;
    }
}
