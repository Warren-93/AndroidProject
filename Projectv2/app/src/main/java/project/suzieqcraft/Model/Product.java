package project.suzieqcraft.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Product extends ProductArrayList {

    int     productId;
    String  productType;
    String  productImage;
//    String  productPrice;

    public Product() {
    }

    public Product(int productId, String productType, String productImage) {
        this.productId = productId;
        this.productType = productType;
        this.productImage = productImage;
    }


        @JsonProperty("Product_ID")
        public void setProductId(int productId) {
            this.productId = productId;
        }

        @JsonProperty("Product_ID")
        public int getProductId() {
            return productId;
        }

        @JsonProperty("Product_Type")
        public void setProductType(String productType) {
            this.productType = productType;
        }

        @JsonProperty("Product_Type")
        public String getProductType() {
            return productType;
        }

        @JsonProperty("Product_Image")
        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        @JsonProperty("Product_Image")
        public String getProductImage() {
            return productImage;
        }


}




