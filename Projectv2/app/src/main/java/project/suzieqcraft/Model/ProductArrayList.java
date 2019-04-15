package project.suzieqcraft.Model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ProductArrayList extends ArrayList<Product> {

    public ArrayList productList;

    public ProductArrayList(){
        super();
    }


    public ArrayList getProductList() {
        return productList;
    }

    public ArrayList setProductList(ArrayList productList) {
        this.productList = productList;
        return this;
    }
}

