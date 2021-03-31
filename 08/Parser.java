package ch07;
import java.io.*;
import java.util.*;

//VM의 Parser 모듈, 구문 분석을 한다
public class Parser {
	
	public Scanner sc;
	private String command;
	private String arg1;
	private String arg2;
	
	public final static int C_ARITHMETIC = 0;
	public final static int C_PUSH = 1;
	public final static int C_POP = 2;
	public final static int C_LABEL = 3;
	public final static int C_GOTO = 4;
	public final static int C_IF = 5;
	public final static int C_FUNCTION = 6;
	public final static int C_RETURN = 7;
	public final static int C_CALL = 8;
	
	//생성자
	public Parser(String filePath) {
		try {
			File vmFile = new File(filePath);
			sc = new Scanner(vmFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Parser(File vmFile) {
		try {
			sc = new Scanner(vmFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	boolean hasMoreCommands() {
		return sc.hasNextLine();
	}

	//입력에서 다음 명령을 읽어서 현재 명령으로 만든다
	void advance() {
		if(hasMoreCommands()) {
			String nowCommand = sc.nextLine();		
			//주석 제거하기
			for(int i = 0; i < nowCommand.length(); i++) {
				if(nowCommand.charAt(i) == '/') {
					nowCommand = nowCommand.substring(0, i);
				}
			}		
			//빈 줄일 경우 건너뛰기
			if(nowCommand.length() == 0) {
				this.advance();
				return;
			}

			//현재 받은 문자열을 command,arg1,arg2로 분리하기
			StringTokenizer st = new StringTokenizer(nowCommand);
			command = st.nextToken();
			if(st.hasMoreTokens()) arg1 = st.nextToken();
			else arg1 = "";
			if(st.hasMoreTokens()) arg2 = st.nextToken();
			else arg2 = "";
		}
		else {
			System.out.println("No more command");
		}
	}
	
	//명령의 타입을 반환한다
	int commandType() {
		if(command.equals("add") || command.equals("sub") || command.equals("neg") ||
		   command.equals("eq") || command.equals("gt") || command.equals("lt") ||
		   command.equals("and") || command.equals("or") || command.equals("not")) {
			return C_ARITHMETIC;
		}
		else if(command.equals("push")) {
			return C_PUSH;
		}
		else if(command.equals("pop")) {
			return C_POP;
		}
		else if(command.equals("label")) {
			return C_LABEL;
		}
		else if(command.equals("goto")) {
			return C_GOTO;
		}
		else if(command.equals("if-goto")) {
			return C_IF;
		}
		else if(command.equals("function")) {
			return C_FUNCTION;
		}
		else if(command.equals("return")) {
			return C_RETURN;
		}
		else if(command.equals("call")) {
			return C_CALL;
		}
		else return -1;
	}
	
	//arg1
	String arg1() {
		if(commandType() == C_ARITHMETIC) {
			return command;
		}
		
		return arg1;
	}
	
	//arg2
	int arg2() {
		return Integer.parseInt(arg2);
	}
}
