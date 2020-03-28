package com.pooyabyte.training.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table( name = "Notification_Message_Type")
@EntityListeners(AuditingEntityListener.class)
public class NotificationMessageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn(name = "notification_message_type_id")
    private Integer id;

    @Column(name ="messageTitle")
    private String[] messageTitle;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "type")
    private String type;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customerId;

    public NotificationMessageType() {
    }

    public NotificationMessageType(String[] messageTitle, boolean isActive, String type, Customer customerId) {
        this.messageTitle = messageTitle;
        this.isActive = isActive;
        this.type = type;
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String[] getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String[] messageTitle) {
        this.messageTitle = messageTitle;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
