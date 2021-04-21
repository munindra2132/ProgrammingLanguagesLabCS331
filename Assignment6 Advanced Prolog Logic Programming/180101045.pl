 /*
* CS331 Programming Language Lab
* Assignment 6 - Advanced Prolog Programming
*
* ===> Student Details
*      Name - Munindra Naik
*      Roll - 180101045
*
*  ==> Shortest Path on a maze with faultynode 
* Instructions to execute -
* 
* On Windows(GNU Prolog)
* 1) Change to the directory where the file is stored using the below command
*    ====>   change_directory('file directory').
* 2) Load the file using the below command
*    ====>   ['filename'].
* 3) To execute the loaded program write on the console 'interchange(X),shortest_path(src,dest,PATH).'
*    where  X  - list of nodes which have to interchange their faultyness
*           src - source node
*           dest - destination node
*    Ex - interchange([88]),shortest_path(11,99,P).
*/
:- dynamic(faultynode/1).
:- include('Mazedata.pl').

interchange([]).

interchange([X|R]):-
    faultynode(X),
    retract(faultynode(X)),
    !,
    toggle(R).

interchange([X|R]):-
    assertz(faultynode(X)),
    interchange(R).


search(Goal,Path,[[Goal|Rest]|_],_):-
    reverse([Goal|Rest],Path).

search(Goal,Path,[Curpath|Rest],V) :-
   Curpath = [Lastnode|_],
   Lastnode \== Goal,
   findall(X,((mazelink(X,Lastnode)),\+ faultynode(X),\+ member(X,V), \+ member(X,Curpath) ), Nearnode),
   append(V,Nearnode,MV),
   maplist(createpath(Curpath),Nearnode,Latestpath),
   append(Rest,Latestpath,MQ),
   search(Goal,Path,MQ,MV).

search(_,_,_,_):-
    write('No Path Available'),!.

createpath(A,B,[B|A]).

shortest_path(Start, Goal, Path):-
    search(Goal,Path,[[Start]],[Start]),!.


