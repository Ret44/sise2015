(defglobal ?*decision* = "")

(defglobal ?*agentID* = "")
(defglobal ?*pos-x* = "")
(defglobal ?*pos-y* = "")

(deftemplate previous-choice
	(slot index)
	(slot choice)
)

(deftemplate previous-chamber
	(multislot connections)
	(slot item-level)
	(slot checked)
	(multislot trail)
)	

(defrule canMove
	(can-move ?x)
	=>
	(bind ?*decision* ?x)
	(assert (decision-was-made))
)