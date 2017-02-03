# CS5363Compiler
Compiler for CS5363
Based on http://xywang.100871.net/Proj_CS5363/Project_16.pdf 
1. Main file: main.java to get the input basefile.tl to pass it into scanner
2. Scanner file: scanner.java that feed the input and generate tokens, generate file basefile.tk. Output "SCANNER ERROR"
3. Parser: parser.java :  After the parser tree is
generated, or while the parser tree is being generated, you are required to
remove non-essential parts of the parser tree to reduce it to an abstract. "PARSER
ERROR", or the text "SYNTAX ERROR". 
syntax tree (AST). Output:  Graphviz DOT file <basename>.ast.dot
4. Type Checking and Type Annotated AST <basename>.3A.cfg.dot
5. Three Address Code GeneratioN
6. MIPS Assembly Output 
