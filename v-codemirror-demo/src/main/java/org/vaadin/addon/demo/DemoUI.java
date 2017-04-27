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
        cmField.setValue("@Widgetset(\"com.vaadin.v7.Vaadin7WidgetSet\")\npublic class FiddleUi extends UI {\n\n	final private static DockerService dockerService = new DockerService();\n	\n	@Override\n	protected void init(VaadinRequest vaadinRequest) {");
        cmField.setSizeFull();
        cmField.setCaption("foobar");
		return cmField;
	}
}
