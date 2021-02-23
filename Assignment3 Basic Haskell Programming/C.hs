{-
 CS 331 Programming Langugage Lab
 Assignment III : Basic Haskell Programming
 
 Student Details -
 Name - Munindra Naik
 Roll -  180101045
 
 Part C - Quicksort on list
 
 Instructions to execute -
 1) On terminal type "ghc -o C C.hs" and hit Enter
 2) After successful compliation type "./C" to execute the program
 3) The program will ask for a number as an input, after the input is given press enter and the result will be printed on terminal
 -}
 
qsort [] = []
qsort (x1:x2) = qsort minVal ++ pivot ++ maxVal
    where
       pivot = [y | y<-x2, y==x1] ++ [x1]
       minVal = [y | y <- x2, y<x1]
       maxVal = [y | y<-x2, y>x1]

main = do
   let qs = [ 14, 88, 22, 1, 9, 53, 4, 16, 5]
   let newQs = qsort qs
   putStr "Quick Sort =>";
   print newQs