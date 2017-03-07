window.org_vaadin_addon_codemirror_CodeMirrorField = function() {
	var e = this.getElement();
	var connector = this;
	var cm = new CodeMirror(
			e,
			{
				value : this.getState().value,
				mode : this.getState().mode
			});
	
	cm.on("changes", function() {
		connector.onValueChange(cm.getValue());
	});

	this.onStateChange = function() {
		var state = this.getState();
		var oldValue = cm.getValue();
		if (oldValue != state.value) {
			cm.setValue(state.value);
		}

	};

};