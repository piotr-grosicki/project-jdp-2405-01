package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.request.CreateGroupRequest;
import com.kodilla.ecommercee.dto.request.UpdateGroupRequest;
import com.kodilla.ecommercee.dto.response.GroupResponse;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.exception.GroupHasProductsException;
import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public List<GroupResponse> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groupMapper.mapToGroupListResponse(groups);
    }

    public GroupResponse getGroup(Long id) throws GroupNotFoundException {
        Group group = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(String.format("Group with id: %s could not be found", id)));

        return groupMapper.mapToGroupResponse(group);
    }

    public GroupResponse addGroup(CreateGroupRequest createGroupRequest) {
        Group group = new Group(createGroupRequest.name());

        groupRepository.save(group);
        return groupMapper.mapToGroupResponse(group);
    }

    public GroupResponse updateGroup(UpdateGroupRequest updateGroupRequest) throws GroupNotFoundException {
        Group group = groupRepository.findById(updateGroupRequest.id()).orElseThrow(() -> new GroupNotFoundException(String.format("Group with id: %s could not be found", updateGroupRequest.id())));

        if (updateGroupRequest.name() != null && !updateGroupRequest.name().isEmpty() && !updateGroupRequest.name().equals(group.getName())) {
            group.setName(updateGroupRequest.name());
            groupRepository.save(group);
        }

        return groupMapper.mapToGroupResponse(group);
    }

    public void deleteGroup(Long id) throws GroupNotFoundException, GroupHasProductsException {
        Group group = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(String.format("Group with id: %s could not be found", id)));

        if (!group.getProductList().isEmpty()) {
            throw new GroupHasProductsException(String.format("Group with id: %s still has products in it and cannot be deleted", id));
        } else {
            groupRepository.delete(group);
        }
    }
}
