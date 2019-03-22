package main

import (
	"os"
	"fmt"
    "time"
    "strconv"
)

var n int

func main(){
    var err error
    n, err = strconv.Atoi(os.Args[1])

    if err != nil {
        fmt.Println("Error occured while taking the argument")
    }

    matrix_a := make([][]int,n);

    for i:=0;i<len(matrix_a);i++{

        matrix_a[i]=make([]int,n);

    }


    for i:=0;i<len(matrix_a);i++{

        for j:=0;j<len(matrix_a[0]);j++{
            matrix_a[i][j] = 1;
        }
    }

    matrix_b := make([][]int,n);

    for i:=0;i<len(matrix_b);i++{

        matrix_b[i]=make([]int,n);

    }


    for i:=0;i<len(matrix_b);i++{

        for j:=0;j<len(matrix_b[0]);j++{
            matrix_b[i][j] = 1;
        }
    }

    defer fmt.Println(multiply(matrix_a,matrix_b));
}

func pmultiply(matrix_a [][] int,matrix_b [][] int,row int,col int) int{

    sum := 0;

    for z:=0;z<len(matrix_a[0]);z++{
        sum = sum + matrix_a[row][z] *  matrix_b[z][col];
    }
    return sum;
}

func multiply(matrix_a [][] int,matrix_b [][] int) ([][] int){

	start := time.Now()

    matrix_c := make([][]int,n);

    for i:=0;i<len(matrix_c);i++{
        matrix_c[i]=make([]int,n);
    }

    
	for i := 0; i < n; i++ {
    	for j := 0; j < n; j++ {
	        matrix_c[i][j] = pmultiply(matrix_a, matrix_b, i, j)
    	}
	}

	stop := time.Now()

	elapsed := stop.Sub(start)

	fmt.Println(elapsed)

    return matrix_c;
}
