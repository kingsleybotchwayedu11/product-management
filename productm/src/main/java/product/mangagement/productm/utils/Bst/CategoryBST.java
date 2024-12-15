package product.mangagement.productm.utils.Bst;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import product.mangagement.productm.models.products.Product;
import product.mangagement.productm.utils.Bst.CategoryNode;

@Component
public class CategoryBST {
    private CategoryNode root;

    // Constructor to initialize an empty BST
    public CategoryBST() {
        root = null;
    }

    // Insert a category node into the BST
    public void insert(int categoryId, int pageNumber,  Page<Product> page) {
        root = insertRec(root, categoryId, pageNumber, page);
    }

    private CategoryNode insertRec(CategoryNode root, int categoryId, int pageNumber, Page<Product> page) {
        if (root == null) {
            root = new CategoryNode(categoryId);
            root.addPage(page, pageNumber);
        }

        // BST property: left if categoryName is less than root's category, right otherwise
        if (categoryId < root.getCategoryId()) {
            root.left = insertRec(root.left, categoryId, pageNumber, page);
        } else if (categoryId > root.getCategoryId()) {
            root.right = insertRec(root.right, categoryId, pageNumber, page);
        }

        return root;
    }

    // Search for a category node by category name
    public CategoryNode search(int categoryId) {
        return searchRec(root, categoryId);
    }

    private CategoryNode searchRec(CategoryNode root, int categoryId) {
        if (root == null || root.getCategoryId() == categoryId) {
            return root;
        }

        // Search in the left or right subtree depending on comparison
        if (categoryId < root.getCategoryId()) {
            return searchRec(root.left, categoryId);
        }

        return searchRec(root.right, categoryId);
    }

    // Method to retrieve items from a specific page of a category
    public Page<Product> getPageItems(int categoryId, int pageNumber) {
        CategoryNode categoryNode = search(categoryId);
        if (categoryNode != null) {
            return categoryNode.getPage(pageNumber);
        }
        return null;  // Return null if category or page not found
    }
    
}
