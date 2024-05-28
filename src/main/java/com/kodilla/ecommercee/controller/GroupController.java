package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.request.CreateGroupRequest;
import com.kodilla.ecommercee.dto.request.UpdateGroupRequest;
import com.kodilla.ecommercee.dto.response.GroupResponse;
import com.kodilla.ecommercee.exception.GroupHasProductsException;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shop/v1/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupResponse>> getAllGroups(@RequestParam Integer token) {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> getGroup(@PathVariable Long id, @RequestParam Integer token) throws GroupNotFoundException {
        return ResponseEntity.ok(groupService.getGroup(id));
    }

    @PostMapping
    public ResponseEntity<GroupResponse> createGroup(@RequestBody CreateGroupRequest createGroupRequest, @RequestParam Integer token) {
        return ResponseEntity.ok(groupService.addGroup(createGroupRequest));
    }

    @PutMapping
    public ResponseEntity<GroupResponse> updateGroup(@Valid @RequestBody UpdateGroupRequest updateGroupRequest, @RequestParam Integer token) throws GroupNotFoundException {
        return ResponseEntity.ok(groupService.updateGroup(updateGroupRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id, @RequestParam Integer token) throws GroupNotFoundException, GroupHasProductsException {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }
}