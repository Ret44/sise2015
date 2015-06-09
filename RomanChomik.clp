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
