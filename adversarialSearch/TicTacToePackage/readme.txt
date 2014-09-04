/**********************************************************************
 *  Adversarial Search readme.txt template
 **********************************************************************/

Name: Guilherme de Mello Mattos Taschetto
Student ID: 12180247-4

Hours to complete assignment (optional): 4

/**********************************************************************
 *  Explain briefly how you implemented the helper methods for 
 * \texttt{getNextMove()}.
 **********************************************************************/

Os métodos MinValue e MaxValue são análogos ao pseudo-código do AIMA. Os
métodos auxiliares que merecem menção aqui são:

	- State MiniMaxPlayer#Result(State, Move)
	
	Explicado em um tópico posterior do relatório.

	- Move[] Actions(State)

	Este método é mais simples. Ele varre todas as posições do tabu-
leiro no estado e, para cada posição onde nenhum jogador fez uma jogada,
é criado um movimento correspondente.

	- bool TerminalTest(State)

	Simplesmente retorna se o estado é inicial através de uma chama-
da no método State#IsGameOver.

/**********************************************************************
 *  Explain briefly how you represented the utility of the end game. 
 * Did you use different values for different end configurations?
 **********************************************************************/

Se o jogo terminou em vitória do jogador PLAYER a utilidade é 1 (onde 
PLAYER é o jogador que está executando o algoritmo de busca).

Se o jogo terminou em vitória do jogador OTHER, a utilidade é -1 (onde
OTHER é o jogador adversário, que não está executando esta instância do
algoritmo).

Se nenhum dos casos anteriores for verdadeiro, significa que o jogo
terminou empatado e a utilidade é 0.

Todas o conjunto de configurações que se encaixem em cada caso tem a
mesma utilidade. Algo que poderia ser implementado é uma redução da
pontuação do vitorioso (e analogamente a do perdedor) em função da
altura da árvore - quanto mais profunda, mais turnos o jogo levou para
terminar e, por isso, a pontuação poderia ser menor.

/**********************************************************************
 *  Explain briefly how you generated the successor moves.
 **********************************************************************/

	- State MiniMaxPlayer#Result(State, Move)
	
	O método retorna o estado atingido a partir de um estado origem
ao realizar-se a ação especificada. Utilizei o método State#clone para
criar uma do estado atual, aplicando a ação do movimento através do mé-
todo State#setState(row, col, player), onde row e col vem do movimento
e player é o identificador do jogador do estado original.

Um detalhe importante é que devemos definir de quem é o turno após cons-
truir este novo estado, através de State#setTurn(other), onde other é 
o identificador do jogador adversário (que não é o player). Sem este
detalhe o algoritmo não funciona pois isso causará um erro na valoração
da função de Utilidade (basicamente o algoritmo retornará a valoração
de sempre vitória do jogador).

/**********************************************************************
 *  How often does your player wins when it plays as X against a 
 * randomizing player, and how often does it wins when it plays as O?
 **********************************************************************/

Venceu 100% como jogador X e 88% como jogador 0.

/**********************************************************************
 *  If you wanted to solve some other games using variations of minimax 
 * (such as the ones seen in class), which ones would you use, and how 
 * would you integrate them into this code? Why?
 **********************************************************************/

Creio que qualquer jogo baseado em turnos possa ser modelado para aplica-
ção do MiniMax. O esqueleto (nextMove, MinValue, MaxValue) do algoritmo 
não muda. O que precisa de adaptação para cada jogo são os métodos auxi-
liares (Actions, Successor, TerminalTest e Utility).

Como exemplo, poderia citar o jogo de escova (cartas), onde o jogador deve buscar
fazer pontos mas também de forma com que o adversário, no seu turno, tenha
uma maior dificuldade de realizar os seus pontos.

/**********************************************************************
 *  If you did the extra credit, describe your algorithm briefly and
 *  state the order of growth of the running time (in the worst case)
 *  for \texttt{getNextMove()}.
 **********************************************************************/

Implementei o AlphaBetaPrunning de acordo com o algoritmo do AIMA, acres-
centando os parâmetros no MinValue e MaxValue, realizando o corte da busca
em um ramo distindo da árvore quando a utilidade era <= alpha (no MinValue)
ou >= beta (no MaxValue), pois é sabido que não será encontrado um valor
melhor.

Sua complexidade de tempo é O(b^(m/2)) para o melhor caso e para o pior
caso é O(b^m) (idêntica ao Minimax), onde b é o branching factor e m é o 
tamanho máximo da árvore.

/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/

Nenhum conhecido até o momento.

/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including staff, classmates, and 
 *  friends) and attribute them by name.
 **********************************************************************/

Conversas conceituais com João Pedro Chagas (colega de classe).

/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/

Diferentemente da implementação do A*, o MiniMax não exigiu nenhuma
adaptação especial do algoritmo às estruturas de dados utilizadas em 
Java. Portanto, pode-se dizer que foi menos problemático implementá-lo.

/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
