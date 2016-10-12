# PortSimulation

Port Simulation, using the [M/M/C](https://en.wikipedia.org/wiki/M/M/c_queue) queuing model

## Build With

* Java 8

## Problem

The problem is described in detail in the following Word file - [Problem](Problem.docx)

## Usage

```
java -jar PortSimulation.jar Cranes Docks Length_1 Length_2 ...
```

Where:

```
Cranes      =   Number of cranes
Docks       =   Number of docks
Length_1    =   Length of the first dock
Length_2    =   Length of the second dock
...         =   Length of the rest docks
```

## Example

Input:

```
java -jar PortSimulation.jar 5 3 150 100 50
```

For every iteration - Output:

```
----------------------------------------------------------------------------------------------------
Simulation Parameters: [1, 1, 3]
Time Limit (hours): 100000
Evaluation Index: 4619240501
Total Ships: 4595
----------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------
Simulation Parameters: [1, 2, 2]
Time Limit (hours): 100000
Evaluation Index: 7810678170
Total Ships: 6128
----------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------
Simulation Parameters: [1, 3, 1]
Time Limit (hours): 100000
Evaluation Index: 6331142392
Total Ships: 5422
----------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------
Simulation Parameters: [2, 1, 2]
Time Limit (hours): 100000
Evaluation Index: 5961501902
Total Ships: 5202
----------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------
Simulation Parameters: [2, 2, 1]
Time Limit (hours): 100000
Evaluation Index: 6953585212
Total Ships: 5635
----------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------
Simulation Parameters: [3, 1, 1]
Time Limit (hours): 100000
Evaluation Index: 7087458411
Total Ships: 5706
----------------------------------------------------------------------------------------------------
```

## Authors

* **Nikos Bampamis** - [nikbam](https://github.com/nikbam)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
