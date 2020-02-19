package xyz.chph.toy.exception;

/*
 * Created by steve_chph, 2020/2/19 下午12:14
 */

public class TargetModuleNotDeclaredException extends CompilationException {
    public TargetModuleNotDeclaredException(String target) {
        System.out.println("module '" + target + "' not declared in ToyMake.json.");
    }
}
