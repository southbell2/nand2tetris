package ch07;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		if(args[0].equals("VMtranslator")) {
			//.vm 파일을 입력 받을시에는 입력 받은 파일만을 연다
			if(args[1].substring(args[1].length()-3).equals(".vm")) {
				Parser parser = new Parser(args[1]);
				//CodeWriter의 생성자의 파라미터에 .vm은 없애고 전달
				CodeWriter codeWriter = new CodeWriter(args[1].substring(0, args[1].length()-3));
				
				while(parser.hasMoreCommands()) {
					parser.advance();
					//명령어 분류
					if(parser.commandType() == Parser.C_ARITHMETIC) {
						codeWriter.writerArithmetic(parser.arg1());
					}
					else if(parser.commandType() == Parser.C_POP || parser.commandType() == Parser.C_PUSH) {
						codeWriter.writePushPop(parser.commandType(), parser.arg1(), parser.arg2());
					}
				}
			}
			//디렉터리를 입력으로 받으면 그 디렉터리 안에 있는 모든 .vm 파일들을 연다
			else {
				File dir = new File(args[1]);
				File[] files = dir.listFiles();
				String filePath = dir.getPath() + '/' + dir.getName();
				CodeWriter codeWriter = new CodeWriter(filePath);
				
				//디렉터리에 있는 모든 파일들에 대해 .vm으로 끝나는 파일들을 선택한다
				for(File file : files) {
					if(file.getName().substring((file.getName().length()-3)).equals(".vm")) {
						Parser parser = new Parser(file);
						
						while(parser.hasMoreCommands()) {
							parser.advance();
							//명령어 분류
							if(parser.commandType() == Parser.C_ARITHMETIC) {
								codeWriter.writerArithmetic(parser.arg1());
							}
							else if(parser.commandType() == Parser.C_POP || parser.commandType() == Parser.C_PUSH) {
								codeWriter.writePushPop(parser.commandType(), parser.arg1(), parser.arg2());
							}
						}
					}
				}
			}
		}
	}
}
