package org.kirilldbr.binding

import java.rmi.server.ObjID

class Var {
	private long id;
	private value
	private List<Binding> dependencies
	
	{
		id = new ObjID().objNum
	}
	
	Var ( ) {
		this ( null )
	}
	
	Var ( def value ) {
		this.value = value
		this.dependencies = []
	}
	
	def getVal ( ) { this.value }
	def setVal ( def value ) { 
		def oldValue = this.value 
		this.value = value
		onChange ( oldValue, this.value )  
	}
	
	def getId ( ) { id }
 	
	static Var var ( def value ) {
		return new Var ( value )
	}
	
	Binding bind ( def closure ) {
		return new Binding ( this, closure )
	}
	
	def register ( Binding binding ) {
		if ( binding != null ) {
			this.dependencies << binding
			binding.invokers << this
		}
		return value
	}
	
	def onChange ( def oldValue, def newValue ) {
		dependencies.each { it.onChange ( this ) }
	} 
	
	def propertyMissing(String name, value) { this.value[name] = value }
	def propertyMissing(String name) { this.value[name] }
	//def methodMissing(String name, args) { this.value.invokeMethod(name, args) }
	
	@Override
	String toString() {
		return value.toString();
	}
}
