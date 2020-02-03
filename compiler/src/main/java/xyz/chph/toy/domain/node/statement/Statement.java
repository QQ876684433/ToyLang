package xyz.chph.toy.domain.node.statement;

import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.node.Node;

@FunctionalInterface
public interface Statement extends Node {
    void accept(StatementGenerator generator);
}
