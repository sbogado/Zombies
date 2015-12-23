package zombie.zombiescene.algoritmosdemonstruos;

import java.util.ArrayList;
import java.util.List;


public class ColaDeNodosParaDijsktra {

	List<Node> nodes;
	int elements = 0;
	
	public ColaDeNodosParaDijsktra(){
		this.nodes = new ArrayList<Node>();
		
		for(int x = 0; x < 700; x++){
			this.nodes.add(new Node(-1, -1));
		}
	}
	
	public void addNodeData(int fisrt, int second){
		for(Node nodo : this.nodes){
			if(nodo.getFirst() == -1){
				nodo.setFirst(fisrt);
				nodo.setSecond(second);
				elements = elements + 1;
				break;
			}
		}
	}
	
	public Node getNodoConMenorPeso(Node node){
		Node nodoConMenorPeso =  this.nodes.get(0);
		for(Node nodo : this.nodes){
			if(nodoConMenorPeso.getFirst() == -1 && nodo.getFirst() != -1){
				nodoConMenorPeso = nodo;
			}
			else{
				if(nodoConMenorPeso.getFirst() != -1 && nodo.getFirst() != -1 && nodoConMenorPeso.getSecond() > nodo.getSecond()){
					nodoConMenorPeso = nodo;
				}
			}

		}
		node.setFirst(nodoConMenorPeso.getFirst());
		node.setSecond(nodoConMenorPeso.getSecond());
		nodoConMenorPeso.setFirst(-1);
		nodoConMenorPeso.setSecond(-1);
		elements = elements - 1;
		
		return node;
	}

	public boolean isEmpty() {
		return elements <= 0;
	}
	
}
