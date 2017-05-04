window.org_vaadin_addon_codemirror_CodeMirrorField = function() {
	var connector = this;
	var e = connector.getElement();
	var config = {
		value : connector.getState().value,
		mode : connector.getState().mode,
		indentUnit : connector.getState().indentUnit,
	    lineNumbers: connector.getState().lineNumbers,
	    lineWrapping: connector.getState().lineWrapping,
	    foldGutter: connector.getState().foldGutter,
	    gutters: connector.getState().gutters,
	};
	// previous synced value
	connector.value = connector.getState().value;
	connector.internalValueChange = false;
	
	connector.cm = new CodeMirror(e, config);
	connector.valuePropagationTimeout = null;
	connector.sizeCheckTimeout = null;

	// workaround to react to the new size after Vaadin's layouting phase
	sizeCheckTimeout = window.setTimeout(function() {
		connector.checkSize();
	}, 200);
	
	connector.autoFoldImports = function () {
		for (var i = 0; i < connector.cm.lastLine(); ++i) {
			if (connector.cm.getLine(i).trim().lastIndexOf("import", 0) === 0) {
			    connector.cm.foldCode(CodeMirror.Pos(i, 0));
				break;
			}
		}
	}
	
	if (config.mode === "text/x-java") connector.autoFoldImports();

	connector.cm.on("changes", function() {
		if (connector.internalValueChange) {
			return;
		}
		if (connector.valuePropagationTimeout != null) {
			window.clearTimeout(connector.valuePropagationTimeout);
			connector.valuePropagationTimeout = null;
		}
		connector.valuePropagationTimeout = window.setTimeout(function() {
			connector.value = connector.cm.getValue();
			connector.onValueChange(connector.value);
		}, 200);
	});

	connector.checkSize = function () {
		if (connector.cm.display.lastWrapHeight != connector.cm.display.wrapper.clientHeight) {
			connector.cm.refresh();
		}
	};
	connector.onStateChange = function() {
		var state = connector.getState();
		var oldValue = connector.value;
		if (oldValue != state.value) {
			connector.internalValueChange = true;
			connector.cm.setValue(state.value);
			connector.internalValueChange = false;
		}

	};

};