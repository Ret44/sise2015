(defglobal ?*decision* = "")

(defrule canMove
	(can-move ?x)
	=>
	(bind ?*decision* ?x)
	(assert (decision-was-made))
)