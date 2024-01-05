package com.example.mediawatch;

import java.util.List;

public class Group {
    private String groupId;
    private String groupName;
    private String adminId; // Add adminId field

    private List<String> members;
//    private List<String> members;

    public Group() {
    }
    public Group(String groupId, String groupName, String adminId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.adminId = adminId;

    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }



    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

//    public List<String> getMembers() {
//        return members;
//    }
//
//    public void setMembers(List<String> members) {
//        this.members = members;
//    }
}
