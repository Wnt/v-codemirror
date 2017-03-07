window.org_vaadin_addon_codemirror_CodeMirrorField = function() {
	var e = this.getElement();
	var connector = this;
	var config = {
		value : this.getState().value,
		mode : this.getState().mode
	};
	var cm = new CodeMirror(e, config);
	this.valuePropagationTimeout = null;

	cm.on("changes", function() {
		if (connector.valuePropagationTimeout != null) {
			window.clearTimeout(connector.valuePropagationTimeout);
		}
		connector.valuePropagationTimeout = window.setTimeout(function() {
			connector.onValueChange(cm.getValue());
		}, 100);
	});

	this.onStateChange = function() {
		var state = this.getState();
		var oldValue = cm.getValue();
		if (oldValue != state.value) {
			cm.setValue(state.value);
		}

	};

};