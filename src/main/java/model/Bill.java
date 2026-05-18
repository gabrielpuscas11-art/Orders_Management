package model;

public record Bill (
    int order_id,
    String client_name,
    String product_name,
    int quantity,
    double total_price
    ){}
