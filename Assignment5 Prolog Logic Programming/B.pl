/*
* Part B - Checking Sublist 

* On GNU Prolog
* 1) Change directory on the prolog terminal where the desired file is present using the below command
*        change_directory('file_directory').
* 2) load the file using the below command
*        ['B.pl'].
* 3) use the below format to run
*       sublist(X,Y)
*
* Note - on GNU Prolog it may throw error sublist/2 cannot be redifned , 
*        then in that case just replace 'sublist' with 'sublist1' in the code and run.
*
*  Ex - sublist([a,b],[a,c,b,b,d]).
*/
sublist([],_).
sublist([X|Y],[X|Z]):-
	sublist(Y,Z),
	!.
sublist([X|Y],[_|Z]):-
	sublist([X|Y],Z),
	!.