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
 
Note - the code contains test cases as specified in the assignment 
        the test values are present inside list named test in line 35
        when the program is executed after typing ./C then pressing enter the test cases are performed 
        the output of the test values are printed on the screen
        then after that the user specific input is given to the program
 -}
 
qsort :: Ord a => [a] -> [a]
qsort [] = []
qsort (pt:array) = qsort lessVal ++ pivot ++ qsort moreVal
    where
       pivot = [y | y <- array, y==pt] ++ [pt]
       lessVal = [y | y <- array , y<pt]
       moreVal = [y | y<- array  , y>pt]

main :: IO ()
main = do
   putStrLn "Running test cases -"
   putStrLn " "
   let test = [ [ 14, 88, 22, 1, 9, 53, 4, 16, 5], [1,3,6,2,1990,4,3], [0,0,2,43,2,43,41], [ 1,1,1,1,0,0,0], [1,2,3,4,5], [8,7,6,5,4,3,2,1], [1,3,2], [-1,1,-2,2,-3,3], [10011, 1,1002,2,3,1004]]
   let res = map qsort test
   let s = zip test res 
   putStrLn  "(Test Input , Test Ouput)"
   mapM_ print s
   putStrLn " "
   putStrLn "Enter the list as input in the form [a,b,c,d] :" 
   input <-getLine
   let array = read input:: [Int]
   let result = qsort array
   putStrLn "Quick Sort result =>";
   print result
