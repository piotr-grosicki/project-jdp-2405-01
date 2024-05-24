package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.response.GroupResponse;
import com.kodilla.ecommercee.entity.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMapper {

    public GroupResponse mapToGroupResponse(Group group) {
        return new GroupResponse(
                group.getId(),
                group.getName(),
                group.getProductList()
        );
    }
    public List<GroupResponse> mapToGroupListResponse(List<Group> groups) {
        return groups.stream()
                .map(this::mapToGroupResponse)
                .toList();
    }
}
