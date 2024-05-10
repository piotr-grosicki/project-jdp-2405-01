package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.request.CreateGroupRequest;
import com.kodilla.ecommercee.dto.request.UpdateGroupRequest;
import com.kodilla.ecommercee.dto.response.GroupResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("shop/v1/group")
public class GroupController {

    @GetMapping
    public ResponseEntity<List<GroupResponse>> getAllGroups() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> getGroup(@PathVariable Long id) {
        return ResponseEntity.ok(new GroupResponse(id, "Sample group name"));
    }

    @PostMapping
    public ResponseEntity<GroupResponse> createGroup(@RequestBody CreateGroupRequest createGroupRequest) {
        return ResponseEntity.ok(new GroupResponse(1L, "Sample group name"));
    }

    @PutMapping
    public ResponseEntity<GroupResponse> updateGroup(@RequestBody UpdateGroupRequest updateGroupRequest) {
        return ResponseEntity.ok(new GroupResponse(1L, "Sample group name"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}