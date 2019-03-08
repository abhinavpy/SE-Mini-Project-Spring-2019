package main

import "fmt"
import "sync"
import "time"


func main(){

    matrix_a := make([][]int,1000);

    for i:=0;i<len(matrix_a);i++{

        matrix_a[i]=make([]int,1000);

    }


    for i:=0;i<len(matrix_a);i++{

        for j:=0;j<len(matrix_a[0]);j++{
            matrix_a[i][j] = 1;
        }
    }

    matrix_b := make([][]int,1000);

    for i:=0;i<len(matrix_b);i++{

        matrix_b[i]=make([]int,1000);

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

    matrix_c := make([][]int,1000);

    for i:=0;i<len(matrix_c);i++{
        matrix_c[i]=make([]int,1000);
    }

    

    var wg sync.WaitGroup
	for i := 0; i < 1000; i++ {
    	for j := 0; j < 1000; j++ {
	        wg.Add(1) // Tell the WaitGroup to wait for another thing.
	        go func(i, j int) {
	            matrix_c[i][j] = pmultiply(matrix_a, matrix_b, i, j)
	            wg.Done() // Tell it that we're done.
	        }(i, j)
    	}
	}


	wg.Wait()	

	stop := time.Now()

	elapsed := stop.Sub(start)

	fmt.Println(elapsed)

    return matrix_c;
}
