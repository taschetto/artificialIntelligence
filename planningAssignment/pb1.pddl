(define (problem pb1)
  (:domain aperture)
  (:requirements :strips :disjunctive-preconditions :typing)
  (:objects
    atlas - robot
    h1 h2 h3 - hallway
    r - room
    c - cube)

  (:init (at atlas h1)
         (in c r)
         (connected h1 h2)
         (connected h2 h3)
         (connected h2 r))

  (:goal
    (and (in c h3)
         (at atlas h3)))
)
