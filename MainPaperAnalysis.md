# The following is a technical paper analysis and report of "Concurrency in Go and Java: Performance Analysis"
## Abstract
To analyze concurrency features of the two programming languages, Java threads and Goroutines have been used. Go is a compiled, 
garbage collected and concurrent programming language. It is designed for fast compilation time and ease of programming. Java is 
a concurrent, object-oriented, and garbage-collected programming language.
## Method
**Matrix multiplication** has been used as a standard to measure performance. Define two matrices say A and B that have the same length of row 
and column. Then divide the first matrix into some parts, then calculate product of part A and matrix B. After completion of all 
parts of part A, merge all the results into a new matrix C.

### Java Implementation

### Go Implementation
**Step 1** - Specify the number of CPU cores on the local system being used.
This can be achieved by: 
1. Setting the ``GOMAXPROCS`` environment variable to number of CPU cores required by program.
2. Using the ``runtime package`` that contains the ``GOMAXPROCS()`` method that returns the maximum number of logical CPUs on the local machine.

**Step 2** - Define the ``multiply()`` method to calculate matrices, and the following is a part of the implementation:

```go
func multiply(arguments, ch chan) {
  /*implementation*/
  ch <- - //Sends a value to notify the end of a goroutine.
}
```

**Step 3** - Measure elapsed time using ``Now()`` method managed by the ``time package``, and it returns current time.

```go
start:=time.Now()
/*calculation*/
stop:=time.Now()
```
After that, use the ``Sub()`` method to get the difference:

```go
stop.Sub(start)
```
