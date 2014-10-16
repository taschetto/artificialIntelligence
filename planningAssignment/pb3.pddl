(define (problem pb2)
  (:domain aperture)
  (:requirements :strips :disjunctive-preconditions :typing)
  (:objects
    atlas - robot
    h1 h2 h3 h4 h5 h6 h7 h8 h9- hallway
    r1 r2 r3 r4 room
    c1 c2 c3 - cube)

  (:init (at atlas h1)
         (in c1 r2)
         (in c2 h4)
         (in c3 r4)
         (connected h1 h2)
         (connected h1 h3)
         (connected h1 r1)
         (connected h2 h4)
         (connected h3 h5)
         (connected h4 h6)
         (connected h5 h6)
         (connected h6 h7)
         (connected h6 h8)
         (connected h7 r3)
         (connected h8 h9)
         (connected h8 r4))

  (:goal
    (and (in c1 h9)
         (in c2 h9)
         (in c3 h9)
         (at r h9)))
)
