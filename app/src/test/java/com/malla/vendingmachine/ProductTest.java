package com.malla.vendingmachine;

import com.malla.vendingmachine.viewmodel.ProductViewModel;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ProductTest {
    @Test
    public void validProduct() throws Exception {
        ProductViewModel p = new ProductViewModel("SampleProduct", "5",2, "" );
        assertNotNull("Product should not have been null", p);
        assertEquals("SampleProduct", p.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidProductName() throws Exception {
        new ProductViewModel("", "5", 2, "");
    }

}