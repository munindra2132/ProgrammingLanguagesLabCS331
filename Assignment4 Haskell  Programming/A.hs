{-
 CS 331 Programming Langugage Lab
 Assignment IV : Haskell Programming
 
 Student Details -
 Name - Munindra Naik
 Roll -  180101045
 
 Part A - Print List from entered input
 
 Instructions to execute -
 1) On terminal type "ghc -o A A.hs" and hit Enter
 2) After successful compliation type "./A" to execute the program
 3) The program will ask for input, after the input is given press enter and the result will be printed on terminal
 
 -}

main :: IO ()
main = do
  putStrLn "Enter input in the form [a, b, c, d] -"
  input <- getLine
  let x = read input :: [Int]
  putStrLn "The list entered is -"
  print x
