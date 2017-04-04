package org.vaadin.addon.codemirror;

import com.vaadin.shared.ui.JavaScriptComponentState;
import com.vaadin.shared.ui.ValueChangeMode;

public class CodeMirrorState extends JavaScriptComponentState {

	public ValueChangeMode valueChangeMode;
	public int valueChangeTimeout;
	public String value;
	public String mode;
	public int indentUnit;

}
