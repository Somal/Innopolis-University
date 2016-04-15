package widgets;

public class Client {
    public WidgetFactory wf;

    public Client(WidgetFactory widgetFactory){
        this.wf=widgetFactory;
    }

    public void run(){
        this.wf.createButton();
        this.wf.createScrollBar();
        this.wf.createWindow();
    }

    public static void main(String[] args) {
        Client c=new Client(new MotifWidgetFactory());
        c.run();
    }







}