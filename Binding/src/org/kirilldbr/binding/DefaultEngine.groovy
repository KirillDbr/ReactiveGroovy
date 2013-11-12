package org.kirilldbr.binding

import org.graphstream.graph.Graph
import org.graphstream.graph.implementations.SingleGraph

class DefaultEngine implements Engine {

	List<Binding> bindings
	
	{
		bindings = []
	}
	
	@Override
	def onChange ( Binding binding ) {
		binding.update ( )
	}

	@Override
	def add ( Binding binding ) {
		bindings << binding
		binding.source
	}
	
	def displayGraph ( ) {
		Graph graph = new SingleGraph("graph")
		graph.setAutoCreate( true )
		bindings.each { Binding binding ->
			binding.destination
			binding.invokers.each { invoker ->
				if ( graph.getNode(invoker.id.toString()) == null ) {
					graph.addNode(invoker.id.toString())
				}
				if ( graph.getNode(binding.destination.id.toString()) == null ) {
					graph.addNode(binding.destination.id.toString())
				}
				graph.addEdge("$invoker${binding.destination}", invoker.id.toString(), binding.destination.id.toString()) 
			}
		}
		graph.display()
	}

}
