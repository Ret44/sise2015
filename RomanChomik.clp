(defrule init
	(declare (salience 10000))
	(initial-fact)
	=>
	(assert (IWas ?*pos-x* ?*pos-y* 0))
)

(defrule goingUp
	(declare (salience 1000))
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice MoveDown))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas ?x (+ ?y 1) ?ind))
)

(defrule goingDown
	(declare (salience 1000))
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice MoveUp))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas ?x (- ?y 1) ?ind))
)

(defrule goingLeft
	(declare (salience 1000))
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice MoveRight))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas (- ?x 1) ?y ?ind))
)

(defrule goingRight
	(declare (salience 1000))
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice MoveLeft))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas (+ ?x 1) ?y ?ind))
)

(defrule staying
	(declare (salience 1000))
	(IWas ?x ?y ?ago)
	(previous-choice (index ?ind) (choice Search|PickUp))
	(test (eq ?ago (- ?ind 1)))
	=>
	(assert (IWas ?x ?y ?ind))
)

(defrule search
	(declare (salience 101))
	(can Search)
	(not (decision-was-made ?ANY))
	(item ?myItem)
	(test (< ?myItem 10))
	=>
	(assert (decision-was-made Search))
	(bind ?*decision* Search)
)

(defrule pick-up
	(declare (salience 100))
	(not (decision-was-made ?ANY))
	(can PickUpItem)
	(item ?myItem)
	(chamber (index ?ind) (item-level ?chamberItem))
	(test (> ?chamberItem ?myItem))
	(test (eq ?ind 0))
	=>
	(assert (decision-was-made PickUpItem))
	(bind ?*decision* PickUpItem)
)

(defrule test1
	(declare (salience 90))
	(IWas ?x ?y ~0)
	(test (eq ?x ?*pos-x*))
	(test (eq ?y (+ ?*pos-y* 1)))
	=>
	(assert (IWas MoveDown))
)

(defrule test2
	(declare (salience 90))
	(IWas ?x ?y ~0)
	(test (eq ?x ?*pos-x*))
	(test (eq ?y (- ?*pos-y* 1)))
	=>
	(assert (IWas MoveUp))
)

(defrule test3
	(declare (salience 90))
	(IWas ?x ?y ~0)
	(test (eq ?x (+ ?*pos-x* 1)))
	(test (eq ?y ?*pos-y*))
	=>
	(assert (IWas MoveRight))
)

(defrule test4
	(declare (salience 90))
	(IWas ?x ?y ~0)
	(test (eq ?x (- ?*pos-x* 1)))
	(test (eq ?y ?*pos-y*))
	=>
	(assert (IWas MoveLeft))
)


(defrule move
	(declare (salience 10))
	(initial-fact)
	(not (decision-was-made ?))
	(can-move ?dir1)
	(or 
		(and 
			(IWas ?dir2)
			(not (test (eq ?dir1 ?dir2)))
		)
		(not (IWas ?))
	)
	=>
	(assert (decision-was-made ?dir1))
	(bind ?*decision* ?dir1)
)
