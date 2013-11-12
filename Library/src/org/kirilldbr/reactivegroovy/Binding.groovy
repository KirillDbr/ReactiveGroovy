package org.kirilldbr.reactivegroovy

import java.util.List;

import groovy.lang.Closure;

class Binding {
	private Var destination
	private Closure formula
	private List<Var> invokers
	
	Binding( Var destination, Closure formula, Var... vars ) {
		this.destination = destination
		this.formula = formula
		this.invokers = vars as List
		this.invokers.each{ it.dependencies << this }
		this.onChange( )
	}	
	
	def onChange( ) {
		this.destination.value = this.formula.call ( this.invokers )
	}
	
}
