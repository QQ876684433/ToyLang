Toy (another JVM based language)
=======

Toy is a simple programming language running on the jvm, which is envolved from [Enkel](https://github.com/JakubDziworski/Enkel-JVM-language)



## Dependency

- Toy(v0.0.1) relies on JRE 1.8
- Toy grammar definition and code generation relies on ANTLR4

## Build & Run

- Generate parser code from ANTLR
  `./gradlew :antlr:generateGrammarSource `
  
- Compile `*.toy` to `*.class`    
  Using a gradle plugin in IDEA 
  
  or by shell:
  
  ```shell
  java -jar xxx.jar xyz.chph.compiler.Compiler xxx.toy
  ```
  
- Run
  `java *.class`
## Example

- Input(`Fields.toy`)
 ```java
Fields {
	int field
	start() {
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