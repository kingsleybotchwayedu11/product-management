package product.mangagement.productm.models.products;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Category {
   @Id
   @GeneratedValue
   private Integer id;

   @Column(nullable = false)
   private String name;

   public void setId(int id) {
       this.id = id;
   }

   public void setName(String name) {
       this.name = name;
   }
   public int getId() {
       return id;
   }
   public String getName() {
       return name;
   }
}
