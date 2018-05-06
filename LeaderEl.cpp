
#include "stdafx.h"
#include<time.h>
#include<mpi.h>
#include<iostream>

using namespace std;
int main(int argc, char** argv)
{
	int num_procs, proc_id, leader;
	MPI_Status status;
	int i;
	int leader_received, max_priority_received;
	int leader_to_send, max_priority_to_send;
	MPI_Init(&argc, &argv);

	MPI_Comm_size(MPI_COMM_WORLD, &num_procs);
	MPI_Comm_rank(MPI_COMM_WORLD, &proc_id);

	leader = proc_id;

	srand(time(NULL) + proc_id);
	int max_priority = rand() % 1000;

	for (i = 0; i < num_procs; i++)
	{
		leader_to_send = leader;
		max_priority_to_send = max_priority;

		MPI_Send(&leader_to_send, 2, MPI_INT, (proc_id + 1) % num_procs, 0, MPI_COMM_WORLD);
		MPI_Send(&max_priority_to_send, 2, MPI_INT, (proc_id + 1) % num_procs, 0, MPI_COMM_WORLD);

		MPI_Recv(&leader_received, 1, MPI_INT, (num_procs + proc_id - 1) % num_procs, 0, MPI_COMM_WORLD, &status);
		MPI_Recv(&max_priority_received, 1, MPI_INT, (num_procs + proc_id - 1) % num_procs, 0, MPI_COMM_WORLD, &status);

		printf("Process no %d received as leader % with max priority %d ", proc_id, leader_received, max_priority_received);

		if (max_priority_received > max_priority)
		{
			leader = leader_received;
			max_priority = max_priority_received;
		}
		else
		{
			if (max_priority_received == max_priority)
			{
				if (leader_received > proc_id)
				{
					leader = leader_received;
					max_priority = max_priority_received;
				}
			}
		}
	}

	printf("Finally, process no %d has as leader % with max priority %d ", proc_id, leader, max_priority);

	MPI_Finalize();
	return 0;
}