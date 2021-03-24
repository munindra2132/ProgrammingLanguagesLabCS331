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

formlist :: [Char] -> IO ()
formlist xs = do
   let list = read xs :: [Int]
   putStr "The list is "
   print list
   
main :: IO ()
main = do
  putStrLn "Running test cases"
  let test  = "[1,2,3,4]"
  let test1 = "[13,32,53,5435,75453,53]"
  let test2 =  "[12,65,22,76,32,76]"
  let test3 = "[87,543,43,6546326,6526343]"
  putStr "Input String -"
  print test
  formlist test
  putStrLn ""
  putStr "Input String -"
  print test1
  formlist test1
  putStrLn ""
  putStr "Input String -"
  print test2
  formlist test2
  putStrLn ""
  putStr "Input String -"
  print test3
  formlist test3
  putStrLn "Done Test Cases "
  putStrLn "Enter input in the form [a, b, c, d] -"
  input <- getLine
  formlist input
  
