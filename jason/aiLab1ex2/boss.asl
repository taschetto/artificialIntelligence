// Agent boss in project bossW.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!boss.

/* Plans */

+!boss : true
	<-	.my_name(N);
		.broadcast(tell, boss(N));
		.puts("I am the boss").
	
+worker(W) : true
	<-	.puts("#{W} is a worker");
		.random(T);
		Ta=math.ceil(T*10);
		.send(W, tell, do(Ta)).

