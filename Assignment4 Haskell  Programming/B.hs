{-
 CS 331 Programming Langugage Lab
 Assignment IV :  Haskell Programming
 
 Student Details -
 Name - Munindra Naik
 Roll -  180101045
 
 Part B - Generate LCM of the list
 
 Instructions to execute -
 1) On terminal type "ghc -o B B.hs" and hit Enter
 2) After successful compliation type "./B" to execute the program
 3) The program will ask for input, after the input is given press enter and the result will be printed on terminal
 

 -}


mgcd :: Integral t => t -> t -> t
mgcd a b
   | b == 0 = a
   | otherwise = mgcd b (a `mod` b)

lcmlist :: Integral t => [t] -> t -> t
lcmlist [] a  = a
lcmlist (x:xs) a = lcmlist xs na 
   where
      na = (a * x) `div` (mgcd a x)  

lcmlisth :: Integral p => [p] -> p
lcmlisth [] = 1
lcmlisth xs = lcmlist xs 1

main :: IO ()
main = do
   putStrLn "Running test cases -"
   putStrLn " "
   let test = [ [ 14, 88, 22, 1, 9, 53, 4, 16, 5], [1,3,6,2,1990,4,3], [14,25,2,43,2,43,41], [ 1,1,1,1,14,28,36], [1,2,3,4,5], [8,7,6,5,4,3,2,1], [1,3,2], [-1,1,-2,2,-3,3], [10011, 1,1002,2,3,1004]]
   let result = map lcmlisth test
   let s = zip test result 
   putStrLn  "(Test Input , Test Ouput)"
   mapM_ print s
   putStrLn "Done Test Cases "
   putStrLn "Enter the input list in the form [ a, b, c, d]"
   input <- getLine 
   let arr = read input :: [Int]
   let res = lcmlisth arr
   putStrLn "LCM of all the numbers of the list is -"
   print res
   