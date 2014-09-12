// Agent worker in project bossW.mas2j

/* Initial beliefs and rules */

/* Initial goals */

//!start.

/* Plans */

//+!start : true <- .print("hello world.").

+boss(B) : true
	<- 	.puts("#{B} is the boss");
		.my_name(W);
		.send(B, tell, worker(W)).

+do(T) : not done(T)
	<-	do(T);
		+done(T).
		
+done(T) : true
<- .puts ("Task #{T} done").
