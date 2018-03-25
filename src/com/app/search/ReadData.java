package com.app.search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadData {

	Node source;
	Node destination;

	public List<String> readFile(String fileName) throws IOException {

		List<String> list = new ArrayList<>();
		BufferedReader br = null;
		FileReader fr = null;
		try {

			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String lineData;
			while ((lineData = br.readLine()) != null) {
				list.add(lineData);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally{
			br.close();
		}
		return list;
	}

	public char[][] convertToArrayAndSetSourceAndDestinationNode(List<String> data) {
		int size = data.size();
		int index = 0;
		char[][] charArray = new char[size][];
		for (String x : data) {
			char[] c = x.toCharArray();
			charArray[index] = c;
			setSourceAndDestinationNode(c, index);
			index++;
		}
		return charArray;
	}

	public void setSourceAndDestinationNode(char[] c, int index) {

		for (int i = 0; i < c.length; i++) {
			if (c[i] == 'S') {
				this.source = new Node(index, i, c[i]);
			} else if (c[i] == 'E') {
				this.destination = new Node(index, i, c[i]);
			}
		}
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node getDestination() {
		return destination;
	}

	public void setDestination(Node destination) {
		this.destination = destination;
	}

}
