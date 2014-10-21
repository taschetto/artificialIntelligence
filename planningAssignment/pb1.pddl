(define (problem pb1)
  (:domain aperture)
  (:requirements :strips :typing)
  (:objects
    atlas - robot
    h1 h2 h3 - hallway
    r - room
    c - cube)

  (:init (at atlas h1)
         (unloaded atlas)
         (in c r)
         (connected h1 h2) (connected h2 h1)
         (connected h2 h3) (connected h3 h2)
         (connected h2 r)  (connected r  h2))

  (:goal
    (and (at atlas h3)
         (in c h3)))
)