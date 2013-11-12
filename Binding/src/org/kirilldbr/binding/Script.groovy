package org.kirilldbr.binding

import java.rmi.server.*


def var = { Var.var ( it ) }

a = var 1
b = var 4
c = var 3
c.bind { a.register( it ) + b.register( it ) }
d = var 3
d.bind { a.register( it ) * c.register( it ) }

println a
println a.id
println b
println c
b.val = 6
println c
a.val = 2
println c

ApplicationEngine.engine.displayGraph ( )