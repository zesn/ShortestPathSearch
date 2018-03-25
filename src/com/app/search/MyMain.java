package com.app.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class MyMain {
	private static int columnLength;
	private static int rowLength;
	
	int[][] adjecentCordinates = {{0,-1},{-1,0},{1,0},{0,1}};

	public static void main(String[] args) throws IOException {
		ReadData data = new ReadData();
		
		List<String> dataString = data.readFile(System.getProperty("user.dir")
				+ "/src/resource/TestCase1.txt");

		char[][] t = data.convertToArrayAndSetSourceAndDestinationNode(dataString);
		
		rowLength = t.length;
		columnLength = t[0].length;
		// System.out.println("Destination "+data.getDestination().toString()+" Source "+data.getSource().toString());

		MyMain main = new MyMain();
		System.out.println("Orignal Map");
		main.printMatrix(t, data);
		main.searchPath(t, data.getSource(), data.getDestination(), data);
	}

	private boolean isValid(int row, int col) {
		return (row >= 0) && (row < rowLength) && (col >= 0)
				&& (col < columnLength);
	}

	private void searchPath(char[][] matrix, Node src, Node dst, ReadData data) {

		boolean[][] visitedNodes = new boolean[matrix.length][matrix[0].length];
		// start traversing from source node
		visitedNodes[src.x][src.y] = true;

		Queue<Node> queue = new LinkedList<>();
		Stack<Node> path = new Stack<>();

		List<Node> nodeTraversed = new ArrayList<>();

		queue.add(src);
		path.push(src);

		boolean flag=false;
		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();
			for (int index = 0; index < 4; index++) {				
				int row = currentNode.x + adjecentCordinates[index][0];
				int col = currentNode.y + adjecentCordinates[index][1];

				if (isValid(row, col) && matrix[row][col] != 'W'
						&& !visitedNodes[row][col]) {
					visitedNodes[row][col] = true;
					Node nextNode = new Node(row, col, matrix[row][col]);
					queue.add(nextNode);
					path.push(currentNode);
					if (dst.getX() == nextNode.getX()
							&& dst.getY() == nextNode.getY()) {	
						path.push(nextNode);
						flag=true;
						break;
					}
				}
			}
			if(flag){
				break;
			}
		}
		if(!path.isEmpty()){
			Node lastNode = path.pop();			
			if(!(lastNode.getX()==dst.getX() && lastNode.getY()==dst.getY())){				
				System.out.println("Destination cant be reached");
				return;
			}			
		}		

		Node tempNode = dst, currentSrc = dst;
		nodeTraversed.add(dst);

		while (!path.isEmpty()) {
			tempNode = path.pop();

			if (isNeighbour(currentSrc, tempNode)) {
				nodeTraversed.add(tempNode);
				currentSrc = tempNode;
				if (src.getX() == tempNode.getX()
						&& src.getY() == tempNode.getY()) {
					break;
				}
			}
		}
		//System.out.println("visited nodes size:" + nodeTraversed.size());
		//System.out.println("visited nodes:" + nodeTraversed.toString());

		// ===========================

		replaceVisitedNode(matrix, visitedNodes);
		replacePathTraversed(matrix, nodeTraversed);
		System.out.println("Result Map");
		printMatrix(matrix, data);
	}

	private boolean isNeighbour(Node currentSrc, Node tempNode) {
		if (null != currentSrc || null != tempNode) {

			for (int index = 0; index < 4; index++) {			
				if (((currentSrc.getX() + adjecentCordinates[index][0]) == tempNode.getX())
						&& ((currentSrc.getY() + adjecentCordinates[index][1]) == tempNode
								.getY())) {
					return true;
				}				
			}
		}
		return false;
	}

	public void replaceVisitedNode(char[][] matrix, boolean[][] visitedNodes) {

		for (int i = 0; i < visitedNodes.length; i++) {
			for (int j = 0; j < visitedNodes[i].length; j++) {
				if (visitedNodes[i][j]
						&& (matrix[i][j] != 'S' || matrix[i][j] != 'E')) {
					matrix[i][j] = '\"';
				}
			}
		}
	}

	public void replacePathTraversed(char[][] matrix, List<Node> nodes) {

		for (Node n : nodes) {
			// if(n.getValue() !='S' || n.getValue()!='E')
			matrix[n.getX()][n.getY()] = '*';
		}
	}

	public void printMatrix(char[][] matrix, ReadData data) {
		matrix[data.getSource().getX()][data.getSource().getY()] = 'S';
		matrix[data.getDestination().getX()][data.getDestination().getY()] = 'E';
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {

				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}

	}

}
