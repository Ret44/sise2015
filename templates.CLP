(defglobal ?*decision* = "")

(defglobal ?*agentID* = "")
(defglobal ?*pos-x* = 0)
(defglobal ?*pos-y* = 0)

(deftemplate previous-choice
	(slot index
		(type INTEGER))
	(slot choice)
)


(deftemplate chamber
	(slot index
		(type INTEGER))
	(multislot connections)
	(slot item-level)
	(slot checked)
	(multislot trail)
)