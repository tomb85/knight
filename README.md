# Knight Moves Assignment

### Algorithm Overview

The main class is `SequenceExtractor.java` that return all sequences of given length subject to vowel constrain from a given key. The algorithm is structured as follows:

* Initialise sequence stack with the origin node
* Obtain the next allowed node from the current node and add it to the stack
* If no further node is available (either because a sequence of desired length has been found or the vowel limit has been reached) increment the sequence count (if required) and remove the node from the stack
* Continue until the stack becomes empty

As the sequence search is independent of each other it can be parallelised and this is achieved by the `SequenceAggregator.java` class that creates a task for each key and addgregates the results.

### Installation

To run tests type:
```sh
$ mvn clean test
```
To install and create distribution package:
```sh
$ mvn clean install
```
To run the application simply unpack the distribution package:
```sh
$ unzip knight-1.0-SNAPSHOT-bin.zip
$ cd knight-1.0-SNAPSHOT/
$ java -jar knight-1.0-SNAPSHOT.jar 10
```
### Issues and Further Work
For sequence of length 10 it returns 1013398 which looks OK and runs resonably fast (less than 1 second) on a quad-core PC. For sequence of length 16 the time increases to 2 minutes. Unfortunatelly I have never had a chance to run it for sequence of length 32.

This means there is still a big potential to optimise here, mainly:

* There is a lot of repetition in traversing the sequence tree as it will be very similiar for each of the source keys
* I imagine some kind of online approach is required here which involves building all possible sequences with the minimal number of passes