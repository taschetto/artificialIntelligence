/**********************************************************************
 *  Hyrule's maze readme.txt template
 **********************************************************************/

Name: Guilherme de Mello Mattos Taschetto
Student ID: 12180247-4

Hours to complete assignment (optional): 6~8

/**********************************************************************
 *  Explain briefly how you implemented the datatype for states 
 * (i.e. to make it efficient in the closed list).
 **********************************************************************/

The closed list was called "explored" in my implementation. It is a
standard Java HashSet for storing Node objects. The Node#hashCode() method 
returns the Node's State#hashCode().

As suggested in the assignment, the State#hashCode() equals to
7 * State.x^2 + State.y.

Is is belived that this hash function allows a lookup in the HashSet to
have a O(1) complexity.

/**********************************************************************
 *  Explain briefly how you represented a search node
 *  (state + number of moves + previous search node).
 **********************************************************************/

The Node class constructor take 4 parameters:

  * Node(Problem, State, Parent, Action)

  Whereas:

    * Problem
        Reference to a Problem object. It contains several information and
        methods about the search problem. For the Node class, it contains
        the heuristic function evaluated to the node state (Node#H()
        method).
  
    * State
        This node state.

    * Parent
        A reference to the parent node (previous search node).

    * Action
        The action that was taken to get from the parent node state to
        this node state.

Other relevant methods:

  * G()
      Recursive method to get the number of moves needed to get to this
      node.

  * H()
      Uses the Problem#HeuristicFunction() method to evaluate this node
      heuristic so it is not bounded to a specifig heuristic algorithm.

  * F()
      The node "priority". It is simple sum between G() and H().

  * getParent() & getAction()
      Used by the Problem class for constructing the solution.

/**********************************************************************
 *  Explain briefly how you detected unsolvable problems.
 **********************************************************************/

 If the AStar#Solve method returned null, the problem is unsolvable.
 Lazy but works. :-P

/**********************************************************************
 *  If you wanted to solve random $10^8$ problem (i.e. a $10000 \times 
 * 10000$ grid), which would you prefer:  more time (say, 2x as much), 
 * more memory (say 2x as much), a better priority queue (say, 2x as 
 * fast), or a better priority function (say, one on the order of 
 * improvement from Hamming to Manhattan)? Why?
 **********************************************************************/

A better priority function. It would allow to focus towards the solution
more efficiently - thus saving time, memory and queue accesses.

/**********************************************************************
 *  If you did the extra credit, describe your algorithm briefly and
 *  state the order of growth of the running time (in the worst case)
 *  for isSolvable().
 **********************************************************************/

I did not. :-(

/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/

None of them are known.

/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including staff, classmates, and 
 *  friends) and attribute them by name.
 **********************************************************************/

Conceptual and implementation conversations with João Pedro Chagas
(classmate). Code was not shared. :-)

/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/

The algorithm from AIMA's book has the current statement:

  ...
  else if child is in frontier with highter PATH-COST then
    replace that frontier node with child

However, frontier is a Priority Queue. And a queue, by its definition,
doesn't provide random access to its elements - it only pops from the 
queue's HEAD and pushes to the queue's TAIL. That being said, it is
not possible to replace the node with a new one.

To work around this limitation, I've made a change to the algorithm.

  if child is in explored skip the child
  else add child to frontier.

This approach seemed to work very well. But I was not confident about
its implications on the algorithm completude. Then I found this topic
on Stack Overflow

http://stackoverflow.com/questions/12691217/can-i-modify-the-standard-a-a-star-under-consistent-heuristic-uniform-cost

  which the user had the same problem. It was stated by the users that
it would work when using non monotonic heuristic. But then the time was
over and I had to deliver the assignment without total understanding
about this point.

/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *********************************************************************/

 It was fun to engage into this implementation. More assignments like
 this are welcome.

 Congratulations for the Professor and his Assistant for the high
 quality boilerplate code. This isn't common here at FACIN. :-)


