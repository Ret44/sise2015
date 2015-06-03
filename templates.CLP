(defglobal ?*decision* = "")

(defglobal ?*agentID* = "")
(defglobal ?*pos-x* = "")
(defglobal ?*pos-y* = "")

(deftemplate previous-choice
	(slot index)
	(slot choice)
)


(deftemplate chamber
	(slot index)
	(multislot connections)
	(slot item-level)
	(slot checked)
	(multislot trail)
)