package my.vaadin.app;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    
    private CustomerService service = CustomerService.getInstance();
    private Grid<Customer> grid = new Grid<>(Customer.class);
    TextField filter= new TextField();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        //textfield Filter
        filter.setPlaceholder("Want to Search");
        filter.addValueChangeListener(e->updateList());
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        
        // filter button
        Button filterButton=new Button("Search",VaadinIcons.SEARCH);
        filterButton.setDescription("Clear the FilerTextField");
        filterButton.addClickListener(e->filter.clear());
        
        //filter layout
        CssLayout filteringLayout =new CssLayout();
        filteringLayout.addComponents(filter,filterButton);
        filteringLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        
        grid.setColumns("firstName", "lastName", "email");

        // add Grid to the layout
        layout.addComponents(filteringLayout,grid);

        // fetch list of Customers from service and assign it to Grid
        updateList();

        setContent(layout);
    }

    public void updateList() {
        List<Customer> customers = service.findAll(filter.getValue());
        grid.setItems(customers);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
