package org.vaadin.addon.codemirror;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.data.HasValue;
import com.vaadin.shared.Registration;
import com.vaadin.shared.ui.JavaScriptComponentState;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.HasValueChangeMode;

@JavaScript({
		// main library
		"vaadin://codemirror/codemirror.js?v=0.9",
		// Vaadin connector
		"vaadin://codemirror/codemirror-connector.js?v=0.9",
		// language support
		"vaadin://codemirror/mode/clike/clike.js?v=0.9",
		// language support
		"vaadin://codemirror/mode/javascript/javascript.js?v=0.9",
		// language support
		"vaadin://codemirror/mode/xml/xml.js?v=0.9",
		// language support
		"vaadin://codemirror/mode/markdown/markdown.js?v=0.9",
		// language support
		"vaadin://codemirror/mode/css/css.js?v=0.9",
		// code folding
		"vaadin://codemirror/fold/foldcode.js?v=0.9",
		// code folding
		"vaadin://codemirror/fold/comment-fold.js?v=0.9",
		// code folding
		"vaadin://codemirror/fold/brace-fold.js?v=0.9",
		// code folding
		"vaadin://codemirror/fold/foldgutter.js?v=0.9", })
@StyleSheet({
		// main styles
		"vaadin://codemirror/codemirror.css?v=0.9",
		// code folding
		"vaadin://codemirror/fold/foldgutter.css?v=0.9", })
public class CodeMirrorField extends AbstractJavaScriptComponent implements HasValue<String>, HasValueChangeMode {
	private List<ValueChangeListener<String>> valueChangeListeners = new ArrayList<>();
	private String value;

	public CodeMirrorField() {
		getState().mode = "text/x-java";
		getState().indentUnit = 2;
		getState().lineNumbers = true;
		getState().lineWrapping = true;
		getState().foldGutter = true;
		
//		setGutters("CodeMirror-linenumbers", "CodeMirror-foldgutter");

		addFunction("onValueChange", arguments -> {
			String value = arguments.asString();
			setValue(value, true);
		});

		setValueChangeMode(ValueChangeMode.EAGER);
	}

//	private void setGutters(String... gutters) {
//		for (String string : gutters) {
//			
//		}
//		getState().foldGutter = 
//	}

	public void setMode(String mode) {
		getState().mode = mode;
	}

	public void setIndentUnit(int indentUnit) {
		getState().indentUnit = indentUnit;
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
		String oldValue = this.value;
		this.value = value;
		if (!Objects.equals(oldValue, value)) {
			getState().value = value;
			for (ValueChangeListener<String> l : valueChangeListeners) {
				l.valueChange(new ValueChangeEvent<String>(this, oldValue, userOriginated));
			}
		}
	}

	@Override
	public String getValue() {
		return value;
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

	@Override
	protected CodeMirrorState getState(boolean markAsDirty) {
		// TODO Auto-generated method stub
		return (CodeMirrorState) super.getState(markAsDirty);
	}
}
