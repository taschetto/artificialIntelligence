(define (domain aperture)
  (:requirements :strips :typing :negative-preconditions)

  (:types robot location cube - object
          hallway room - location)

  (:predicates  (at ?r - robot ?loc - location)
                (connected ?loc1 ?loc2 - location)
                (in ?c - cube ?loc - location)
                (has ?r - robot ?c - cube)
                (unloaded ?r - robot))

  (:action enter
    :parameters (?r - robot ?from - hallway ?to - room)
    :precondition
      (and (at ?r ?from)
           (connected ?from ?to))
    :effect
      (and (not (at ?r ?from))
           (at ?r ?to)))

  (:action exit
    :parameters (?r - robot ?from - room ?to - hallway)
    :precondition
      (and (at ?r ?from)
           (connected ?from ?to))
    :effect
      (and (not (at ?r ?from))
           (at ?r ?to)))

  (:action move
    :parameters (?r - robot ?from ?to - hallway)
    :precondition
      (and (at ?r ?from)
           (connected ?from ?to))
    :effect
      (and (not (at ?r ?from))
           (at ?r ?to)))

  (:action pickup 
    :parameters (?r - robot ?c - cube ?loc - location)
    :precondition
      (and (at ?r ?loc)
           (in ?c ?loc)
           (not (has ?r ?c))
           (unloaded ?r))
    :effect
      (and (has ?r ?c)
           (not (in ?c ?loc))
           (not (unloaded ?r))))

  (:action drop
    :parameters (?r - robot ?c - cube ?loc - location)
    :precondition
      (and (at ?r ?loc)
           (has ?r ?c)
           (not (unloaded ?r)))
    :effect
      (and (not (has ?r ?c))
           (in ?c ?loc)
           (unloaded ?r)))
)
