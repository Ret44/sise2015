
(defrule item-check
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
	(not (decision-was-made))
	(can PickUpItem)
	(item ?itemval)
	=>
	(assert (decision-was-made))
	(bind ?*decision* PickUpItem)
)

(defrule can-move-left
	(not (decision-was-made))
	(not (previous-move MoveLeft))
	(can-move MoveLeft)
	=>
	(assert (decision-was-made))
	(bind ?*decision* MoveLeft)
)

(defrule can-move-down
	(not (previous-move MoveDown))
	(not (decision-was-made))
	(not (can-move MoveLeft))
	(can-move MoveDown)
	=>
	(assert (decision-was-made))
	(bind ?*decision* MoveDown)
)

(defrule can-move-right
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
