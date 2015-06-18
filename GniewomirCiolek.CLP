(defrule checkItem
	(not (previous-choice (index ?ind) (choice Search|PickUp)))
	(can Search)
	(item ?theItem)
	(test (< ?theItem 10))
	=>
	(assert (decided))
	(bind ?*decision* Search)
)

(defrule pickItem
	(not (decided))
	(not (previous-choice (index ?ind) (choice PickUp)))
	(previous-choice (index ?ind) (choice Search))
	(not (top-level))
	(can PickUp)
	(item ?theItem)
	(chamber (index ?ind) (item-level ?chamberItem))
	(test (> ?chamberItem ?theItem))
	=>
	(assert (decided))
	(bind ?*decision* PickUp)
)

(defrule moveRight
	(not (decided))
	(or 
		(and 
			(not (can-move MoveLeft))
			(not (can-move MoveUp))
			(not (can-move MoveDown))
		)
		(and 
			(not (previous-choice (index ?ind) (choice MoveRight)))
			(can-move MoveRight)
		)
		
	)
	=>
	(assert (decided))
	(bind ?*decision* MoveRight)
)
(defrule moveLeft
	(not (decided))
	(or 
		(and 
			(not (can-move MoveRight))
			(not (can-move MoveUp))
			(not (can-move MoveDown))
		)
		(and 
			(not (previous-choice (index ?ind) (choice MoveLeft)))
			(can-move MoveLeft)
		)
		
	)
	=>
	(assert (decided))
	(bind ?*decision* MoveLeft)
)
(defrule moveDown
	(not (decided))
	(or 
		(and 
			(not (can-move MoveLeft))
			(not (can-move MoveUp))
			(not (can-move MoveRight))
		)
		(and 
			(not (previous-choice (index ?ind) (choice MoveDown)))
			(can-move MoveDown)
		)
		
	)
	=>
	(assert (decided))
	(bind ?*decision* MoveDown)
)
(defrule moveUp
	(not (decided))
	(or 
		(and 
			(not (can-move MoveLeft))
			(not (can-move MoveRight))
			(not (can-move MoveDown))
		)
		(and 
			(not (previous-choice (index ?ind) (choice MoveUp)))
			(can-move MoveUp)
		)
		
	)
	=>
	(assert (decided))
	(bind ?*decision* MoveUp)
)
(defrule topItem
	(item ?theItem)
	(test (eq ?theItem 10))
	=>
	(assert (top-level))
)