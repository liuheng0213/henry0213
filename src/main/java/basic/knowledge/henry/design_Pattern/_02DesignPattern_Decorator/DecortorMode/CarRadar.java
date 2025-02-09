package basic.knowledge.henry.design_Pattern._02DesignPattern_Decorator.DecortorMode;

public class CarRadar extends CarDecorator{
	public CarRadar(ICarShowable myCar) {
		super(myCar);
	}

	@Override
	public void show() {
		super.show();
		System.out.println("radar added");
	}
}
