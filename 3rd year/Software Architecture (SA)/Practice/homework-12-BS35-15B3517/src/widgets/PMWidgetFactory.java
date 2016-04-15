package widgets;

public class PMWidgetFactory extends WidgetFactory {
	
	public ScrollBar createScrollBar() {
		System.out.println("method createScrollBar in PMWidgetFactory");
		PMScrollBar pmsb = new PMScrollBar();	
		return pmsb;
	}

	public Window createWindow() {
		System.out.println("method createScrollBar in PMWidgetFactory");
		PMWindow pmw = new PMWindow();		
		return pmw;
	}

	public Button createButton() {
		System.out.println("method createButton in PMWidgetFactory");
		PMButton pmButton = new PMButton();
		return pmButton;
	}
}
