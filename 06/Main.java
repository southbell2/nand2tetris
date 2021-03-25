package ch06;
import java.io.*;

public class Main {
	public static StringBuilder sb = new StringBuilder();	
	
	//10진수를 입력하면 15자리 2진수 스트링을 반환하는 메소드
	static String makeBinaryString(int decimal) {
		StringBuilder sb = new StringBuilder();
		String str = Integer.toBinaryString(decimal);
		
		for(int i = str.length(); i < 15; i++) {
			sb.append('0');
		}
		
		sb.append(str);
		
		return sb.toString();
	}
	
	//어셈블러
	static void assembler(String fileName) {
		//파일 읽기
		//1패스, 레이블을 기호 테이블에 삽입한다
		Parser parser = new Parser(fileName);	
		SymbolTable symbolTable = new SymbolTable();
		//ROM 주소를 카운트할 변수
		int romAddress = 0;
		
		while(parser.hasMoreCommands()) {
			//다음 명령을 얻는다
			parser.advance();
			
			//A 명령어이거나 C 명령어 일때 rom 주소 1 증가
			if(parser.commandType() == Parser.A_COMMAND || parser.commandType() == Parser.C_COMMAND) {
				romAddress++;
			}
			//L 명령어이면 symboltable에 기호 삽입
			else {
				symbolTable.addEntry(parser.symbol(), romAddress);
			}
		}
		
		//2패스
		parser = new Parser(fileName);
		int ramAddress = 16;

		while (parser.hasMoreCommands()) {
			// 다음 명령을 얻는다
			parser.advance();

			// A 명령어일때
			if (parser.commandType() == Parser.A_COMMAND) {
				String aSymbol = parser.symbol();
				//symbol이 숫자면
				if('0' <= aSymbol.charAt(0) && aSymbol.charAt(0) <= '9') {
					sb.append("0").append(makeBinaryString(Integer.parseInt(aSymbol))).append('\n');
				}
				//숫자가 아니면
				else {
					//기호테이블에 존재
					if(symbolTable.contains(aSymbol)) {
						aSymbol = Integer.toString(symbolTable.getAddress(aSymbol));
						sb.append("0").append(makeBinaryString(Integer.parseInt(aSymbol))).append('\n');
					}
					//기호테이블에 존재하지 않을때
					else {
						symbolTable.addEntry(aSymbol, ramAddress);
						sb.append("0").append(makeBinaryString(ramAddress)).append('\n');
						ramAddress++;
					}
				}
			}
			// C 명령어일때
			else if (parser.commandType() == Parser.C_COMMAND) {
				// comp dest jump 순으로 들어간다
				sb.append("111").append(Code.comp(parser.comp())).append(Code.dest(parser.dest()))
						.append(Code.jump(parser.jump())).append('\n');
			}
		}
		
		//파일에 출력하기
		try {
			String hackFileName = fileName.substring(0, fileName.length()-3);
			File file = new File(hackFileName + "hack");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			if(file.isFile() && file.canWrite()) {
				writer.write(sb.toString());
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//StringBuilder 초기화
		sb.setLength(0);
	}

	public static void main(String[] args) {	
		String name = args[1];
		assembler(name);
	}
}

