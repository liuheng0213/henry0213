package basic.knowledge.henry.design_Pattern._11DesignPattern_Visitor;

public interface Visitor {
    // 访问工程师类型
    void visit(Engineer engineer);

    // 访问经理类型
    void visit(Manager manager);
}
