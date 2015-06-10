(defrule init
	(initial-fact)
	=>
	(assert (IWas ?*pos-x* ?*pos-y* 0))
)

(defrule goingUp
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice MoveDown))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas ?x (+ ?y 1) ?ind))
)

(defrule goingDown
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice MoveUp))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas ?x (- ?y 1) ?ind))
)

(defrule goingLeft
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice MoveRight))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas (- ?x 1) ?y ?ind))
)

(defrule goingRight
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice MoveLeft))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas (+ ?x 1) ?y ?ind))
)

(defrule staying
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice Search|PickUp))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas ?x ?y ?ind))
)

(defrule search
	(can Search)
	(item ?itemval)
	(not (decision-was-made))
	(test (< ?itemval 5))
	=>
	(assert (decision-was-made))
	(bind ?*decision* Search)
)

(defrule pick-up
	(not (decision-was-made))
	(can PickUpItem)
	(item ?itemval)
	=>
	(assert (decision-was-made))
	(bind ?*decision PickUpItem)
)

(defrule test1
	(IWas ?x ?y ~0)
	(test (eq ?x ?*pos-x*))
	(test (eq ?y (+ ?*pos-y* 1)))
	=>
	(assert (IWas MoveDown))
)

(defrule test2
	(IWas ?x ?y ~0)
	(test (eq ?x ?*pos-x*))
	(test (eq ?y (- ?*pos-y* 1)))
	=>
	(assert (IWas MoveUp))
)

(defrule test3
	(IWas ?x ?y ~0)
	(test (eq ?x (+ ?*pos-x* 1)))
	(test (eq ?y ?*pos-y*))
	=>
	(assert (IWas MoveLeft))
)

(defrule test4
	(IWas ?x ?y ~0)
	(test (eq ?x (- ?*pos-x* 1)))
	(test (eq ?y ?*pos-y*))
	=>
	(assert (IWas MoveRight))
)

(defrule move
	(not (decision-was-made))
	(can-move ?dir)
	(not (IWas ?dir))
	=>
	(assert (decision-was-made))
	(bind ?*decision* ?dir)
)
