package google.projectbuilding.fishmarket_v1.models;

public class FishModel {

    private String image;
    private String name;
    private String price;
    private String fisherman;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public FishModel(String image, String name, String price, String fisherman) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.fisherman = fisherman;
    }

    public FishModel(String name, String image) {
        this.image = image;
        this.name = name;
//        this.price = price;
//        this.fisherman = fisherman;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFisherman() {
        return fisherman;
    }

    public void setFisherman(String fisherman) {
        this.fisherman = fisherman;
    }
}
