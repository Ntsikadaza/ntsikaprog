//package inventory.management.system;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InventoryAppTest {

    private StockItem[] inventory;

    @Before
    public void setUp() {
        inventory = new StockItem[5];
        inventory[0] = new StockItem(101, "Laptop", 12000, 5);
        inventory[1] = new StockItem(102, "Mouse", 150, 50);
        inventory[2] = new StockItem(103, "Keyboard", 350, 20);
        inventory[3] = new StockItem(104, "Monitor", 2000, 10);
        inventory[4] = new StockItem(105, "Printer", 2500, 8);
    }

    @Test
    public void testStockValueCalculation() {
        assertEquals(12000 * 5, inventory[0].getStockValue(), 0.001);
        assertEquals(150 * 50, inventory[1].getStockValue(), 0.001);
        assertEquals(350 * 20, inventory[2].getStockValue(), 0.001);
        assertEquals(2000 * 10, inventory[3].getStockValue(), 0.001);
        assertEquals(2500 * 8, inventory[4].getStockValue(), 0.001);
    }

    @Test
    public void testUpdatePriceAndQuantity() {
        inventory[1].setPrice(200);
        inventory[1].setQuantity(40);
        assertEquals(200 * 40, inventory[1].getStockValue(), 0.001);
    }

    @Test
    public void testInventoryTotalValue() {
        double total = 0;
        for (StockItem item : inventory) {
            total += item.getStockValue();
        }
        double expectedTotal = (12000*5) + (150*50) + (350*20) + (2000*10) + (2500*8);
        assertEquals(expectedTotal, total, 0.001);
    }

    @Test
    public void testStockItemFields() {
        assertEquals(101, inventory[0].getId());
        assertEquals("Laptop", inventory[0].getName());
        assertEquals(12000, inventory[0].getPrice(), 0.001);
        assertEquals(5, inventory[0].getQuantity());
    }
}