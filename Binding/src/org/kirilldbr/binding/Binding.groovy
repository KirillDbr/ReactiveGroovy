package org.kirilldbr.binding

import groovy.lang.Closure;


//http://stackoverflow.com/questions/10701421/tracking-method-invocations-in-groovy-closure


class Binding {
	private Var destination
	private List invokers
	private def source
	private Engine engine 
	Binding ( Var destination, Closure source ) {
		this ( ApplicationEngine.engine, destination, source )
	}
	
	Binding ( Engine engine, Var destination, Closure source ) {
		this.destination = destination
		this.source = source
		this.invokers = []
		this.source.call ( this )
		this.engine = engine
		engine.add ( this )
	}
	
	def update ( ) {
		this.destination.value = this.source.call ( null )
	} 
	
	def onChange ( def source ) {
		engine.onChange( this )
	}
}
