package ch06;

import java.io.*;
import java.util.*;
//Parser 모듈
public class Parser {
	
	public Scanner sc;
	public String nowString;
	public final static int A_COMMAND = 0;
	public final static int C_COMMAND = 1;
	public final static int L_COMMAND = 2;
	
	//생성자
	public Parser(String fileLocation) {	
		try {	
			File asmFile = new File(fileLocation);
			sc = new Scanner(asmFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//입력에 명령이 더 있는지 확인
	boolean hasMoreCommands() {
		return sc.hasNextLine();
	}
	
	//입력에서 다음 명령을 읽어서 현재 명령으로 만든다
	void advance() {
		if(hasMoreCommands()) {
			nowString = sc.nextLine();
			StringBuilder tempString = new StringBuilder();
			
			//공백 제거하기
			for(int i = 0; i < nowString.length(); i++) {
				if(nowString.charAt(i) != ' ') {
					tempString.append(nowString.charAt(i));
				}
			}
			nowString = tempString.toString();
			
			//주석 제거하기
			for(int i = 0; i < nowString.length(); i++) {
				if(nowString.charAt(i) == '/') {
					nowString = nowString.substring(0, i);
				}
			}
			
			//빈 줄일 경우 건너뛰기
			if(nowString.length() == 0) {
				this.advance();
				return;
			}
			
		}
		else {
			System.out.println("No more command");
		}
	}
	
	//현재 명령의 타입을 반환
	int commandType() {
		switch(nowString.charAt(0)) {
		case '@': return A_COMMAND;
		case '(': return L_COMMAND;
		default : return C_COMMAND;
		}
	}
	
	//A-COMMAND or L-COMMAND에서 기호 또는 10진수를 반환
	String symbol() {
		String returnString = "";
		if(commandType() == A_COMMAND) {
			returnString = nowString.substring(1);
		}
		else if(commandType() == L_COMMAND) {
			returnString = nowString.substring(1, nowString.length()-1);
		}
		
		return returnString;
	}
	
	//dest 연상기호 반환
	String dest() {
		String returnString = "";
		if(commandType() == C_COMMAND) {
			for(int i = 0; i < nowString.length(); i++) {
				if(nowString.charAt(i) == '=') {
					returnString = nowString.substring(0, i);
				}
			}
		}
		
		return returnString;
	}
	
	//comp 연상기호 반환
	String comp() {
		String returnString = "";
		if(commandType() == C_COMMAND) {
			//연상기호의 시작 인덱스를 찾는다
			int start = 0;
			for(int i = 0; i < nowString.length(); i++) {
				if(nowString.charAt(i) == '=') {
					start = i + 1;
					break;
				}
			}
			
			//연상기호의 마지막 인덱스+1을 찾는다
			int end = nowString.length();
			for(int i = 0; i < nowString.length(); i++) {
				if(nowString.charAt(i) == ';') {
					end = i;
					break;
				}
			}
			
			returnString = nowString.substring(start, end);
		}
		
		return returnString;
	}
	
	//jump 연상기호 반환
	String jump() {
		String returnString = "";
		if(commandType() == C_COMMAND) {
			for(int i = 0; i < nowString.length(); i++) {
				if(nowString.charAt(i) == ';') {
					returnString = nowString.substring(i+1);
					break;
				}
			}
		}
		
		return returnString;
	}
}
