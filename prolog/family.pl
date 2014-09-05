parent(antonio,joaozinho).
parent(maria,joaozinho).
parent(antonio,luis).
parent(maria,luis).
parent(antonio, cristina).
parent(maria, cristina).
parent(carlos, antonio).
parent(fernanda, antonio).
parent(pedro, maria).
parent(mariana, maria).
parent(jose, mariana).
parent(sandra, mariana).
parent(carlos, alberto).
parent(fernanda, alberto).
parent(carlos, marcia).
parent(fernanda, marcia).

gender(jose,male).
gender(pedro,male).
gender(carlos,male).
gender(antonio,male).
gender(alberto,male).
gender(joaozinho,male).
gender(luis,male).

gender(sandra,fem).
gender(mariana,fem).
gender(fernanda,fem).
gender(maria,fem).
gender(marcia,fem).
gender(cristina,fem).

father(X, Y) :- parent(X, Y), gender(X, male).
mother(X, Y) :- parent(X, Y), gender(X, fem).

sibling(X, Y) :- parent(Z, X), parent(Z, Y).
brother(X, Y) :- sibling(X, Y), gender(X, male), gender(Y, male), \+ (X=Y).
sister(X, Y) :- sibling(X, Y), gender(X, fem), gender(Y, fem), \+ (X=Y).

parentsibling(X, Y) :- parent(Z, Y), sibling(X, Z), \+ (X=Z).
uncle(X, Y) :- gender(X, male), parentsibling(X, Y).
aunt(X, Y) :- gender(X, fem), parentsibling(X, Y).

grandparent(X, Y) :- parent(Z, Y), parent(X, Z).
grandfather(X, Y) :- gender(X, male), grandparent(X, Y).
grandmother(X, Y) :- gender(X, fem), grandparent(X, Y).

greatgrandparent(X, Y) :- grandparent(Z, Y), parent(X, Z).
greatgrandfather(X, Y) :- gender(X, male), greatgrandparent(X, Y).
greatgrandmother(X, Y) :- gender(X, fem), greatgrandparent(X, Y).

ancestor(X, Y) :- parent(X, Y) ; parent(Z, Y), ancestor(X, Z).