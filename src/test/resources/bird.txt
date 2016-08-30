bird(X) :- residentBird(X).
bird(X) :- migratoryBird(X).
:- residentBird(X), migratoryBird(X).
residentBird(jo).
migratoryBird(jo).