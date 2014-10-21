(define (problem pb6)
  (:domain aperture)
  (:requirements :strips :typing)
  (:objects
    atlas - robot
    h1 h2 h3 h4 h5 h6 - hallway
    r1 r2 r3 r4 - room
    c1 c2 c3 - cube)

  (:init (at atlas r1)
         (unloaded atlas)
         (in c1 r2)
         (in c2 r3)
         (in c3 r4)

         (connected h1 r1) (connected r1 h1)
         (connected h1 r2) (connected r2 h1)
         (connected h2 r1) (connected r1 h2)
         (connected h2 r2) (connected r2 h2)
         (connected h1 h3) (connected h3 h1)
         (connected h2 h4) (connected h4 h2)
         (connected h3 r3) (connected r3 h3)
         (connected h4 r3) (connected r3 h4)
         (connected h3 h5) (connected h5 h3)
         (connected h4 h6) (connected h6 h4)
         (connected h5 r4) (connected r4 h5)
         (connected h6 r4) (connected r4 h6)
  )

  (:goal
    (and (at atlas r1)
         (in c1 r1)
         (in c2 r1)
         (in c3 r1))
  )
)
