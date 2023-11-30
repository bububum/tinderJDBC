package model.entitiy;

import model.enums.OrderStatus;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private OrderStatus status;
    private Long sender;
    private Long recipient;


    public Order(Long id, LocalDateTime createdDate, LocalDateTime updatedDate, OrderStatus status, Long sender, Long recipient) {
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.status = status;
        this.sender = sender;
        this.recipient = recipient;
    }

    public Order(LocalDateTime createdDate, LocalDateTime updatedDate, OrderStatus status, Long sender, Long recipient) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.status = status;
        this.sender = sender;
        this.recipient = recipient;
    }

    public Order(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getRecipient() {
        return recipient;
    }

    public void setRecipient(Long recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", status=" + status +
                ", sender=" + sender +
                ", recipient=" + recipient +
                '}';
    }
}
