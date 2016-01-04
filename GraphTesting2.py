import random
import Queue


'''
	Generates a weighted graph with random vertices, edges, and weights.

	Parameters: size	-	the size of the graph

	Returns: a random graph
'''
def randomGraph(size):
	
	# initialize the adjacency matrix and zero every edge 
	matrix = []
	for i in range(size):
		matrix.append([0] * size)

	vertices = list(range(size))

	for i in range(1, size):
		sampleSize = random.randint(1, i)
		random.shuffle(vertices)
		sample = vertices[0:sampleSize]

		# assign each vertex in the sample a weighted connection to vertex i
		for vertex in sample:
			weight = random.randint(10, 100)
			matrix[i][vertex] = weight
			matrix[vertex][i] = weight

	return matrix

'''
	Performs a breadth first search on a graph represented by an adjacency matrix.
	A vertex in the graph is chosen at random for the starting point of the BFS.
	Returns the total weight of the tree formed by the BFS.

	Parameters: graph    -    the graph for the BFS (as an adjacency matrix)

	Returns: the total weight of the spanning tree formed by the search
'''
def BFS(graph):
	pathTotal = 0

	vertices = list(range(len(graph))) # list from 0 ... n-1
	start = 1 #random.choice(vertices) # choose a random vertex
	visited = [False] * len(graph) # keep track of each visited vertex

	Q = Queue.Queue()
	Q.put(start)
	visited[start] = True

	while not Q.empty():
		vertex = Q.get() # get the next vertex in the queue
		print(vertex)
		neighbors = findNeighbors(graph, vertex)
		for i in neighbors:
			if not visited[i]:
				# if we get to this point, then we must have made it to the next vertex
				pathTotal += graph[vertex][i]
				visited[i] = True # mark visited and add to queue
				print(visited)
				Q.put(i)

	return pathTotal

'''
	Constructs a minimum spanning tree based on a graph represented by an adjacency
	matrix using Prim's MST algorithm. Returns the total weight of the minimum spanning
	tree.

	Parameters: graph    -    the graph for which an MST will be constructed

	Returns: the total weight of the MST formed by Prim's algorithm

'''
def primsMST(graph):
	size = len(graph)
	pathTotal = 0

	vertices = [0] * size
	weights = [float("inf")] * size
	inMST = [False] * size

	inMST[0] = True
	bestNeighbor = 0

	for i in range(size - 1):
		
		minimumWeight = float("inf")

		for j in range(size):
			if not inMST[j]:
				weight = graph[bestNeighbor][j]
				if weight != 0 and weight < weights[j]:
					vertices[j] = bestNeighbor
					weights[j] = weight

		for j in range(size):
			if not inMST[j]:
				if weights[j] < minimumWeight:
					bestNeighbor = j
					minimumWeight = weights[j]

		if bestNeighbor != 0:
			print(weights[bestNeighbor])
			pathTotal += weights[bestNeighbor]
			inMST[bestNeighbor] = True

	return pathTotal

'''
	Finds all the neighbors of a vertex in an adjacency matrix.

	Parameters: graph    -    the graph as an adjacency matrix
				vertex   -    a vertex in the graph (as a number)

	Returns: a list of all the neighbors of the selected vertex.
'''
def findNeighbors(graph, vertex):
	neighbors = []
	for i in range(len(graph)):
		if graph[vertex][i] != 0:
			neighbors.append(i)
	print(neighbors)
	return neighbors

def main():
	testMat = [[0, 15, 0, 7, 10, 0], [15, 0, 9, 11, 0, 9], [0, 9, 0, 0, 12, 7], [7, 11, 0, 0, 8, 14], [10, 0, 12, 8, 0, 8], [0, 9, 7, 14, 8, 0]]
	print(BFS(testMat))

main()
