package com.nl.practices.inventory;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;
import java.util.List;

public class InventoryServiceTest {

    private InventoryService inventoryService = new InventoryService();

    @Test
    void listAllProducts(){
        Product p = new Product("prid-1","product-one", new BigDecimal(1000), 1);
        inventoryService.addProduct(p);
        List<Product> products = inventoryService.listProductsByPrice();
        assertThat(products)
                .hasSize(1)
                        .contains(p);
    }
}
