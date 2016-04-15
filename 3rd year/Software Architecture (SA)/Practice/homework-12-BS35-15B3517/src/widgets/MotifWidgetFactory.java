package widgets;

public class MotifWidgetFactory extends WidgetFactory {
	public ScrollBar createScrollBar() {
		System.out.println("method createScrollBar in MotifWidgetFactory");		
		MotifScrollBar msc = new MotifScrollBar();	
		return msc;
	}

	public Window createWindow() {
		System.out.println("method createWindow in MotifWidgetFactory");
		MotifWindow mw = new MotifWindow();
		return mw;
	}

	public Button createButton() {
		System.out.println("method createButton in MotifWidgetFactory");
		MotifButton motifButton = new MotifButton();
		return motifButton;
	}
}
