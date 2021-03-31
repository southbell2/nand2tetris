package ch07;

import java.io.*;

public class CodeWriter {
	private File asmFile;
	private String asmFileName;
	//어셈블리코드로 번역된 명령어들을 저장할 StringBuilder
	private StringBuilder commands = new StringBuilder();
	private int conditionCnt = 0;
	
	//생성자
	public CodeWriter(String filePath) {
		//asmFileName은 static의 pop,push 연산을 할때 쓸 파일이름
		asmFileName = filePath;
		for(int i = filePath.length()-1; 0 <= i; i--) {
			if(filePath.charAt(i) == '/') {
				asmFileName = filePath.substring(i+1);
				break;
			}
		}
		//filePath에는 .asm이 붙어있지 않아서 .asm을 붙여서 파일을 만든다
		asmFile = new File(filePath + ".asm");
	}
	
	//산술 및 논리 연산
	void writerArithmetic(String segment) throws IOException {
		if(segment.equals("add")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
			
			commands.append("@SP").append('\n');
			commands.append("AM=M-1").append('\n');
			commands.append("D=M").append('\n');
			commands.append("A=A-1").append('\n');
			commands.append("M=D+M").append('\n');
			
			if(asmFile.isFile() && asmFile.canWrite()) {
				writer.write(commands.toString());
				writer.close();
			}	
			commands.setLength(0);
		}
		else if(segment.equals("sub")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
			
			commands.append("@SP").append('\n');
			commands.append("AM=M-1").append('\n');
			commands.append("D=M").append('\n');
			commands.append("A=A-1").append('\n');
			commands.append("M=M-D").append('\n');
			
			if(asmFile.isFile() && asmFile.canWrite()) {
				writer.write(commands.toString());
				writer.close();
			}	
			commands.setLength(0);
		}
		else if(segment.equals("neg")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
			
			commands.append("@SP").append('\n');
			commands.append("A=M-1").append('\n');
			commands.append("M=-M").append('\n');
			
			if(asmFile.isFile() && asmFile.canWrite()) {
				writer.write(commands.toString());
				writer.close();
			}	
			commands.setLength(0);
		}
		else if(segment.equals("eq")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
			
			commands.append("@SP").append('\n');
			commands.append("AM=M-1").append('\n');
			commands.append("D=M").append('\n');
			commands.append("A=A-1").append('\n');
			commands.append("D=M-D").append('\n');
			commands.append("M=-1").append('\n');
			commands.append("@EQ").append(Integer.toString(conditionCnt)).append('\n');
			commands.append("D;JEQ").append('\n');
			commands.append("@SP").append('\n');
			commands.append("A=M-1").append('\n');
			commands.append("M=0").append('\n');
			commands.append("(EQ").append(Integer.toString(conditionCnt)).append(')').append('\n');
			
			conditionCnt++;
			
			if(asmFile.isFile() && asmFile.canWrite()) {
				writer.write(commands.toString());
				writer.close();
			}	
			commands.setLength(0);
		}
		else if(segment.equals("gt")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
			
			commands.append("@SP").append('\n');
			commands.append("AM=M-1").append('\n');
			commands.append("D=M").append('\n');
			commands.append("A=A-1").append('\n');
			commands.append("D=M-D").append('\n');
			commands.append("M=-1").append('\n');
			commands.append("@GT").append(Integer.toString(conditionCnt)).append('\n');
			commands.append("D;JGT").append('\n');
			commands.append("@SP").append('\n');
			commands.append("A=M-1").append('\n');
			commands.append("M=0").append('\n');
			commands.append("(GT").append(Integer.toString(conditionCnt)).append(')').append('\n');
			
			conditionCnt++;
			
			if(asmFile.isFile() && asmFile.canWrite()) {
				writer.write(commands.toString());
				writer.close();
			}	
			commands.setLength(0);
		}
		else if(segment.equals("lt")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
			
			commands.append("@SP").append('\n');
			commands.append("AM=M-1").append('\n');
			commands.append("D=M").append('\n');
			commands.append("A=A-1").append('\n');
			commands.append("D=M-D").append('\n');
			commands.append("M=-1").append('\n');
			commands.append("@LT").append(Integer.toString(conditionCnt)).append('\n');
			commands.append("D;JLT").append('\n');
			commands.append("@SP").append('\n');
			commands.append("A=M-1").append('\n');
			commands.append("M=0").append('\n');
			commands.append("(LT").append(Integer.toString(conditionCnt)).append(')').append('\n');
			
			conditionCnt++;
			
			if(asmFile.isFile() && asmFile.canWrite()) {
				writer.write(commands.toString());
				writer.close();
			}	
			commands.setLength(0);
		}
		else if(segment.equals("and")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
			
			commands.append("@SP").append('\n');
			commands.append("AM=M-1").append('\n');
			commands.append("D=M").append('\n');
			commands.append("A=A-1").append('\n');
			commands.append("M=D&M").append('\n');
			
			if(asmFile.isFile() && asmFile.canWrite()) {
				writer.write(commands.toString());
				writer.close();
			}	
			commands.setLength(0);
		}
		else if(segment.equals("or")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
			
			commands.append("@SP").append('\n');
			commands.append("AM=M-1").append('\n');
			commands.append("D=M").append('\n');
			commands.append("A=A-1").append('\n');
			commands.append("M=D|M").append('\n');
			
			if(asmFile.isFile() && asmFile.canWrite()) {
				writer.write(commands.toString());
				writer.close();
			}	
			commands.setLength(0);
		}
		else if(segment.equals("not")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
			
			commands.append("@SP").append('\n');
			commands.append("A=M-1").append('\n');
			commands.append("M=!M").append('\n');
			
			if(asmFile.isFile() && asmFile.canWrite()) {
				writer.write(commands.toString());
				writer.close();
			}	
			commands.setLength(0);
		}
	}
	
	//push, pop 연산
	void writePushPop(int command, String segment, int index) throws IOException {
		//push
		if(command == Parser.C_PUSH) {
			if(segment.equals("constant")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append('@').append(Integer.toString(index)).append('\n');
				commands.append("D=A").append('\n');
				commands.append("@SP").append('\n');
				commands.append("A=M").append('\n');
				commands.append("M=D").append('\n');
				commands.append("@SP").append('\n');
				commands.append("M=M+1").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);
			}
			else if(segment.equals("local")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append("@LCL").append('\n');
				commands.append("D=M").append('\n');
				commands.append('@').append(Integer.toString(index)).append('\n');
				commands.append("A=D+A").append('\n');
				commands.append("D=M").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M+1").append('\n');
				commands.append("A=A-1").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);			
			}
			else if(segment.equals("argument")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append("@ARG").append('\n');
				commands.append("D=M").append('\n');
				commands.append('@').append(Integer.toString(index)).append('\n');
				commands.append("A=D+A").append('\n');
				commands.append("D=M").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M+1").append('\n');
				commands.append("A=A-1").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);			
			}
			else if(segment.equals("this")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append("@THIS").append('\n');
				commands.append("D=M").append('\n');
				commands.append('@').append(Integer.toString(index)).append('\n');
				commands.append("A=D+A").append('\n');
				commands.append("D=M").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M+1").append('\n');
				commands.append("A=A-1").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);			
			}
			else if(segment.equals("that")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append("@THAT").append('\n');
				commands.append("D=M").append('\n');
				commands.append('@').append(Integer.toString(index)).append('\n');
				commands.append("A=D+A").append('\n');
				commands.append("D=M").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M+1").append('\n');
				commands.append("A=A-1").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);			
			}
			else if(segment.equals("temp")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append("@R5").append('\n');
				commands.append("D=A").append('\n');
				commands.append('@').append(Integer.toString(index)).append('\n');
				commands.append("A=D+A").append('\n');
				commands.append("D=M").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M+1").append('\n');
				commands.append("A=A-1").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);			
			}
			else if(segment.equals("pointer")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append("@R3").append('\n');
				commands.append("D=A").append('\n');
				commands.append('@').append(Integer.toString(index)).append('\n');
				commands.append("A=D+A").append('\n');
				commands.append("D=M").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M+1").append('\n');
				commands.append("A=A-1").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);			
			}
			else if(segment.equals("static")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append('@').append(asmFileName).append('.').append(Integer.toString(index)).append('\n');
				commands.append("D=M").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M+1").append('\n');
				commands.append("A=A-1").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);			
			}
		}
		//pop
		else {
			if(segment.equals("local")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append("@LCL").append('\n');
				commands.append("D=M").append('\n');
				commands.append('@').append(Integer.toString(index)).append('\n');
				commands.append("D=D+A").append('\n');
				commands.append("@R13").append('\n');
				commands.append("M=D").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M-1").append('\n');
				commands.append("D=M").append('\n');
				commands.append("@R13").append('\n');
				commands.append("A=M").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);
			}
			else if(segment.equals("argument")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append("@ARG").append('\n');
				commands.append("D=M").append('\n');
				commands.append('@').append(Integer.toString(index)).append('\n');
				commands.append("D=D+A").append('\n');
				commands.append("@R13").append('\n');
				commands.append("M=D").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M-1").append('\n');
				commands.append("D=M").append('\n');
				commands.append("@R13").append('\n');
				commands.append("A=M").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);
			}
			else if(segment.equals("this")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append("@THIS").append('\n');
				commands.append("D=M").append('\n');
				commands.append('@').append(Integer.toString(index)).append('\n');
				commands.append("D=D+A").append('\n');
				commands.append("@R13").append('\n');
				commands.append("M=D").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M-1").append('\n');
				commands.append("D=M").append('\n');
				commands.append("@R13").append('\n');
				commands.append("A=M").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);
			}
			else if(segment.equals("that")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append("@THAT").append('\n');
				commands.append("D=M").append('\n');
				commands.append('@').append(Integer.toString(index)).append('\n');
				commands.append("D=D+A").append('\n');
				commands.append("@R13").append('\n');
				commands.append("M=D").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M-1").append('\n');
				commands.append("D=M").append('\n');
				commands.append("@R13").append('\n');
				commands.append("A=M").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);
			}
			else if(segment.equals("temp")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append("@R5").append('\n');
				commands.append("D=A").append('\n');
				commands.append('@').append(Integer.toString(index)).append('\n');
				commands.append("D=D+A").append('\n');
				commands.append("@R13").append('\n');
				commands.append("M=D").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M-1").append('\n');
				commands.append("D=M").append('\n');
				commands.append("@R13").append('\n');
				commands.append("A=M").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);
			}
			else if(segment.equals("pointer")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append("@R3").append('\n');
				commands.append("D=A").append('\n');
				commands.append('@').append(Integer.toString(index)).append('\n');
				commands.append("D=D+A").append('\n');
				commands.append("@R13").append('\n');
				commands.append("M=D").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M-1").append('\n');
				commands.append("D=M").append('\n');
				commands.append("@R13").append('\n');
				commands.append("A=M").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);
			}
			else if(segment.equals("static")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
				
				commands.append('@').append(asmFileName).append('.').append(Integer.toString(index)).append('\n');
				commands.append("D=A").append('\n');
				commands.append("@R13").append('\n');
				commands.append("M=D").append('\n');
				commands.append("@SP").append('\n');
				commands.append("AM=M-1").append('\n');
				commands.append("D=M").append('\n');
				commands.append("@R13").append('\n');
				commands.append("A=M").append('\n');
				commands.append("M=D").append('\n');
				
				if(asmFile.isFile() && asmFile.canWrite()) {
					writer.write(commands.toString());
					writer.close();
				}			
				commands.setLength(0);
			}
		}
	}
}
