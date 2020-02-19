Toy (another JVM based language)
=======

Toy is a simple programming language running on the jvm, which is envolved from [Enkel](https://github.com/JakubDziworski/Enkel-JVM-language)


## Build & Run
- Generate parser code from ANTLR
  `./gradlew :antlr:generateGrammarSource `
  
- Compile `*.toy` to `*.class`    
  command line usage:
  
  ```shell
  usage: Compiler [-d <destination>] [-h] [-m <module>] [-s <source>]
   -d,--dest <destination>   the output directory of the class files,
                             otherwise the root directory of project as
                             default.
   -h,--help                 show this help message and exit program
   -m,--module <module>      the module declared in ToyMake.json you want to
                             build,
                             otherwise all the modules will be built.
   -s,--src <source>         the root directory of your project,
                             otherwise the workspace directory as default.
  ```
  
  example: 
  
  ```shell
  java -cp build/:compiler/build/libs/compiler-0.0.1-SNAPSHOT.jar xyz.chph.toy.compiler.Compiler -s ToyExamples/ -d build/
  ```
  
  output:
  
  ```shell
  steve@steve-PC:~/$ java -cp build/:compiler/build/libs/compiler-0.0.1-SNAPSHOT.jar xyz.chph.toy.compiler.Compiler -s ToyExamples/ -d build/
  Importing/Function.toy
  Importing/Import.toy
  ClassPathCalls/Library.toy
  ClassPathCalls/Client.toy
  ClassPathCalls/TestJava.toy
  ```
  
  and the class files will be created in `./build/`：
  
  ```shell
  steve@steve-PC:~/$ tree build/
  build/
  ├── ClassPathCalls
  │   ├── Client.class
  │   ├── Library.class
  │   └── TestJava.class
  └── Importing
      ├── Function.class
      └── Import.class
  
  2 directories, 5 files
  ```
  
- Run
  `java -cp build/ *.class`, examples:
  
  ```shell
  java -cp build/ Importing.Import
  ```
## Example

- Input(`Fields.toy`)
 ```
Fields {
 int field
 start {
    field = 5
    print field
 }
}
 ```

- Output(`Fields.class`)

Decompiled form:
```
public class Fields {
    public int field;

    public void start() {
        this.field = 5;
        System.out.println(this.field);
    }

    public Fields() {
    }

    public static void main(String[] var0) {
        (new Fields()).start();
    }
}
```

Other examples please see `./ToyExamples/*.toy`

## TODO
- [ ] Unit Tests & Bugs fixing
- [ ] Other grammar features
- [ ] VS Code/IDEA highlight plugin
- [ ] Support debugger
- [ ] Port to LLVM IR