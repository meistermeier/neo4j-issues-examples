package com.example.discourse44560;

import java.util.List;
import java.util.Map;

public interface RelatedEntityProjection {

    String getName();

    Map<String, List<RelatedEntityProjection>> getRelatedEntities();
}
