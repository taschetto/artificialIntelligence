// Agent sample_agent in project tutorial

/* Initial beliefs and rules */

/* Initial goals */

carrot(2,5).
//pos(1,1).

/* Plans */

+pos(X,Y)
	<- .puts("I'm at position #{X} #{Y}").

//+carrot(X,Y) : pos(X-1,Y)
//	<- .puts("Going for carrot at ");
//	   move(left);
//	   grab(X,Y).
//
//+carrot(X,Y) : pos(X-1,Y-1)
//	<- .puts("Going for carrot at ");
//	   move(left);
//	   move(down);
//	   grab(X,Y).

+carrot(X,Y) : pos(X,Y)
	<- .puts("I'm over the carrot, grabing it");
	   grab(X,Y).

+carrot(X,Y) : not pos(X,Y)
	<- .puts("I'm not over the carrot, I need to plan to get there");
	   !move(X,Y);
	   grab(X,Y).
	   
+!move(X,Y) : pos(X,Y)
	<- .puts("Reached target location #{X},#{Y}").
	
	   
+!move(X,Y) : pos(_,Y1) & Y1 > Y
	<- move(down);
	   !move(X,Y).

+!move(X,Y) : pos(_,Y1) & Y1 < Y
	<- move(up);
	   !move(X,Y).

+!move(X,Y) : pos(X1,_) & X1 < X
	<- move(right);
	   !move(X,Y).

+!move(X,Y) : pos(X1,_) & X1 > X
	<- move(left);
	   !move(X,Y).
