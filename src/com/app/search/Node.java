package com.app.search;

public class Node {
	
	int x;
	int y;
	char value;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
	public Node(int x, int y, char value) {
		super();
		this.x = x;
		this.y = y;
		this.value = value;
	}
	@Override
	public String toString() {
		return "(" + x +","+ y+ ")";
	}	

}
