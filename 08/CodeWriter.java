package ch07;

import java.io.*;

public class CodeWriter {
	private File asmFile;
	private String filename;
	private String functionName;
	//어셈블리코드로 번역된 명령어들을 저장할 StringBuilder
	private StringBuilder commands = new StringBuilder();
	private int conditionCnt = 0;
	private int retCnt = 0;
	
	//생성자
	public CodeWriter(String filePath) throws IOException {
		//filePath에는 .asm이 붙어있지 않아서 .asm을 붙여서 파일을 만든다
		this.asmFile = new File(filePath + ".asm");
		
		this.functionName = "null";
		
		//Bootstrap Code
		BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
		
		commands.append("@256").append('\n');
		commands.append("D=A").append('\n');
		commands.append("@SP").append('\n');
		commands.append("M=D").append('\n');
		
		if(asmFile.isFile() && asmFile.canWrite()) {
			writer.write(commands.toString());
			writer.close();
		}	
		commands.setLength(0);
		
		this.writeCall("Sys.init", 0);
		
	}
	
	//xxx.vm 에서 xxx의 값을 셋팅, static 변수 이름에 쓰인다
	void setFileName(String filename) {
		this.filename = filename.substring(0, filename.length()-3);
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
				
				commands.append('@').append(this.filename).append('.').append(Integer.toString(index)).append('\n');
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
				
				commands.append('@').append(this.filename).append('.').append(Integer.toString(index)).append('\n');
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
	
	//label 쓰기
	void writeLabel(String label) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
		
		commands.append('(').append(this.functionName).append('$').append(label).append(')').append('\n');
		
		if(asmFile.isFile() && asmFile.canWrite()) {
			writer.write(commands.toString());
			writer.close();
		}
		commands.setLength(0);
	}
	
	//goto 명령어
	void writeGoto(String label) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
		
		commands.append('@').append(this.functionName).append('$').append(label).append('\n');
		commands.append("0;JMP").append('\n');
		
		if(asmFile.isFile() && asmFile.canWrite()) {
			writer.write(commands.toString());
			writer.close();
		}
		commands.setLength(0);
	}
	
	//if-goto 명령어
	void writeIf(String label) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
		
		commands.append("@SP").append('\n');
		commands.append("AM=M-1").append('\n');
		commands.append("D=M").append('\n');
		commands.append('@').append(this.functionName).append('$').append(label).append('\n');
		commands.append("D;JNE").append('\n');
		
		if(asmFile.isFile() && asmFile.canWrite()) {
			writer.write(commands.toString());
			writer.close();
		}
		commands.setLength(0);
	}
	
	//function 선언하기
	void writeFunction(String functionName, int numLocals) throws IOException {
		this.functionName = functionName;
		BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
		
		commands.append('(').append(functionName).append(')').append('\n');
		for(int i = 0; i < numLocals; i++) {
			this.writePushPop(Parser.C_PUSH, "constant", 0);
		}
		
		if(asmFile.isFile() && asmFile.canWrite()) {
			writer.write(commands.toString());
			writer.close();
		}
		commands.setLength(0);
	}
	
	//return
	void writeReturn() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
		
		//R13은 FRAME, R14는 RET
		//FRAME = LCL
		commands.append("@LCL").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@R13").append('\n');
		commands.append("M=D").append('\n');
		//RET = *(FRAME-5)
		commands.append("@5").append('\n');
		commands.append("D=D-A").append('\n');
		commands.append("A=D").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@R14").append('\n');
		commands.append("M=D").append('\n');
		//*ARG = pop()
		commands.append("@SP").append('\n');
		commands.append("AM=M-1").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@ARG").append('\n');
		commands.append("A=M").append('\n');
		commands.append("M=D").append('\n');
		//SP = ARG+1
		commands.append("@ARG").append('\n');
		commands.append("D=M+1").append('\n');
		commands.append("@SP").append('\n');
		commands.append("M=D").append('\n');
		//THAT = *(FRAME-1)
		commands.append("@R13").append('\n');
		commands.append("D=M-1").append('\n');
		commands.append("A=D").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@THAT").append('\n');
		commands.append("M=D").append('\n');
		//THIS = *(FRAME-2)
		commands.append("@R13").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@2").append('\n');
		commands.append("D=D-A").append('\n');
		commands.append("A=D").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@THIS").append('\n');
		commands.append("M=D").append('\n');
		//ARG = *(FRAME-3)
		commands.append("@R13").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@3").append('\n');
		commands.append("D=D-A").append('\n');
		commands.append("A=D").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@ARG").append('\n');
		commands.append("M=D").append('\n');
		//LCL = *(FRAME-4)
		commands.append("@R13").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@4").append('\n');
		commands.append("D=D-A").append('\n');
		commands.append("A=D").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@LCL").append('\n');
		commands.append("M=D").append('\n');
		//goto RET
		commands.append("@R14").append('\n');
		commands.append("A=M").append('\n');
		commands.append("0;JMP").append('\n');
		
		if(asmFile.isFile() && asmFile.canWrite()) {
			writer.write(commands.toString());
			writer.close();
		}
		commands.setLength(0);
	}
	
	//call
	void writeCall(String functionName, int numArgs) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(asmFile, true));
		
		//push return-address
		commands.append("@RET").append(Integer.toString(this.retCnt)).append('\n');
		commands.append("D=A").append('\n');
		commands.append("@SP").append('\n');
		commands.append("AM=M+1").append('\n');
		commands.append("A=A-1").append('\n');
		commands.append("M=D").append('\n');
		//push LCL
		commands.append("@LCL").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@SP").append('\n');
		commands.append("AM=M+1").append('\n');
		commands.append("A=A-1").append('\n');
		commands.append("M=D").append('\n');
		//push ARG
		commands.append("@ARG").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@SP").append('\n');
		commands.append("AM=M+1").append('\n');
		commands.append("A=A-1").append('\n');
		commands.append("M=D").append('\n');
		//push THIS
		commands.append("@THIS").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@SP").append('\n');
		commands.append("AM=M+1").append('\n');
		commands.append("A=A-1").append('\n');
		commands.append("M=D").append('\n');
		//push THAT
		commands.append("@THAT").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@SP").append('\n');
		commands.append("AM=M+1").append('\n');
		commands.append("A=A-1").append('\n');
		commands.append("M=D").append('\n');
		//ARG = SP-n-5
		commands.append("@").append(Integer.toString(numArgs)).append('\n');
		commands.append("D=A").append('\n');
		commands.append("@5").append('\n');
		commands.append("D=D+A").append('\n');
		commands.append("@SP").append('\n');
		commands.append("D=M-D").append('\n');
		commands.append("@ARG").append('\n');
		commands.append("M=D").append('\n');
		//LCL = SP
		commands.append("@SP").append('\n');
		commands.append("D=M").append('\n');
		commands.append("@LCL").append('\n');
		commands.append("M=D").append('\n');
		//goto f
		commands.append("@").append(functionName).append('\n');
		commands.append("0;JMP").append('\n');
		//(return-address)
		commands.append("(RET").append(Integer.toString(this.retCnt)).append(")").append('\n');
		
		this.retCnt++;
		
		if(asmFile.isFile() && asmFile.canWrite()) {
			writer.write(commands.toString());
			writer.close();
		}
		commands.setLength(0);
	}
}
