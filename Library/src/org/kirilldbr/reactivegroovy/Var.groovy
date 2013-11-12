package org.kirilldbr.reactivegroovy

import java.util.List;

class Var {

	private value	
	private List dependencies
	
	Var( ) { this( null ) }
	
	Var( def value ) {
		this.value = value
		this.dependencies = []
	}
	
	def getVal( ) { this.value }
	def setVal( def value ) {
		def oldValue = this.value
		this.value = value
		onChange( )
	}
	
	static Var var( def value ) { new Var( value ) }
	
	Binding bind ( Closure closure, Var... vars ) {
		return new Binding ( this, closure, vars )
	}
	
	def onChange ( ) {
		dependencies.each { it.onChange ( ) }
	}
	
}
