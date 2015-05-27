```
Project Description.
Step 1: Generate 100 files
if CLI parameter is “-g [foler_name]",
1. generates 100 files in a specified folder, named from 001.txt to 100.txt
2. each file contains 10,000 text lines
3. each line is a space separated list of random 10 integers
4. each integer contains 8 digits
5. prints file generation timing statistics per file and total

Step 2:  Merge all numbers from some files
if CLI parameter is “-s [foler_name]"
1. reads 10 files from a specified folder, named from 01.txt to 10.txt and prints out time it took to finish reading all files
2. puts all the numbers into a map, with file name being the key
3. uses fork/join paradigm to map/reduce this map in order to sort all the numbers in the shortest possible time
4. outputs results into a single file, with the same line structure, named res.txt in the same folder
5. prints how long it took to  read the files, sort the numbers and write the file

---
Solution:
1. Use Java8 BufferedWriter to write data to files. We do not want to use fork/join to write file, because it is IO bound.
I implemented write files by fork/join version. The speed was much slower than this version, so I deleted it.

2. Use two different way to implement read, sort, merge sort to new files
1) Use Java 8 Stream to read file to stream and sort it to Stream at dev.stream.MergeSortFilesMain.
2) Use fork-join to sort file  at fork.join.MergeSortFilesForkJoinMain

3. Algorithm
1). Sort each file
2). Merge all sorted LinkedList contain file data by Priority Queue

There are a lot discussion on Stream or Fork-Join. The performance could be vary according to CPU, Memory size and data distributed ways

ATTENTION:
This project need JDK 8.0 to support to run.

Command to Build the Project:
mvn package
if you got build failed, make sure your you set your $JAVA_HOME at .bash_profile as below:
export JAVA_HOME=$(/usr/libexec/java_home)

---------------------Set Your $JAVA_HOME----------------------
$ vim .bash_profile
export JAVA_HOME=$(/usr/libexec/java_home)

$ source .bash_profile

$ echo $JAVA_HOME
/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home
----------------------------------------------------------------

Command to Run:
1. generate files:
mvn exec:java -Dexec.mainClass="dev.fun.Main" -Dexec.args="-g ./output/"

2. sort files:
mvn exec:java -Dexec.mainClass="dev.fun.Main" -Dexec.args="-s ./input/"
mvn exec:java -Dexec.mainClass="fork.join.MergeMain" -Dexec.args="-s ./input/"

Difficulty:
The difficulty for this project is not to provide a solution. The most hard part is to provide a fastest solution.
Java has many ways to implement read and write files, which way is the fast? Java has a few ways to implement
concurrency, which way is the fastest way. Acutally, the answer will be different if the CPU, memory, disk bandwidth
data size and data location. There are a lot discussion on this topic. A couple of graphs from articles listed at
Reference below.

![alt tag](/src/main/resources/File-Indexing.jpg)
![alt tag](/src/main/resources/3Fig1.png)

Limitation:
With limited time, some codes are duplicated and not optimized
－－－－－－－－－－－－

Start to generat files from 01.txt to 100.txt at ./output/
writeInputDocument in 0.045 Second
writeInputDocument in 0.019 Second
writeInputDocument in 0.011 Second
writeInputDocument in 0.012 Second
writeInputDocument in 0.011 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.009 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.009 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.009 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.009 Second
writeInputDocument in 0.009 Second
writeInputDocument in 0.009 Second
writeInputDocument in 0.009 Second
writeInputDocument in 0.009 Second
writeInputDocument in 0.021 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.009 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.009 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.006 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.02 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.006 Second
writeInputDocument in 0.006 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.006 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.006 Second
writeInputDocument in 0.006 Second
writeInputDocument in 0.006 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.009 Second
writeInputDocument in 0.006 Second
writeInputDocument in 0.006 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.006 Second
writeInputDocument in 0.006 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.008 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.006 Second
writeInputDocument in 0.006 Second
writeInputDocument in 0.009 Second
writeInputDocument in 0.007 Second
writeInputDocument in 0.007 Second
createAllDocuments 100 FILES in 4.831 Second

-------------------------------------------------------
Start to read and sort data files from 01.txt to 10.txt at ./input/
Read a File Time = 0.003 Second, Sort a File Time = 0.42 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.193 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.137 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.067 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.081 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.091 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.13 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.07 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.068 Second
Read a File Time = 0.0 Second, Sort a File Time = 1.02 Second
Read All Files and Sort Separately Time = 2.288 Second
MergeSort All Files and Write to One File Time  = 0.494 Second
Total Time = 2.782

Read a File Time = 0.0 Second, Sort a File Time = 0.097 Second
Read a File Time = 0.001 Second, Sort a File Time = 0.044 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.045 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.048 Second
Read a File Time = 0.001 Second, Sort a File Time = 0.044 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.046 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.044 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.142 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.045 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.044 Second
Read All Files and Sort Separately Time = 0.601 Second
MergeSort All Files and Write to One File Time  = 0.473 Second
Total Time = 1.0739999999999998

Read a File Time = 0.0 Second, Sort a File Time = 0.04 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.042 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.044 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.04 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.045 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.054 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.145 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.047 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.044 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.042 Second
Read All Files and Sort Separately Time = 0.545 Second
MergeSort All Files and Write to One File Time  = 0.398 Second
Total Time = 0.9430000000000001

Read a File Time = 0.0 Second, Sort a File Time = 0.042 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.042 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.043 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.04 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.046 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.045 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.052 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.167 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.046 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.039 Second
Read All Files and Sort Separately Time = 0.563 Second
MergeSort All Files and Write to One File Time  = 0.384 Second
Total Time = 0.947

Read a File Time = 0.0 Second, Sort a File Time = 0.046 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.072 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.042 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.042 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.048 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.047 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.047 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.044 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.049 Second
Read a File Time = 0.0 Second, Sort a File Time = 0.049 Second
Read All Files and Sort Separately Time = 0.487 Second
MergeSort All Files and Write to One File Time  = 0.457 Second
Total Time = 0.944

--------------------------------------------------------------------

Start to read and sort data files from 01.txt to 10.txt at ./input/
Read a File Time = 0.045 Second
Read a File Time = 0.031 Second
Read a File Time = 0.005 Second
Read a File Time = 0.005 Second
Read a File Time = 0.004 Second
Read a File Time = 0.006 Second
Read a File Time = 0.006 Second
Read a File Time = 0.005 Second
Read a File Time = 0.004 Second
Read a File Time = 0.005 Second
Read all files --fork/join took 0.125 Second
Sort each file --forkJoin in 1.412 Second
Merge Sort all files --forkJoin in 0.55 Second
Total time --fork/join took 2.087 Second

Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read a File Time = 0.003 Second
Read a File Time = 0.003 Second
Read a File Time = 0.002 Second
Read a File Time = 0.003 Second
Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read all files --fork/join took 0.023 Second
Sort each file --forkJoin in 0.919 Second
Merge Sort all files --forkJoin in 0.506 Second
Total time --fork/join took 1.448 Second

Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read a File Time = 0.003 Second
Read a File Time = 0.004 Second
Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read a File Time = 0.003 Second
Read a File Time = 0.002 Second
Read all files --fork/join took 0.024 Second
Sort each file --forkJoin in 0.378 Second
Merge Sort all files --forkJoin in 0.541 Second
Total time --fork/join took 0.943 Second

Read a File Time = 0.003 Second
Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read a File Time = 0.003 Second
Read a File Time = 0.003 Second
Read a File Time = 0.003 Second
Read all files --fork/join took 0.024 Second
Sort each file --forkJoin in 0.473 Second
Merge Sort all files --forkJoin in 0.43 Second
Total time --fork/join took 0.927 Second

Read a File Time = 0.002 Second
Read a File Time = 0.002 Second
Read a File Time = 0.001 Second
Read a File Time = 0.002 Second
Read a File Time = 0.001 Second
Read a File Time = 0.015 Second
Read a File Time = 0.002 Second
Read a File Time = 0.003 Second
Read a File Time = 0.002 Second
Read a File Time = 0.003 Second
Read all files --fork/join took 0.033 Second
Sort each file --forkJoin in 0.455 Second
Merge Sort all files --forkJoin in 0.43 Second
Total time --fork/join took 0.918 Second
```

reference:
http://blog.takipi.com/forkjoin-framework-vs-parallel-streams-vs-executorservice-the-ultimate-benchmark/
http://www.infoq.com/articles/forkjoin-to-parallel-streams

...