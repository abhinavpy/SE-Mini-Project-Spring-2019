package main

import "fmt"
import "time"
//import "math"
import "sync"

func main() {
	matrix_a := [][]float32{{0.5, 0.5, 0}, {0.5, 0, 1}, {0, 0.5, 0}}

	//matrix_a := [][]float32{{0.02,0.02,0.31,0.02,0.02,0.02,0.02},{0.02,0.45,0.02,0.02,0.02,0.02,0.02},{0.88,0.45,0.31,0.02,0.02,0.02,0.02},{0.02,0.02,0.31,0.45,0.02,0.02,0.31},{0.02,0.02,0.02,0.45,0.02,0.02,0.31},{0.02,0.02,0.02,0.02,0.02,0.45,0.02},{0.02,0.02,0.02,0.02,0.88,0.45,0.31}}

	num_of_nodes := 3
	epsilon := 0.00001

	fmt.Println("The graph is as follows:")
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			fmt.Printf("a[%d][%d] = %f\n", i, j, matrix_a[i][j])
		}
	}

	fmt.Println("The initial values assigned to each page are as follows:")
	matrix_b := []float32{0.33333, 0.33333, 0.33333}

	for i := 0; i < 3; i++ {
		fmt.Printf("b[%d] = %f\n", i, matrix_b[i])
	}

	//matrix_d := [4][3]float32{{0.5, 0.5, 0}, {0.5, 0, 1}, {0, 0.5, 0}, {0, 5, 1.4}}
	//fmt.Println(len(matrix_d))

	fmt.Println("this is epsilon:")
	fmt.Println(epsilon)

	fmt.Println("this is the number of nodes:")
	fmt.Println(num_of_nodes)

	fmt.Println(multiply(matrix_a, matrix_b))
}

func pmultiply(matrix_a [][]float32, matrix_b []float32, row int) float32 {
	var sum float32
	sum = 0
	for z := 0; z < len(matrix_a[0]); z++ {
		sum += matrix_a[row][z] * matrix_b[z]
		//fmt.Println("matrix_a[",row,"][",z,"] = ",matrix_a[row][z],"  matrix_b[",z,"] = ",matrix_b[z]," sum = ",matrix_a[row][z] * matrix_b[z])
	}
	//fmt.Println("sum at matrix_b[%d] is %f", row, sum)
	return sum
}

func multiply(matrix_a [][]float32, matrix_b []float32) []float32 {

	start := time.Now()
	
	countiter := 0

	checkprev := make([]float32, len(matrix_b))
	seconds := 0
	extraSum := time.Duration(seconds)

	for true {

		countiter += 1
		//fmt.Println("This is iteration", countiter)
		temp := make([]float32, len(matrix_b))

		var wg sync.WaitGroup

		for i := 0; i < len(matrix_b); i++ {
			wg.Add(1) // Tell the WaitGroup to wait for another thing.
			go func(i int) {
				temp[i] = pmultiply(matrix_a, matrix_b, i)
				wg.Done() // Tell it that we're done.
			}(i)
		}

		wg.Wait()	

		extraTimeStart := time.Now()

		for k := 0; k < len(matrix_b); k++ {
			matrix_b[k] = temp[k]
		}

		x := float32(2)

		for i := 0; i < len(matrix_b); i++ {
			if checkprev[i] - matrix_b[i] < x {
				x = checkprev[i] - matrix_b[i]
			}
		}

		if countiter >= 55 {
			break
		}

		for p := 0; p < len(matrix_b); p++ {
			checkprev[p] = matrix_b[p]
		}

		extraTimeStop := time.Now()

		extraElapsed := extraTimeStop.Sub(extraTimeStart)

		extraSum += extraElapsed

		//fmt.Println("Matrix b is ", matrix_b)
	}

	// temp := make([]float32, 3)

	// var wg sync.WaitGroup

	// for i := 0; i < 3; i++ {
	//     wg.Add(1) // Tell the WaitGroup to wait for another thing.
	//     go func(i int) {
	//         temp[i] = pmultiply(matrix_a, matrix_b, i)
	//         wg.Done() // Tell it that we're done.
	//     }(i)
	// }

	// wg.Wait()	

	// for k := 0; k < 3; k++ {
	// 	matrix_b[k] = temp[k]
	// }

	stop := time.Now()

	elapsed := stop.Sub(start)

	//fmt.Println("The extraSum time is :", extraSum)

	//fmt.Println("Elapsed time is ", elapsed)

	elapsed = elapsed - extraSum

	fmt.Println("the final time taken is: ")

	fmt.Println(elapsed)

	fmt.Println("The number of matrix multiplications performed are: ")
	fmt.Println(countiter)

	return matrix_b
}
