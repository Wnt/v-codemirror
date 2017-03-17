window.org_vaadin_addon_codemirror_CodeMirrorField = function() {
	var e = this.getElement();
	var connector = this;
	var config = {
		value : this.getState().value,
		mode : this.getState().mode
	};
	var value = this.getState().value;
	var internalValueChange = false;
	;
	var cm = new CodeMirror(e, config);
	this.valuePropagationTimeout = null;

	cm.on("changes", function() {
		if (connector.internalValueChange) {
			return;
		}
		connector.value = cm.getValue();
		if (connector.valuePropagationTimeout != null) {
			window.clearTimeout(connector.valuePropagationTimeout);
			connector.valuePropagationTimeout = null;
		}
		connector.valuePropagationTimeout = window.setTimeout(function() {
			connector.onValueChange(connector.value);
		}, 200);
	});

	this.onStateChange = function() {
		var state = this.getState();
		var oldValue = connector.value;
		if (oldValue != state.value) {
			connector.internalValueChange = true;
			cm.setValue(state.value);
			connector.internalValueChange = false;
		}

	};

};