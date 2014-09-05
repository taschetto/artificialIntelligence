prof(felipe).
prof(bordini).
it(ze).
instructor(carol).

staff(X) :- prof(X).
staff(X) :- it(X).
staff(X) :- instructor(X).

first([H|T],H).
rest([H|T],T).

%find(List, Element) -- find([a, b, c, d],b).

find([E|T], E).
find([_|T], E) :- find(T, E).