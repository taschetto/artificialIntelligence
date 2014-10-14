(define (domain aperture)
  (:requirements :strips :equality)

  (:predicates  (at ?x ?y)
                (connected ?x ?y)
                (in ?x ?y)
                (has ?x ?y)
                (isRobot ?x)
                (isCube ?x)
                (isHallway ?x)
                (isRoom ?x))

  (:action enter
    :parameters (?robot ?from ?to)
    :precondition (and (isRobot ?robot) (isHallway ?from) (isRoom ?to) (at ?robot ?from) (connected ?from ?to))
    :effect (and (not (at ?robot ?from)) (at ?robot ?to)))

  (:action exit
    :parameters (?robot ?from ?to)
    :precondition (and (isRobot ?robot) (isRoom ?from) (isHallway ?to) (at ?robot ?from) (connected ?from ?to))
    :effect (and (not (at ?robot ?from)) (at ?robot ?to)))

  (:action move
    :parameters (?robot ?from ?to)
    :precondition (and (isHallway ?from) (isHallway ?to) (at ?robot ?from) (connected ?from ?to))
    :effect (and (not (at ?robot ?from)) (at ?robot ?to)))

  (:action pickup 
    :parameters (?robot ?cube ?location)
    :precondition (and (isRobot ?robot) (isCube ?cube) (at ?robot ?location) (in ?cube ?location))
    :effect (and ))
)
