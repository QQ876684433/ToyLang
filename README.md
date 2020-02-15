Toy (another JVM based language)
=======

Toy is a simple programming language running on the jvm, which is envolved from [Enkel](https://github.com/JakubDziworski/Enkel-JVM-language)


## Build & Run
- Generate parser code from ANTLR
  `./gradlew :antlr:generateGrammarSource `
  
- Compile `*.toy` to `*.class`    
  command line usage:
  
  ```shell
  usage: Compiler [-d <destination>] [-h] [-s <source>]
   -d,--dest <destination>   the output directory of the class files,
                             otherwise the root directory of project as
                             default.
   -h,--help                 show this help message and exit program
   -s,--src <source>         the root directory of your project,
                             otherwise the workspace directory as default.
  ```
  
  example: 
  
  ```shell
  java -jar xyz.chph.toy.compiler-0.0.1-SNAPSHOT.jar -s ToyExamples/ -d build/
  ```
  
- Run
  `java *.class`
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