package ch.hesge.vaadin.ui.views.errors;


import ch.hesge.vaadin.ui.common.NavBar;
import ch.hesge.vaadin.ui.views.Home;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("404")
@PageTitle("Page Not Found")
public class NotFound extends VerticalLayout {

    public NotFound() {
        initView();
    }

    private void initView() {
        NavBar navBar = new NavBar();

        H1 h1 = new H1("Page not found!");
        RouterLink bookListLink = new RouterLink("Please go back home", Home.class);

        Div pageContent = new Div(h1, bookListLink);

        add(navBar, pageContent);
    }
}
