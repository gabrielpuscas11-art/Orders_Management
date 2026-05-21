package model;

public class Client {
    private int id;
    private String name;
    private String email;
    private String address;

    public Client(){}

    /**
     * Constructor cu parametri pentru initializarea completa a unui client.
     *
     * @param id Identificatorul unic al clientului
     * @param name Numele complet
     * @param email Adresa de e-mail
     * @param address Adresa de domiciliu sau livrare
     */
    public Client(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
