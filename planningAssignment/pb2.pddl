(define (problem pb2)
  (:domain aperture)
  (:requirements :strips :typing)
  (:objects
    atlas - robot
    h1 h2 h3 h4 - hallway
    r1 r2 r3 - room
    c1 c2 c3 - cube)

  (:init (at atlas h4)
         (unloaded atlas)
         (in c1 h1)
         (in c2 r1)
         (in c3 r2)
         
         (connected h1 h2) (connected h2 h1)
         (connected h2 h3) (connected h3 h2)
         (connected h3 h4) (connected h4 h3)

         (connected r1 h2) (connected h2 r1)
         (connected r2 h2) (connected h2 r2)
         (connected r3 h4) (connected h4 r3)
  )

  (:goal
    (and (at atlas h4)
         (in c1 h4)
         (in c2 h4)
         (in c3 h4))
  )
)
