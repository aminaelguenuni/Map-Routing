/******************************************************************************
 *  readme.txt template                                                   
 *  Map
 *****************************************************************************/

Name(s):  Amina El Guenuni    
Login(s):      
Precept #:  
OS: Windows        
Compiler: javac
Editor:     
Hours:      


/******************************************************************************
 *  Explain your overall approach.
 *****************************************************************************/
I implemented the first two optimizations ideas suggusted in PA05:

For IDEA 1, instead of inserting all V vertices into the PQ upfront, I only insert the
  source. I also maintained a "touched" list of vertices modified each query,
  so re-initialization only resets those vertices instead of all V. Finally,
  the search stops as soon as the destination is popped from the PQ as suggusted. 
For IDEA 2, I implemented A* as suggusted with the updated formula, to make 
   sure that the search is biased and shifting towards the destination. 
Idea 1 helps most on short queries. While, idea 2 helps most on long ones.
Together they significantly cut both runtime and vertices examined as showcased in
this Readme document. 

/******************************************************************************
 *  Which input files did you use to test your program? Mark the
 *  ones where your answers agreed with our reference solutions and
 *  the ones where it disagreed. How long (in seconds) did your program
 *  take to solve each instance? How many vertices did it examine
 *  on average per shortest path query?
 *****************************************************************************/
I used the files provided below and added a timer as we did in previous assigments
to calculate the runtime and vertices. I am not sure where to find the reference 
solutions to check my work against it.  
Input file                Running Time (seconds)     Vertices    Agreed?
------------------------------------------------------------------------
usa-1000long.txt             21.897                     44580        NA
usa-5000short.txt            12.427                     4024         NA
usa-50000short.txt           119.469                    4294         NA


/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/
- From my testing, I didnt find any clear bugs, I have worked through different bugs
to make it work, namely the runtime counter and average queries one. As well, as understanding
what expected. However, the idea 1 and 2 were clearly explained which made is a bit easier to implement

/******************************************************************************
 *  List whatever help (if any) that you received.
 *****************************************************************************/
- The book provided
-Geeks for Geeks
-Google ressources debuging coding problems, A* algo and understanding and fixing other bugs encournted
-Youtube videos about A* algo and Dijkstra

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/
I think understanding what needed was foundametal for me, I dont know why, but I kept thinking that I needed
to comment off idea 1 implementation and then work on Idea 2, like the same strategy as PA 03. So, I had to do a lot 
of reading for the instructions and tried to read a bit between the lines to understand what expected coding wise. I also 
forgot comeplety about the runtime, until I checked the readme file and tried to use our previous assigment timer. Another
problem, or challenge, was testing if my algorithm is working, I decided to create a copy of the original folder, add timer and run
it and it is then, that I saw the difference between the improved algorithm and the original one provided. 
For instance: 
------ RESULTS ------
Total queries:         1000
Total time:            21.187 seconds
The original code shows this  
------ RESULTS ------
Total queries:         1000
Total time:            45.41 seconds

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/
I honestly enjoyed it, this assignment was a great demonstration of how small algorithmic changes 
produce significant real-world speedups. Seeing runtime drop from 45s to 21s 
on long queries, and vertices examined drop from 44,000 to 4,000 on short 
queries, made the value of A* and idea 1 improvement very important. 

//Comparaison: 
For 1000long:
Improved version:
------ RESULTS ------
Total queries:         1000
Total time:            21.187 seconds
The original code shows this  
------ RESULTS ------
Total queries:         1000
Total time:            45.41 seconds
For 5000short
 12.427seconds    
The original code:
Total time:            270.48 seconds



For 50000short:
119.469                    4294
The original code:
20min+ I had to stop it as it was taking way longer