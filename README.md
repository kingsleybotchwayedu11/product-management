# Project Management System Documentation

## Overview
This project management system (PMS) allows administrators to manage various aspects of a product catalog. The system includes functionalities for uploading products, managing categories, and performing CRUD (Create, Read, Update, Delete) operations on products, categories, and images. It is built using **Java Spring Boot** and follows the typical structure of a modern web application with an API layer to handle all necessary operations.

### Key Features:
1. **Product Upload**: Admins can add new products to the catalog.
2. **Category Management**: Admins can manage product categories, including adding, editing, and deleting categories.
3. **Product Selection by Category**: Users (or Admins) can filter products based on categories.
4. **Admin Login and Registration**: Admins can log in to the system and register new admin users.
5. **Image Management**: Admins can upload, delete, or modify product images.
6. **CRUD Operations on Products**: Admins can add, edit, and delete products from the catalog.

---

## Technology Stack
- **Backend**: Java with **Spring Boot** Framework
- **Database**: Relational Database (e.g., MySQL, PostgreSQL) for storing products, categories, and image metadata.
- **Security**: Spring Security for managing user authentication and authorization.
- **Frontend (Optional)**: A separate frontend application (e.g., React or Angular) can be used to interact with the backend.

---

## System Architecture
The application is structured in the following layers:
1. **Controller Layer**: Handles incoming HTTP requests and interacts with the service layer.
2. **Service Layer**: Contains the business logic and interacts with the repository layer.
3. **Repository Layer**: Manages CRUD operations and interacts with the database via Spring Data JPA.
4. **Security Layer**: Manages authentication and authorization of users (admin in this case).
5. **Model Layer**: Contains entity classes for products, categories, and users.

---

## Endpoints & Functionality

### 1. **Admin Registration**
- **POST** `/api/admin/register`
  - **Description**: Allows the registration of a new admin user.
  - **Request Body**:
    ```json
    {
      "username": "adminUser",
      "password": "adminPass",
      "email": "admin@example.com"
    }
    ```
  - **Response**:
    - `201 Created` - Admin successfully registered.
    - `400 Bad Request` - Validation errors (e.g., missing fields).

---

### 2. **Admin Login**
- **POST** `/api/admin/login`
  - **Description**: Authenticates an admin user and returns a JWT token for further requests.
  - **Request Body**:
    ```json
    {
      "username": "adminUser",
      "password": "adminPass"
    }
    ```
  - **Response**:
    - `200 OK` - JWT token returned on successful authentication.
    - `401 Unauthorized` - Invalid credentials.

---

### 3. **Product Upload**
- **POST** `/api/products/upload`
  - **Description**: Uploads a new product to the catalog.
  - **Request Body**:
    ```json
    {
      "title": "Product Name",
      "description": "Product Description",
      "price": 99.99,
      "categoryId": 1,
      "imageUrls": ["url1", "url2"]
    }
    ```
  - **Response**:
    - `201 Created` - Product successfully uploaded.
    - `400 Bad Request` - Validation errors (e.g., missing fields).

---

### 4. **Category Upload**
- **POST** `/api/categories/upload`
  - **Description**: Adds a new category for products.
  - **Request Body**:
    ```json
    {
      "name": "Electronics"
    }
    ```
  - **Response**:
    - `201 Created` - Category successfully added.
    - `400 Bad Request` - Validation errors.

---

### 5. **Select Products by Category**
- **GET** `/api/products/category/{categoryId}`
  - **Description**: Retrieves products filtered by a category.
  - **Response**:
    - `200 OK` - List of products belonging to the specified category.
    - `404 Not Found` - Category not found.

---

### 6. **Add Product Image**
- **POST** `/api/products/{productId}/images`
  - **Description**: Allows adding an image to a specific product.
  - **Request Body**: (Multipart image file)
    - Form-data: `image: <image file>`
  - **Response**:
    - `200 OK` - Image uploaded successfully.
    - `400 Bad Request` - Invalid image format.

---

### 7. **Delete Product Image**
- **DELETE** `/api/products/{productId}/images/{imageId}`
  - **Description**: Deletes a specific image associated with a product.
  - **Response**:
    - `200 OK` - Image deleted successfully.
    - `404 Not Found` - Image or product not found.

---

### 8. **Add New Category**
- **POST** `/api/categories`
  - **Description**: Adds a new product category.
  - **Request Body**:
    ```json
    {
      "name": "Books"
    }
    ```
  - **Response**:
    - `201 Created` - Category successfully added.
    - `400 Bad Request` - Validation errors.

---

### 9. **Delete Category**
- **DELETE** `/api/categories/{categoryId}`
  - **Description**: Deletes an existing category.
  - **Response**:
    - `200 OK` - Category deleted successfully.
    - `404 Not Found` - Category not found.

---

### 10. **Add New Product**
- **POST** `/api/products`
  - **Description**: Adds a new product to the catalog.
  - **Request Body**:
    ```json
    {
      "title": "New Product",
      "description": "Description of new product",
      "price": 49.99,
      "categoryId": 2,
      "imageUrls": ["image1_url", "image2_url"]
    }
    ```
  - **Response**:
    - `201 Created` - Product successfully added.
    - `400 Bad Request` - Validation errors.

---

### 11. **Delete Product**
- **DELETE** `/api/products/{productId}`
  - **Description**: Deletes a product from the catalog.
  - **Response**:
    - `200 OK` - Product deleted successfully.
    - `404 Not Found` - Product not found.

---

## Data Models

### 1. **Product Entity**
```java
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ElementCollection
    private List<String> imageUrls;
    
    // Getters and Setters
}
