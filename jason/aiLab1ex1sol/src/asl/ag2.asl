// Agent ag2 in project Assignment1.mas2j

/* Initial beliefs and rules */

position(init).

/* Initial goals */

/* Plans */

+position(init) : true
   <- !announce(position);
      move(doctor);
      move(work);
      move(gym).

+position(P) : true
     <- !announce(position).

+hear(H) : true
     <- .print("I just heard: ",H).

+!announce(position) : position(P)
    <- shout("I am at position ",P).