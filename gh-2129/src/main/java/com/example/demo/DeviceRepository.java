package com.example.demo;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeviceRepository extends PagingAndSortingRepository<Device, Long> {

    @Query("match (group1:Group {groupId: $groupId})-[:IS_LINKED]->(device:Device) return device limit $limit")
    List<Device> deviceByGroupId(@Param("groupId") Long groupId, @Param("limit") Integer limit);
}
