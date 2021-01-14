# ServiceNow

Overview of the solution:
Investigates the sentences by prepare a map of patterns with a special character for each word.
Assumption, % is a special character that can't be used as word (can be replaced by other special character if required).
For example, the sentence "Naomi is getting into the car" will produce 6 patterns as following:
1. "% is getting into the car",
2. "Naomi % getting into the car",
3. "Naomi is % into the car",
4. "Naomi is getting % the car",
5. "Naomi is getting into % car",
6. "Naomi is getting into the %"

For each pattern check if the pattern is already in the map and if so
add the sentence to his group and add the changing word to the diffOfSimilarity set.
At the end removes group with one sentence.


How to run?
The input file should be located here: src/main/resources/input.csv 
1. What can you say about the complexity of your code?
The complexity is O(n*m) where n is number of words in each sentence and m is number of sentences.
2. How will your algorithm scale?
In this solution only vertical scale is available (by adding more CPU and memory). To support horizontal scale we must consider a shared storage where the groups will be located (Database or cached managed storage) and devide the input by multiple threads or application instances.
3. If you had two weeks to do this task, what would you have done differently? What would be
better?
  a) I'd support the horizontal scale as described above. Split the input by number of words in sentence - no need to check for similarity sentences with different number of words.
  b) Use Spring Framework for dependency injection (input and output handling and the service itself)
  c) Provide the input file as parameter to the application
  d) Proper error handling. The date pattern can be configurable and have a special exception. 
  e) Add more unit tests for edge cases
  
