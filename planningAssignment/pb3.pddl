(define (problem pb3)
  (:domain aperture)
  (:requirements :strips :typing)
  (:objects
    atlas - robot
    h1 h2 h3 h4 h5 h6 h7 h8 h9 - hallway
    r1 r2 r3 r4 - room
    c1 c2 c3 - cube)

  (:init (at atlas h1)
         (unloaded atlas)
         (in c1 r2)
         (in c2 h4)
         (in c3 r4)
         (connected h1 h2) (connected h2 h1)
         (connected h1 h3) (connected h3 h1)
         (connected h2 h4) (connected h4 h2)
         (connected h3 h5) (connected h5 h3)
         (connected h4 h6) (connected h6 h4)
         (connected h5 h6) (connected h6 h5)
         (connected h6 h7) (connected h7 h6)
         (connected h6 h8) (connected h8 h6)
         (connected h8 h9) (connected h9 h8)
         (connected r1 h1) (connected h1 r1)
         (connected r1 h2) (connected h2 r1)
         (connected r2 h4) (connected h4 r2)
         (connected r2 h5) (connected h5 r2)
         (connected r3 h7) (connected h7 r3)
         (connected r4 h8) (connected h8 r4))

  (:goal
    (and (at atlas h9)
         (in c1 h9)
         (in c2 h9)
         (in c3 h9)))
)