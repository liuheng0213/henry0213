package basic.knowledge.henry.design_Pattern._01SOLID.openCLose.goodPractise;

public class AlipayProcessor implements PayProcessor{
    @Override
    public void handle() {
        System.out.println(" iam using ali pay");
    }
}
