package ch06;
import java.util.*;

//SymbolTable 모듈, 해쉬테이블을 이용해서 구현한다
public class SymbolTable {
	
	private HashMap<String, Integer> symbolTable;
	
	//생성자
	public SymbolTable() {
		symbolTable = new HashMap<>();
		
		//predefined symbol 넣기
		this.addEntry("SP", 0);
		this.addEntry("LCL", 1);
		this.addEntry("ARG", 2);
		this.addEntry("THIS", 3);
		this.addEntry("THAT", 4);
		this.addEntry("R0", 0);
		this.addEntry("R1", 1);
		this.addEntry("R2", 2);
		this.addEntry("R3", 3);
		this.addEntry("R4", 4);
		this.addEntry("R5", 5);
		this.addEntry("R6", 6);
		this.addEntry("R7", 7);
		this.addEntry("R8", 8);
		this.addEntry("R9", 9);
		this.addEntry("R10", 10);
		this.addEntry("R11", 11);
		this.addEntry("R12", 12);
		this.addEntry("R13", 13);
		this.addEntry("R14", 14);
		this.addEntry("R15", 15);
		this.addEntry("SCREEN", 16384);
		this.addEntry("KBD", 24576);
	}
	
	//테이블에 symbol 추가
	public void addEntry(String symbol, int address) {
		this.symbolTable.put(symbol, address);
	}
	
	//symbol있는지 확인
	public boolean contains(String symbol) {
		return symbolTable.containsKey(symbol);
	}
	
	//주소 얻기
	public int getAddress(String symbol) {
		return symbolTable.get(symbol);
	}
}
