(defrule init
	(initial-fact)
	=>
	(assert (IWas ?*pos-x* ?*pos-y* 0))
)

(defrule wasIUp
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice MoveDown))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas ?x (+ ?y 1) ?ind))
)

(defrule wasIDown
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice MoveUp))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas ?x (- ?y 1) ?ind))
)

(defrule wasILeft
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice MoveRight))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas (- ?x 1) ?y ?ind))
)

(defrule wasIRight
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice MoveLeft))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas (+ ?x 1) ?y ?ind))
)

(defrule wasIHere
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

(defrule move
	(not (decision-was-made))
	(can-move ?dir)
	=>
	(assert (decision-was-made))
	(bind ?*decision* ?dir)
)
