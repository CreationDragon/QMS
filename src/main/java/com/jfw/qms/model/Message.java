package com.jfw.qms.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
    private int messageId;
    private String messageContent;
    private Integer adminId;
    private Integer replyState;
    private Integer userId;
    private String userName;
    private String userEmail;
    private String adminEnail;

    @Id
    @Column(name = "message_id", nullable = false)
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "message_content", nullable = true, length = 500)
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Basic
    @Column(name = "admin_id", nullable = true)
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "reply_state", nullable = true)
    public Integer getReplyState() {
        return replyState;
    }

    public void setReplyState(Integer replyState) {
        this.replyState = replyState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAdminEnail() {
        return adminEnail;
    }

    public void setAdminEnail(String adminEnail) {
        this.adminEnail = adminEnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (messageId != message.messageId) return false;
        if (messageContent != null ? !messageContent.equals(message.messageContent) : message.messageContent != null)
            return false;
        if (adminId != null ? !adminId.equals(message.adminId) : message.adminId != null) return false;
        if (replyState != null ? !replyState.equals(message.replyState) : message.replyState != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + (messageContent != null ? messageContent.hashCode() : 0);
        result = 31 * result + (adminId != null ? adminId.hashCode() : 0);
        result = 31 * result + (replyState != null ? replyState.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
