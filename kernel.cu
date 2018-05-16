
#include <stdio.h>
#include<iostream>
#include "cuda_runtime.h"
#include "device_launch_parameters.h"
using namespace std;

#define N 	4
#define INF 9999

__global__ void Floyd(int** graph, int k)
{
	int i = threadIdx.x;
	for(int j = 0; j <= N; j++){
		if (graph[i][k] + graph[k][j] < graph[i][j])
			graph[i][j] = graph[i][k] + graph[k][j];
	}
}


int main()
{
	int h_graph[N][N] = {
		0,5,9999, 10,
		9999, 0,3, 9999,
		9999, 9999, 0,1,
		9999, 9999, 9999,0
	};
	
	size_t size = N * N * sizeof(int);

	int** d_graph;
	
	cudaMalloc(&d_graph, size);

	cudaMemcpy(d_graph, h_graph, size, cudaMemcpyHostToDevice);

	int numBlocks = 1;
	dim3 threadsPerBlock(N);

	for (int k = 0; k < N; k++) 
	{
		Floyd<<<numBlocks, threadsPerBlock>>>(d_graph, k);
	}

	cudaMemcpy(h_graph, d_graph, size, cudaMemcpyDeviceToHost);

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			if (h_graph[i][j] == INF){
				cout << "INF ";
			}
			else{
				cout << h_graph[i][j] << " ";
			}
		}
		cout << endl;
	}
	
	cudaFree(d_graph);
}