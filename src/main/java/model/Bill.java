package model;

/**
 * Reprezinta o factura generata in urma unei comenzi.
 * Aceasta structura de date este imutabila (datele nu pot fi modificate).
 *
 * @param orderId Identificatorul unic al comenzii
 * @param clientName Numele clientului
 * @param productName Numele produsului cumparat
 * @param quantity Cantitatea achizitionata
 * @param totalPrice Valoarea totala a facturii
 */
public record Bill (
    int orderId,
    String clientName,
    String productName,
    int quantity,
    double totalPrice
    ){}
