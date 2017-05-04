window.org_vaadin_addon_codemirror_CodeMirrorField = function() {
	var e = this.getElement();
	var connector = this;
	var config = {
		value : this.getState().value,
		mode : this.getState().mode,
		indentUnit : this.getState().indentUnit,
	    lineNumbers: this.getState().lineNumbers,
	    lineWrapping: this.getState().lineWrapping,
	    foldGutter: this.getState().foldGutter,
	    gutters: this.getState().gutters,
	};
	// previous synced value
	this.value = this.getState().value;
	this.internalValueChange = false;
	
	this.cm = new CodeMirror(e, config);
	this.valuePropagationTimeout = null;
	this.sizeCheckTimeout = null;

	// workaround to react to the new size after Vaadin's layouting phase
	sizeCheckTimeout = window.setTimeout(function() {
		connector.checkSize();
	}, 200);
	
	this.autoFoldImports = function () {
		for (var i = 0; i < connector.cm.lastLine(); ++i) {
			if (connector.cm.getLine(i).trim().lastIndexOf("import", 0) === 0) {
			    connector.cm.foldCode(CodeMirror.Pos(i, 0));
				break;
			}
		}
	}
	
	if (config.mode === "text/x-java") connector.autoFoldImports();

	this.cm.on("changes", function() {
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

	this.checkSize = function () {
		if (connector.cm.display.lastWrapHeight != connector.cm.display.wrapper.clientHeight) {
			connector.cm.refresh();
		}
	};
	this.onStateChange = function() {
		var state = this.getState();
		var oldValue = connector.value;
		if (oldValue != state.value) {
			connector.internalValueChange = true;
			connector.cm.setValue(state.value);
			connector.internalValueChange = false;
		}

	};

};