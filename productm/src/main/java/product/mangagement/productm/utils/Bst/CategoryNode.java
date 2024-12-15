package product.mangagement.productm.utils.Bst;

import java.util.HashMap;
import java.util.List;
import org.springframework.data.domain.Page;

import product.mangagement.productm.models.products.Product;

public class CategoryNode {
    Integer categoryId;     // Name of the category
    HashMap<Integer, Page<Product>> pages;       // List of paginated pages for this category
    CategoryNode left;      // Left child
    CategoryNode right;     // Right child

    // Constructor to initialize the CategoryNode
    public CategoryNode(Integer catId) {
        this.categoryId = catId;
        this.pages = new HashMap<>();
        this.left = null;
        this.right = null;
    }

    // Get the category name
    public Integer getCategoryId() {
        return categoryId;
    }

    // Get the list of pages for this category
    public Page<Product> getPage(int pageNumber) {
        return pages.get(pageNumber);
    }



    // Add a page to the category
    public void addPage(Page<Product> page, int pageNumber ) {
        pages.put(pageNumber, page);
    }
}
