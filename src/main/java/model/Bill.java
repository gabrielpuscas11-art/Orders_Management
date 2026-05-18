package model;

public record Bill (
    int orderId,
    String clientName,
    String productName,
    int quantity,
    double totalPrice
    ){}
