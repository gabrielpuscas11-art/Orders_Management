package BusinessLogic;

import DataAcces.ProductDAO;
import model.Product;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private final ProductDAO productDAO = new ProductDAO();
    /**
     * Returneaza o lista cu toate produsele existente in baza de date.
     *
     * @return Lista de obiecte Product
     */
    public List<Product> viewAllProducts() {
        return productDAO.findAll();
    }
    /**
     * Valideaza si insereaza un produs nou in sistem.
     *
     * @param product Produsul care urmeaza sa fie adaugat
     * @throws IllegalArgumentException Daca pretul sau stocul sunt negative
     */
    public void insertProduct(Product product) {
        if (product.getPrice() < 0 || product.getStock() < 0) {
            throw new IllegalArgumentException("Pretul si stocul nu pot fi negative!");
        }
        productDAO.insert(product);
    }
    /**
     * Actualizeaza datele unui produs existent, dupa verificarea ID-ului.
     *
     * @param product Produsul modificat
     * @throws NoSuchElementException Daca produsul nu este gasit in baza de date
     */
    public void updateProduct(Product product) {
        if (productDAO.findById(product.getId()) == null) {
            throw new NoSuchElementException("Produsul cu ID-ul " + product.getId() + " nu exista!");
        }
        productDAO.update(product);
    }
    /**
     * Sterge un produs din baza de date pe baza ID-ului furnizat.
     *
     * @param id Identificatorul unic al produsului de sters
     * @throws NoSuchElementException Daca stergerea a esuat din cauza unui ID inexistent
     */
    public void deleteProduct(int id) {
        if (!productDAO.delete(id)) {
            throw new NoSuchElementException("Stergerea a esuat! Produsul cu ID-ul " + id + " nu exista.");
        }
    }
}