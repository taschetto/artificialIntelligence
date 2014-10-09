(define (domain blocks)
  (:requirements :strips :equality)

  (:predicates  (clear ?x)
                (block ?x)
                (on ?x ?y)
                (table ?x))

  (:action move
    :parameters (?b ?x ?y)
    :precondition (and (on ?b ?x) (clear ?b) (clear ?y) (block ?b) (block ?y) (not (= ?b ?x)) (not (= ?b ?y)) (not (= ?x ?y)))
    :effect (and (on ?b ?y) (clear ?x) (not (on ?b ?x)) (not (clear ?y))))

  (:action moveToTable
    :parameters (?b ?x ?y)
    :precondition (and (on ?b ?x) (clear ?b) (block ?b) (table ?y) (not (= ?b ?x)))
    :effect (and (on ?b ?y) (clear ?x) (not (on ?b ?x)))
  )
)
