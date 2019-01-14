package ch.hesge.vaadin.ui.views;


import ch.hesge.vaadin.ui.common.components.NavBar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;


@Route("")
public class Home extends VerticalLayout {

    public Home() {
        initView();
    }

    private void initView() {
        NavBar navBar = new NavBar();

        TextArea area1 = new TextArea();
        area1.setValue("Bienvenue dans votre biblioteque virtuelle \n \n Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ");

        Image image = new Image();
        image.setSrc("https://imagesvc.timeincapp.com/v3/mm/image?url=https%3A%2F%2Ftimedotcom.files.wordpress.com%2F2015%2F06%2F521811839-copy.jpg&w=1600&c=sc&poi=face&q=70");

        Div pageContent = new Div(area1, image);

        add(navBar, pageContent);
    }
}
