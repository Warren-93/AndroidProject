package project.suzieqcraft.Model;

import java.util.ArrayList;

public class ImageArrayList extends ArrayList<Image> {

    public ArrayList imageList;

    public ImageArrayList() {
        super();
    }

    public ArrayList getImageList() {
        return imageList;
    }

    public ArrayList setImageList(ArrayList imageList) {
        this.imageList = imageList;
        return this;
    }
}
