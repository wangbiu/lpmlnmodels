kse_solve_trick.  
:~ kse_solve_trick. [1@1]
:~ kse_solve_trick. [1@2]

#show bird/1.
residentBird(1) :- not unsat(0, 1, 1).
unsat(0, 1, 1) :- not residentBird(1).
:~unsat(0, 1, 1). [1@1, 0]

migratoryBird(1) :- not unsat(1, 1, 2).
unsat(1, 1, 2) :- not migratoryBird(1).
:~unsat(1, 1, 2). [2@1, 1]

residentBird(2) :- not unsat(2, 1, 1).
unsat(2, 1, 1) :- not residentBird(2).
:~unsat(2, 1, 1). [1@1, 2]

migratoryBird(2) :- not unsat(3, 1, 2).
unsat(3, 1, 2) :- not migratoryBird(2).
:~unsat(3, 1, 2). [2@1, 3]

residentBird(3) :- not unsat(4, 1, 1).
unsat(4, 1, 1) :- not residentBird(3).
:~unsat(4, 1, 1). [1@1, 4]

migratoryBird(3) :- not unsat(5, 1, 2).
unsat(5, 1, 2) :- not migratoryBird(3).
:~unsat(5, 1, 2). [2@1, 5]

bird(1) :- residentBird(1), not unsat(6, 2, 1).
unsat(6, 2, 1) :- residentBird(1), not bird(1).
:~unsat(6, 2, 1). [1@2, 6]

bird(2) :- residentBird(2), not unsat(7, 2, 1).
unsat(7, 2, 1) :- residentBird(2), not bird(2).
:~unsat(7, 2, 1). [1@2, 7]

bird(3) :- residentBird(3), not unsat(8, 2, 1).
unsat(8, 2, 1) :- residentBird(3), not bird(3).
:~unsat(8, 2, 1). [1@2, 8]

bird(1) :- migratoryBird(1), not unsat(9, 2, 1).
unsat(9, 2, 1) :- migratoryBird(1), not bird(1).
:~unsat(9, 2, 1). [1@2, 9]

bird(2) :- migratoryBird(2), not unsat(10, 2, 1).
unsat(10, 2, 1) :- migratoryBird(2), not bird(2).
:~unsat(10, 2, 1). [1@2, 10]

bird(3) :- migratoryBird(3), not unsat(11, 2, 1).
unsat(11, 2, 1) :- migratoryBird(3), not bird(3).
:~unsat(11, 2, 1). [1@2, 11]

:- migratoryBird(1),residentBird(1), not unsat(12, 2, 1).
unsat(12, 2, 1) :- migratoryBird(1), residentBird(1).
:~unsat(12, 2, 1). [1@2, 12]

:- migratoryBird(2),residentBird(2), not unsat(13, 2, 1).
unsat(13, 2, 1) :- migratoryBird(2), residentBird(2).
:~unsat(13, 2, 1). [1@2, 13]

:- migratoryBird(3),residentBird(3), not unsat(14, 2, 1).
unsat(14, 2, 1) :- migratoryBird(3), residentBird(3).
:~unsat(14, 2, 1). [1@2, 14]

