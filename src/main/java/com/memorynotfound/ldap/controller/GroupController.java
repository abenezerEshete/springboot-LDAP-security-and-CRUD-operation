package com.memorynotfound.ldap.controller;


import com.memorynotfound.ldap.model.Group;
import com.memorynotfound.ldap.model.Person;
import com.memorynotfound.ldap.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {


    @Autowired
    GroupRepository groupRepository;

    @GetMapping("/list")
    public List<Group> groupList() {

        List<Group> groupList = groupRepository.findAll();
        return groupList;
    }


    @PostMapping("/add/{groupName}")
    public String addPersonToGroup( @PathVariable String groupName, @RequestBody Person person) {


            groupRepository.addMemberToGroup(groupName,person);
            return "successfully add Person to group";
      }

   @PostMapping("/remove/{groupName}")
    public String removePersonToGroup( @PathVariable String groupName, @RequestBody Person person) {


            groupRepository.addMemberToGroup(groupName,person);
            return "successfully remove Person from group";
      }


}
