(define (domain logistics)
  (:requirements :strips)
  
  (:predicates  (at ?c ?a)
                (cargo ?c)
                (airplane ?p)
                (airport ?a))

  (:action load
      :parameters (?c ?p ?a)
      :precondition (and (at ?c ?a) (at ?p ?a) (cargo ?c) (airplane ?p) (airport ?a))
      :effect (and (not (at ?c ?a)) (at ?c ?p)))

  (:action unload
      :parameters (?c ?p ?a)
      :precondition (and (at ?c ?p) (at ?p ?a) (cargo ?c) (airplane ?p) (airport ?a))
      :effect (and (at ?c ?a) (not (at ?c ?p))))

  (:action fly
      :parameters (?p ?from ?to)
      :precondition (and (at ?p ?from) (airplane ?p) (airport ?from) (airport ?to))
      :effect (and (not (at ?p ?from)) (at ?p ?to)))
)
