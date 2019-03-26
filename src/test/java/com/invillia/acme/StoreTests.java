package com.invillia.acme;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StoreTests {
	
	private final StoreController storeController = new StoreController();
	
	@Test
	public void testCreateStoreNull() {
		
		assertEquals(null, storeController.create(null, ""));
	}
	
}
