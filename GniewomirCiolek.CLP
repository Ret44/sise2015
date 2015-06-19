(defrule TooManyWays
	(can-move MoveRight)
	(can-move MoveLeft)
	(can-move MoveUp)
	(can-move MoveDown)
	=>
	(assert (tooManyWays))
)
(defrule OnlyTwoMoves
	(can-move ?lenght)
	(test(eq  ?lenght 2))
	=>
	(assert (only2Moves))
)
(defrule pickItem
	
	(not (previous-choice (index 0) (choice PickUp)))
	(can PickUpItem)
	(item ?theItem)
	(chamber (index ?ind) (item-level ?chamberItem))
	(test(> ?chamberItem ?theItem ))
	=>
	(assert (decided))
	(bind ?*decision* PickUpItem)
)
(defrule checkItem
	(not (decided))
	(not (previous-choice (index 0) (choice Search|PickUp)))
	(can Search)
	(not (room-searched))
	(not (item-check-done))
	(item ?theItem)
	(test (< ?theItem 10))
	=>
	(assert (decided))
	(bind ?*decision* Search)
)
(defrule LastMoveLeft
	
	(or 
		(previous-choice (index ?ind) (choice MoveLeft))
	)
	(test(< ?ind 2))
	=>
	(assert (lastMove MoveLeft))
	(assert (lastChecked))
)
(defrule LastMoveRight
	(not(lastChecked))
	(or 
		(previous-choice (index ?ind) (choice MoveRight))
	)
	(test(< ?ind 2))
	=>
	(assert (lastMove MoveRight))
	(assert (lastChecked))
)
(defrule LastMoveUp
	(not(lastChecked))
	(or 
		(previous-choice (index ?ind) (choice MoveUp))
	)
	(test(< ?ind 2))
	=>
	(assert (lastMove MoveUp))
	(assert (lastChecked))
)
(defrule LastMoveDown
	(not(lastChecked))
	(or 
		(previous-choice (index ?ind) (choice MoveDown))
	)
	(test(< ?ind 2))
	=>
	(assert (lastMove MoveDown))
	(assert (lastChecked))
)

(defrule moveRight
	(not (decided))
	(can-move MoveRight)
	(or

		(and 
			(tooManyWays)
			(lastMove MoveRight)
		)

		(and 
			(not (can-move MoveLeft))
			(not (can-move MoveUp))
			(not (can-move MoveDown))
		)
		(and
			(or
				(lastMove MoveRight)
				(lastMove MoveDown)
			)
			(not (can-move MoveUp))
			(not (lastMove MoveLeft))
		)
	)
	=>
	(assert (decided))
	(bind ?*decision* MoveRight)
)








(defrule moveLeft
	(can-move MoveLeft)
	(not (decided))
	(or 
		(and 
			(tooManyWays)
			(lastMove MoveLeft)
		)
		(and 
			(not (can-move MoveRight))
			(not (can-move MoveUp))
			(not (can-move MoveDown))
		)
				(and
					(or
						(lastMove MoveUp)
						(lastMove MoveLeft)
					)
					(not (can-move MoveDown))
					(not (lastMove MoveRight))
				)
		
	)
	=>
	(assert (decided))
	(bind ?*decision* MoveLeft)
)
(defrule moveUp
	(can-move MoveUp)
	(not (decided))
	(or 
		(and 
			(tooManyWays)
			(lastMove MoveUp)
		)
		(and 
			(not (can-move MoveRight))
			(not (can-move MoveLeft))
			(not (can-move MoveDown))
		)
				(and
					(or
						(lastMove MoveUp)
						(lastMove MoveRight)
					)
					(not (can-move MoveLeft))
					(not (lastMove MoveDown))
				)
		
	)
	=>
	(assert (decided))
	(bind ?*decision* MoveUp)
)
(defrule moveDown
(can-move MoveDown)
	(not (decided))
	(or 
		(and 
			(tooManyWays)
			(lastMove MoveDown)
		)
		(and 
			(not (can-move MoveRight))
			(not (can-move MoveLeft))
			(not (can-move MoveUp))
		)
				(and
					(or
						(lastMove MoveDown)
						(lastMove MoveLeft)
					)
					(not (can-move MoveRight))
					(not (lastMove MoveUp))
				)
		
	)
	=>
	(assert (decided))
	(bind ?*decision* MoveDown)
)



(defrule lastOption4
	(not (decided))
	(can-move MoveDown)
	=>
	(assert (decided))
	(bind ?*decision* MoveDown)
)

(defrule lastOption
	(not (decided))
	(can-move MoveRight)
	=>
	(assert (decided))
	(bind ?*decision* MoveRight)
)
(defrule lastOption2
	(not (decided))
	(can-move MoveLeft)
	=>
	(assert (decided))
	(bind ?*decision* MoveLeft)
)
(defrule lastOption3
	(not (decided))
	(can-move MoveUp)
	=>
	(assert (decided))
	(bind ?*decision* MoveUp)
)




