public class StockItem extends Product {
    private int quantity;

    public StockItem(int id, String name, double price, int quantity) {
        super(id, name, price);
        this.quantity = quantity;
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getStockValue() {
        return getPrice() * quantity;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Qty: %d | Stock Value: %.2f", quantity, getStockValue());
    }
}