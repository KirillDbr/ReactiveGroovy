package org.kirilldbr.reactivegroovy.tests

import static org.junit.Assert.*

import org.junit.Test
import org.kirilldbr.reactivegroovy.*

public class BindingTestCase {
	
	@Test
	public void testDoubleBinding() {
		def var = { Var.var ( it ) }
		
		def a = var 1
		def b = var 4
		def c = var null
		
		def binding = c.bind ( { x, y -> x.val + y.val }, a, b )
		assertEquals 5, c.val
		
		a.val = 2
		assertEquals 6, c.val
		
		b.val = -1
		assertEquals 1, c.val
	}

}
