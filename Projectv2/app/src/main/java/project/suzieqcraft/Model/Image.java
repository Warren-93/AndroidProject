package project.suzieqcraft.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Image extends ImageArrayList {

    int galleryId;
    String productType;
    String galleryImage;

    public Image() {
    }

    public Image(int galleryId, String galleryImage, String productType) {
        this.galleryId = galleryId;
        this.productType = productType;
        this.galleryImage = galleryImage;
    }

    @JsonProperty("Gallery_ID")
    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    @JsonProperty("Gallery_ID")
    public int getGalleryId() {
        return galleryId ;
    }

    @JsonProperty("Product_Type")
    public void setProductType(String productType) {
        this.productType = productType;
    }

    @JsonProperty("Product_Type")
    public String getProductType() {
        return productType;
    }

    @JsonProperty("Gallery_Image")
    public void setProductImage(String galleryImage) {
        this.galleryImage = galleryImage;
    }

    @JsonProperty("Gallery_Image")
    public String getGalleryImage() {
        return galleryImage;
    }


}

