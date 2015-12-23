package zombie.zombiescene.algoritmosdemonstruos;


//En el caso de java usamos una clase que representara el pair de C++
public class Node implements Comparable<Node>{
	int first, second;
	public Node( int d , int p ){							//constructor
		this.first = d;
		this.second = p;
	}
	public int compareTo( Node other){				//es necesario definir un comparador para el correcto funcionamiento del PriorityQueue
		if( second > other.second ) return 1;
		if( second == other.second ) return 0;
		return -1;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	
	
	
	@Override
	public String toString(){
		return "Node: "+first+" peso:"+second;
		
	}
	
};
