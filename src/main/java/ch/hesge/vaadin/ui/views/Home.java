package ch.hesge.vaadin.ui.views;


import ch.hesge.vaadin.ui.common.components.NavBar;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;


@Route("")
public class Home extends VerticalLayout {

    private NavBar navBar = new NavBar();
    private TextArea textArea = new TextArea();
    private Image image = new Image();
    private HorizontalLayout pageContent = new HorizontalLayout();

    public Home() {
        initView();
        addStyling();
    }

    private void initView() {
        textArea.setValue("Bienvenue dans votre biblioteque virtuelle \n \n Sed ut perspiciatis unde omnis " +
                "iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa" +
                " quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim" +
                " ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni " +
                "dolores eos qui ratione voluptatem sequi nesciunt. ");

        image.setSrc("http://bit.ly/2Fzlnpd");

        pageContent.add(textArea, image);
        add(navBar, pageContent);
    }

    private void addStyling() {
        pageContent.getStyle().set("width", "100%");
        pageContent.getStyle().set("max-height", "80vh");
        textArea.getStyle().set("padding", "0px");
        textArea.getStyle().set("width", "30%");
        image.getStyle().set("width", "70%");
    }


}
