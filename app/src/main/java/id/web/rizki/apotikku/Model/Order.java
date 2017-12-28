package id.web.rizki.apotikku.Model;

/**
 * Created by rizki on 20/12/17.
 */

public class Order {
    private String ProductId;
    private String ProductName;
    private String Quantity;
    private String Price;
    private String Discon;

    public String getDiscon() {
        return Discon;
    }

    public void setDiscon(String discon) {
        Discon = discon;
    }

    public Order() {

    }

    public Order(String productId, String productName, String quantity, String price,String discon) {
        ProductId = productId;
        ProductName = productName;
        Quantity = quantity;
        Price = price;
        Discon = discon;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

}
