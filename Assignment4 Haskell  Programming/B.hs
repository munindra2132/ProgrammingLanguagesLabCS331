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

gcdList :: Integral t => [t] -> t -> t
gcdList [] b = b
gcdList (x:xs) b = gcdList xs (mgcd b x)


prod :: Num p => [p] -> p
prod [] = 1
prod (x:xs) = x * ( prod xs)


main :: IO ()
main = do
   putStrLn "Enter the input list in the form [ a, b, c, d]"
   input <- getLine 
   let arr = read input :: [Int]
   let g = gcdList arr 0
   let pr = prod arr
   let res = (prod arr) `div` (gcdList arr 0)
   putStrLn "LCM of all the numbers of the list is -"
   print res
   