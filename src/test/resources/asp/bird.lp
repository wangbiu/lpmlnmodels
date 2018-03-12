bird(jo) :- residentBird(jo).
bird(jo) :- migratoryBird(jo).
:- residentBird(jo), migratoryBird(jo).
residentBird(jo).
