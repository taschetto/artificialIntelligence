(define (problem pb4)
  (:domain aperture)
  (:requirements :strips :typing)
  (:objects
    atlas - robot
    h1 h2 - hallway
    r1 r2 - room
    c  - cube)

  (:init (at atlas r1)
         (unloaded atlas)
         (in c r2)

         (connected h1 r1) (connected r1 h1)
         (connected h1 r2) (connected r2 h1)
         (connected h2 r1) (connected r1 h2)
         (connected h2 r2) (connected r2 h2)
  )

  (:goal
    (and (at atlas r1)
         (in c r1))
  )
)
