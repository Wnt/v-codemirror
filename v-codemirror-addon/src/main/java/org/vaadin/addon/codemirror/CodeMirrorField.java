package org.vaadin.addon.codemirror;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.data.HasValue;
import com.vaadin.shared.Registration;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.HasValueChangeMode;

@JavaScript({
	// main library
	"vaadin://codemirror/codemirror.js", 
	// Vaadin connector
	"vaadin://codemirror/codemirror-connector.js",
	// language support
	"vaadin://codemirror/mode/clike/clike.js",
	// language support
	"vaadin://codemirror/mode/javascript/javascript.js",
	// language support
	"vaadin://codemirror/mode/xml/xml.js",
	// language support
	"vaadin://codemirror/mode/markdown/markdown.js",
	// language support
	"vaadin://codemirror/mode/css/css.js",
	})
@StyleSheet({"vaadin://codemirror/codemirror.css"})
public class CodeMirrorField extends AbstractJavaScriptComponent implements HasValue<String>, HasValueChangeMode {
	private List<ValueChangeListener<String>> valueChangeListeners = new ArrayList<>();
	
	public CodeMirrorField() {
		getState().mode = "text/x-java";
		
		addFunction("onValueChange", arguments -> {
			String value = arguments.asString();
			setValue(value, true);
		});
		
		setValueChangeMode(ValueChangeMode.EAGER);
	}
	public void setMode(String mode) {
		getState().mode = mode;
	}

	@Override
	public void setValueChangeMode(ValueChangeMode valueChangeMode) {
		getState().valueChangeMode = valueChangeMode;
	}

	@Override
	public ValueChangeMode getValueChangeMode() {
		return getState().valueChangeMode;
	}

	@Override
	public void setValueChangeTimeout(int valueChangeTimeout) {
		getState().valueChangeTimeout = valueChangeTimeout;

	}

	@Override
	public int getValueChangeTimeout() {
		return getState().valueChangeTimeout;
	}

	@Override
	public void setValue(String value) {
		setValue(value, false);

	}

	protected void setValue(String value, boolean userOriginated) {
		String oldValue = getState().value;
		getState().value = value;
		if (oldValue != value) {
			for (ValueChangeListener<String> l : valueChangeListeners) {
				l.valueChange(new ValueChangeEvent<String>(this, oldValue, userOriginated));
			}
		}
	}

	@Override
	public String getValue() {
		return getState().value;
	}

	@Override
	public Registration addValueChangeListener(ValueChangeListener<String> listener) {
		valueChangeListeners.add(listener);
		return () -> valueChangeListeners.remove(listener);
	}

	@Override
	public boolean isReadOnly() {
		// TODO Auto-generated method stub
		return super.isReadOnly();
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		// TODO Auto-generated method stub
		super.setReadOnly(readOnly);
	}

	@Override
	public boolean isRequiredIndicatorVisible() {
		// TODO Auto-generated method stub
		return super.isRequiredIndicatorVisible();
	}

	@Override
	public void setRequiredIndicatorVisible(boolean visible) {
		// TODO Auto-generated method stub
		super.setRequiredIndicatorVisible(visible);
	}

	@Override
	protected CodeMirrorState getState() {
		return (CodeMirrorState) super.getState();
	}
}
