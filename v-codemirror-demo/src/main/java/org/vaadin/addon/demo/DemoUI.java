package org.vaadin.addon.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addon.codemirror.CodeMirrorField;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("MyComponent Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        TabSheet tabSheet = new TabSheet(createCMField(),createCMField());
        tabSheet.setSizeFull();
        setContent(tabSheet);
    }

	private CodeMirrorField createCMField() {
		CodeMirrorField cmField = new CodeMirrorField();
        cmField.setValue("package org.vaadin.vaadinfiddle;\n" + 
        		"\n" + 
        		"import javax.servlet.annotation.WebServlet;\n" + 
        		"\n" + 
        		"import com.vaadin.annotations.Theme;\n" + 
        		"import com.vaadin.annotations.VaadinServletConfiguration;\n" + 
        		"import com.vaadin.server.VaadinRequest;\n" + 
        		"import com.vaadin.server.VaadinServlet;\n" + 
        		"import com.vaadin.ui.Button;\n" + 
        		"import com.vaadin.ui.Label;\n" + 
        		"import com.vaadin.ui.TextField;\n" + 
        		"import com.vaadin.ui.UI;\n" + 
        		"import com.vaadin.ui.VerticalLayout;\n" + 
        		"\n" + 
        		"/**\n" + 
        		" * This UI is the application entry point. A UI may either represent a browser window \n" + 
        		" * (or tab) or some part of a html page where a Vaadin application is embedded.\n" + 
        		" * <p>\n" + 
        		" * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be \n" + 
        		" * overridden to add component to the user interface and initialize non-component functionality.\n" + 
        		" */\n" + 
        		"@Theme(\"mytheme\")\n" + 
        		"public class MyUI extends UI {\n" + 
        		"\n" + 
        		"    @Override\n" + 
        		"    protected void init(VaadinRequest vaadinRequest) {\n" + 
        		"        final VerticalLayout layout = new VerticalLayout();\n" + 
        		"        \n" + 
        		"        final TextField name = new TextField();\n" + 
        		"        name.setCaption(\"Type your name here:\");\n" + 
        		"\n" + 
        		"        Button button = new Button(\"Click Me\");\n" + 
        		"        button.addClickListener( e -> {\n" + 
        		"            layout.addComponent(new Label(\"Thanks \" + name.getValue() \n" + 
        		"                    + \", it works!\"));\n" + 
        		"        });\n" + 
        		"        \n" + 
        		"        layout.addComponents(name, button);\n" + 
        		"        \n" + 
        		"        setContent(layout);\n" + 
        		"    }\n" + 
        		"\n" + 
        		"    @WebServlet(urlPatterns = \"/*\", name = \"MyUIServlet\", asyncSupported = true)\n" + 
        		"    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)\n" + 
        		"    public static class MyUIServlet extends VaadinServlet {\n" + 
        		"    }\n" + 
        		"}\n" + 
        		"");
        cmField.setSizeFull();
        cmField.setCaption("foobar");
		return cmField;
	}
}
