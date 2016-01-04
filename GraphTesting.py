

from random import*
import time

class vertex:
	#Create a vertex
	def __init__(self, value, neighbours = []):
		self.neighbours = neighbours
		self.value = value
	
class GraphMaster:
		
	def BFS(self, start, end, myList):
		#Gets the shortest path between the start and end values, and also checks which one is the largest
		q = [ ]				#Create and empty queue 
		path = [start]
		q.append(path)			#Append the start vertex to q
		largestDistance = 0
		while(len(q) > 0):		
			x = q[0]
			q.remove(x)
			last_node = x[len(x)-1]
			if last_node == end:				#If we reach our ending node, print the valid path and see if it is the longest path
				#print "VALID PATH : ",x
				if len(x) > largestDistance:
					largestDistance = len(x)
			for edge in myList[last_node]:
				if edge not in x:
					new_path = []
					new_path = x + [edge]
					q.append(new_path)
		
		return largestDistance
		
	def generateTree(self, value, temp):
		#My variable name for my array became temp during trial and error phases, so it stuck around.  It is the list of neighbouring verticies 
		temp = [ ]
		zero = []
		
		#Circulate through the number of verticies (value)
		for i in range(1, value):
			p = randint(0, i-1)				# chooses one random vertex
			#print "Parent " ,i, "=", p
			temp.append([p])				
			temp[p-1].append(i)				#Connect the vertex to it's neighbour
			temp[i-1] = [p]
			if(p == 0):					#if the neighbour is zero, it is added to it's own array
				zero.append(i)
		temp.insert(0, zero)

		return temp
		
	def printOut(self, myList):
		#Print out the neighbour list 
		print
		print("List of neighbours:")
		print "Vertex:	 Neighbours: "
		
		for i in range(0, len(myList)):
			print i, "         :   ", myList[i]			#Print each value and corresponding neighbours
		
	def addEdges(self, n, myList):
		#Adds new edges to the tree as described in the instructions

		# n = desired number of new edges
		for i in range(1, n):
			v1 = randint(0, n-1)
			v2 = randint(0, n-1)
			if((v1 != v2) and (v2 not in myList[v1-1])):
				myList[v1-1].append(v2)
				myList[v2-1].append(v1)
				
		return myList


# ************************************
#		MAIN PROGRAM
# ************************************
	
i = 0	
n = 8						# Number of verticies
tree = []
edges = []
pathLengths = [ ]				#Holds the values of path lengths from 0 to every vertex
largestPath = 0
d = 0
d2 = 0
graph = GraphMaster()			#Graph object
tree = graph.generateTree(n, tree)
#Print out the neighbour list 
graph.printOut(tree)
#Get the length of each path from 0 to i
while(i < n):
	pathLengths.append(graph.BFS(0, i, tree))
	i +=1
print "Length of each path in tree: ",pathLengths
#Find the largest path, which is the diameter
largestPath = max(pathLengths)
print "Diameter of tree = ", largestPath

print "Now adding additional edges."	
edges = graph.addEdges(n, tree)			#Create a graph by adding random edges to the tree
pathLengths2 = [ ]						#Holds the values of path lengths from 0 to every vertex
i = 0
largestPathE = 0
graph.printOut(edges)
while(i < n):
	pathLengths2.append(graph.BFS(0, i, edges))
	i +=1
print "Length of each path in graph: ", pathLengths2
#Find the largest path, which is the diameter
largestPathE = max(pathLengths2)

print "Diameter of graph = ", largestPathE
print
#   *************
#|  EXPERIMENT   |
#   *************

print "Begining Experiment..."
print
k = 10								#Using a k value of 10 took too long
nVals = [100, 200, 400, 800, 1600]			#Each value in nVals represents a number of verticies

for val in nVals:
	
	treeDSum = 0 						#Stores the sum of the diameters
	graphDSum = 0
	for test in range(k):
		m = 0
		largestPath = 0
		
		tree = [ ]
		edges = [ ]
		pathLengths = [ ]

		tree = graph.generateTree(val, tree)			#Generate a tree vith val number of verticies
		#Get the length of each path from 0 to m
		while(m < len(tree)):
			pathLengths.append(graph.BFS(0, m, tree))
			m+=1
		
		#Find the largest path, which is the diameter
		largestPath = max(pathLengths)
		treeDSum += largestPath
		#Create a graph by adding random edges to the tree
		edges = graph.addEdges(n, tree)
		pathLengths2 = [ ]						#Holds the values of path lengths from 0 to every vertex
		i = 0
		largestPathE = 0
		
		while(i < n):
			pathLengths2.append(graph.BFS(0, i, edges))
			i +=1
		
		largestPathE = max(pathLengths2)
		graphDSum += largestPathE
	#print the data
	print "Number of verticies: ", val
	print "Average tree diameter = ", (treeDSum * 1.0) / k
	print "Average graph diameter = ", (graphDSum * 1.0) / k
	print
	
	
	
	
	