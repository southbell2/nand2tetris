@ARG
D=M
@1
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
@R3
D=A
@1
D=D+A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@0
D=D+A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@1
D=D+A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@ARG
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
@2
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
AM=M-1
D=M
A=A-1
M=M-D
@ARG
D=M
@0
D=D+A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
(null$MAIN_LOOP_START)
@ARG
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
@SP
AM=M-1
D=M
@null$COMPUTE_ELEMENT
D;JNE
@null$END_PROGRAM
0;JMP
(null$COMPUTE_ELEMENT)
@THAT
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
@THAT
D=M
@1
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
@SP
AM=M-1
D=M
A=A-1
M=D+M
@THAT
D=M
@2
D=D+A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@R3
D=A
@1
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
AM=M-1
D=M
A=A-1
M=D+M
@R3
D=A
@1
D=D+A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@ARG
D=M
@0
A=D+A
D=M
@SP
AM=M+1
A=A-1
M=D
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
AM=M-1
D=M
A=A-1
M=M-D
@ARG
D=M
@0
D=D+A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@null$MAIN_LOOP_START
0;JMP
(null$END_PROGRAM)
