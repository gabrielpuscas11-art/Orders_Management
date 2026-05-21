package model;

public class Orders {
    private int id;
    private int productId;
    private int clientId;
    private int quantity;

    /**
     * Constructor cu parametri pentru initializarea unei comenzi.
     *
     * @param id Identificatorul unic al comenzii
     * @param clientId ID-ul clientului care plaseaza comanda
     * @param productId ID-ul produsului comandat
     * @param quantity Cantitatea de produse solicitata
     */
    public Orders(int id, int clientId, int productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.clientId = clientId;
        this.quantity = quantity;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
