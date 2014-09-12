// Agent greeter in project hello.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start(xyz).

/* Plans */

+!start(X) : true <-
	blah(X);
	.print("hello world.").

