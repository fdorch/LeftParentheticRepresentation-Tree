# README #

This repository is read-only, make a private fork in the bitbucket server
for your own files. Clone this forked repository to your local computer and solve
the task.

See on viienda kodutöö failide hoidla, mida saab kasutada ainult algseks lugemiseks.
Töötamiseks looge endale isiklik repositoorium, näiteks privaatne 'fork' bitbucket serverisse, millest saate luua klooni oma arvutisse.

## Command line examples. Näidete kasutamine käsurealt ##
#### Compilation. Kompileerimine: ####

```
#!bash

javac -cp src src/Node.java
```

#### Execution. Käivitamine: ####

```
#!bash

java -cp src Node
```


### Usage of tests. Testide kasutamine ###
#### Compilation of a test. Testi kompileerimine: ####

```
#!bash

javac -encoding utf8 -cp 'src:test:test/junit-4.13.2.jar:test/hamcrest-core-1.3.jar' test/NodeTest.java

```
In Windows replace colons by semicolons. Sama Windows aknas (koolonite asemel peavad olema semikoolonid):

```
#!bash

javac -encoding utf8 -cp 'src;test;test/junit-4.13.2.jar;test/hamcrest-core-1.3.jar' test/NodeTest.java


```

#### Running a test. Testi käivitamine: ####

```
#!bash

java -cp 'src:test:test/junit-4.13.2.jar:test/hamcrest-core-1.3.jar' org.junit.runner.JUnitCore NodeTest
```

The same for Windows. Sama Windows aknas (koolonite asemel semikoolonid):

```
#!bash

java -cp 'src;test;test/junit-4.13.2.jar;test/hamcrest-core-1.3.jar' org.junit.runner.JUnitCore NodeTest
```
