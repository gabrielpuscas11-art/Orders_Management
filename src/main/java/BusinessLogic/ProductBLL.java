package BusinessLogic;

import DataAcces.ProductDAO;
import model.Product;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private final ProductDAO productDAO = new ProductDAO();

    public List<Product> viewAllProducts() {
        return productDAO.findAll();
    }

    public void insertProduct(Product product) {
        if (product.getPrice() < 0 || product.getStock() < 0) {
            throw new IllegalArgumentException("Pretul si stocul nu pot fi negative!");
        }
        productDAO.insert(product);
    }

    public void updateProduct(Product product) {
        if (productDAO.findById(product.getId()) == null) {
            throw new NoSuchElementException("Produsul cu ID-ul " + product.getId() + " nu exista!");
        }
        productDAO.update(product);
    }

    public void deleteProduct(int id) {
        if (!productDAO.delete(id)) {
            throw new NoSuchElementException("Stergerea a esuat! Produsul cu ID-ul " + id + " nu exista.");
        }
    }
}