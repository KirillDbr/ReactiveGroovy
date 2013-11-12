package org.kirilldbr.binding

class ApplicationEngine implements Engine {

	private static volatile Engine engine

	static Engine getEngine () {
		if (engine) {
			engine
		} else {
			synchronized(ApplicationEngine) {
				if (engine) {
					engine
				} else {
					engine = new DefaultEngine ()
				}
			}
		}
	}

	@Override
	def onChange ( Binding binding ) {
		engine.onChange ( binding )
	}

	@Override
	def add(Binding binding) {
		engine.add ( binding )
	}
	
}
