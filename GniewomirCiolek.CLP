
(defglobal ?*ile* = 0)
(defglobal ?*indeks* = 2)

(defrule WayRight
	(can-move MoveRight)
	=>
	(bind ?*ile* (+ ?*ile* 1))
)
(defrule WayLeft
	(can-move MoveLeft)
	=>
	(bind ?*ile* (+ ?*ile* 1))
)
(defrule WayUp
	(can-move MoveUp)
	=>
	(bind ?*ile* (+ ?*ile* 1))
)
(defrule WayDown
	(can-move MoveDown)
	=>
	(bind ?*ile* (+ ?*ile* 1))
)
(defrule HowManyWays
	(initial-fact)
	=>
	(assert (ways ?*ile*))
)

(defrule lastChoice
	(initial-fact)
	(previous-choice (index 1) (choice ?lastC))
	=>
	(assert (lastCC ?lastC))
)
(defrule pickItem
	
	
	(can PickUpItem)
	(previous-choice (index 1) (choice Search))
	(not (previous-choice (index 1) (choice PickUp)))
	(item ?theItem)
	(chamber (index 1) (item-level ?chamberItem) (checked ?isChecked))
	(not (eq ?isChecked false))
	(test(> ?chamberItem ?theItem ))
	=>
	(assert (decided))
	(bind ?*decision* PickUpItem)
)
(defrule checkItem
	(can Search)
	(not (decided))
	(not (previous-choice (index 1) (choice Search|PickUp)))
	(not (room-searched))
	(not (item-check-done))
	(item ?theItem)
	(test (< ?theItem 10))
	=>
	(assert (decided))
	(bind ?*decision* Search)
)
(defrule lastWasCan
	(previous-choice (index 1) (choice Search|PickUp))
	=>
	(assert (lastCan))
	(bind ?*indeks* 3)
)
(defrule lastWasCan
	(lastCan)
	(previous-choice (index 2) (choice Search|PickUp))
	=>
	(bind ?*indeks* 4)
)

(defrule LastMoveLeft
		(previous-choice (index ?ind) (choice MoveLeft))
	(test(< ?ind (+ ?*indeks* 1)))
	=>
	(assert (lastMove MoveLeft))
	(assert (lastChecked))
)
(defrule LastMoveRight
	(previous-choice (index ?ind) (choice MoveRight))
	(not(lastChecked))
	(test(< ?ind (+ ?*indeks* 1)))
	=>
	(assert (lastMove MoveRight))
	(assert (lastChecked))
)
(defrule LastMoveUp
	
	(or 
		(previous-choice (index ?ind) (choice MoveUp))
	)
	(not(lastChecked))
	(test(< ?ind (+ ?*indeks* 1)))
	=>
	(assert (lastMove MoveUp))
	(assert (lastChecked))
)
(defrule LastMoveDown
	
	(or 
		(previous-choice (index ?ind) (choice MoveDown))
	)
	(not(lastChecked))
	(test(< ?ind (+ ?*indeks* 1)))
	=>
	(assert (lastMove MoveDown))
	(assert (lastChecked))
)

(defrule moveRight
	(can-move MoveRight)
	(not (decided))
	
	(or
		(and
			(ways 2)
			(not (lastMove MoveLeft))
		)
		
		(and 
			(tooManyWays)
			(lastMove MoveRight)
		)

		(ways 1)
		(and
			(or
				(lastMove MoveRight)
				(lastMove MoveDown)
			)
			(not (can-move MoveUp))
			(not (lastMove MoveLeft))
		)
		(and 
					(ways 3)
					(lastMove MoveDown)
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
			(ways 2)
			(not (lastMove MoveRight))
		)
		(and 
			(tooManyWays)
			(lastMove MoveLeft)
		)
		(ways 1)
				(and
					(or
						(lastMove MoveUp)
						(lastMove MoveLeft)
					)
					(not (can-move MoveDown))
					(not (lastMove MoveRight))
				)
				(and 
					(ways 3)
					(lastMove MoveUp)
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
			(ways 2)
			(not (lastMove MoveDown))
		)
		(and 
			(tooManyWays)
			(lastMove MoveUp)
		)
			(ways 1)
				(and
					(or
						(lastMove MoveUp)
						(lastMove MoveRight)
					)
					(not (can-move MoveLeft))
					(not (lastMove MoveDown))
				)
				(and 
					(ways 3)
					(lastMove MoveRight)
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
			(ways 2)
			(not (lastMove MoveUp))
		)
		(and 
			(tooManyWays)
			(lastMove MoveDown)
		)
			(ways 1)
				(and
					(or
						(lastMove MoveDown)
						(lastMove MoveLeft)
					)
					(not (can-move MoveRight))
					(not (lastMove MoveUp))
				)
				(and 
					(ways 3)
					(lastMove MoveLeft)
				)
		
	)
	=>
	(assert (decided))
	(bind ?*decision* MoveDown)
)





