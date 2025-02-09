package basic.knowledge.henry.design_Pattern._11DesignPattern_Visitor.IMCTest;



public interface Visitor {
    HandSymbol visit(CmpPlayer cmpPlayer);

    HandSymbol visit(HumanPlayer humanPlayer);
}
