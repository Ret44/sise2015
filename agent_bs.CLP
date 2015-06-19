
(defrule previous-movement
	(intial-fact)
	(not (previous-move ?val))
	=>
	(assert (previous-move (previous-movement-func 0)))
)

(defrule item-check
	(declare (salience 5))
	(can Search)
	(item ?itemval)
	(not (room-searched))
	(not (item-check-done))
	(test (< ?itemval 5))
	=>
	(assert (item-check-done))
	(assert (decision-was-made))
	(bind ?*decision* Search)
)

(defrule pick-up
	(declare (salience 6))
	(not (decision-was-made))
	(can PickUpItem)
	(item ?itemval)
	=>
	(assert (decision-was-made))
	(bind ?*decision PickUpItem)
)

(defrule can-move-left
	(declare (salience 10))
	(not (decision-was-made))
	(not (previous-move MoveLeft))
	(can-move MoveLeft)
	=>
	(assert (decision-was-made))
	(bind ?*decision* MoveLeft)
)

(defrule can-move-down
	(declare (salience 20))
	(not (previous-move MoveDown))
	(not (decision-was-made))
	(not (can-move MoveLeft))
	(can-move MoveDown)
	=>
	(assert (decision-was-made))
	(bind ?*decision* MoveDown)
)

(defrule can-move-right
	(declare (salience 30))
	(not (previous-move MoveRight))
	(not (decision-was-made))
	(not (can-move MoveLeft))
	(not (can-move MoveDown))
	(can-move MoveRight)
	=>
	(assert (decision-was-made))
	(bind ?*decision* MoveRight)
)

(defrule can-move-up
	(declare (salience 40))
	(not (previous-move MoveUp))
	(not (decision-was-made))
	(not (can-move MoveLeft))
	(not (can-move MoveDown))
	(not (can-move MoveRight))
	(can-move MoveUp)
	=>
	(assert (decision-was-made))
	(bind ?*decision* MoveUp)
)
