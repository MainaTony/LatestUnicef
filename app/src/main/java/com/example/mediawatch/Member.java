package com.example.mediawatch;

public class Member {
    private String memberId;
    private String groupId; // ID of the group
    private String userId;  // ID of the user added as a member
    private String memberName; // Name of the member created by the admin

    // Constructors, getters, setters

    public Member() {
    }

    public Member(String memberId, String groupId, String userId, String memberName) {
        this.memberId = memberId;
        this.groupId = groupId;
        this.userId = userId;
        this.memberName = memberName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}

