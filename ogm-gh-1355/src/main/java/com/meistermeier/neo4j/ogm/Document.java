package com.meistermeier.neo4j.ogm;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Document extends BaseEntity {
    private String documentId;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
