OsxSystemAudioInfo {
	classvar <stdOut;
	classvar <stdOutLines;

	*initClass {
		stdOut = "system_profiler SPAudioDataType".unixCmdGetStdOut;
		stdOutLines = "system_profiler SPAudioDataType".unixCmdGetStdOutLines;
	}

	*getDefaultOutputDevice {
		var deviceNameLineIndex;
		var defaultOutputLineIndex = stdOutLines.detectIndex { |line|
			line.contains("Default Output Device: Yes");
		};
		if (defaultOutputLineIndex != nil, {
			var i = 1;
			while ({
				if (stdOutLines[defaultOutputLineIndex-i].trim == "", {
					deviceNameLineIndex = defaultOutputLineIndex - i - 1;
					false;
				}, {
					true;
				});
			}, {
				i = i + 1;
			});
		});
		^if (deviceNameLineIndex != nil, {
			var deviceName = stdOutLines[deviceNameLineIndex].trim;
			deviceName[0..(deviceName.size-2)];
		});
	}

	*getDefaultInputDevice {
		var deviceNameLineIndex;
		var defaultInputLineIndex = stdOutLines.detectIndex { |line|
			line.contains("Default Output Device: Yes");
		};
		if (defaultInputLineIndex != nil, {
			var i = 1;
			while ({
				if (stdOutLines[defaultInputLineIndex-i].trim == "", {
					deviceNameLineIndex = defaultInputLineIndex - i - 1;
					false;
				}, {
					true;
				});
			}, {
				i = i + 1;
			});
		});
		^if (deviceNameLineIndex != nil, {
			var deviceName = stdOutLines[deviceNameLineIndex].trim;
			deviceName[0..(deviceName.size-2)];
		});
	}

	*getNumOutputChannels { |deviceName = nil|
		var deviceNameLineIndex, numOutputChannelsLineIndex;
		deviceName = deviceName ?? this.getDefaultInputDevice;
		deviceNameLineIndex = stdOutLines.detectIndex { |line|
			line.contains(deviceName);
		};
		if (deviceNameLineIndex != nil, {
			var i = 1;
			while ({
				if (stdOutLines[deviceNameLineIndex+i] == nil, {
					false;
				}, {
					if ("Output Channels: ([0-9]+)".matchRegexp(stdOutLines[deviceNameLineIndex+i]), {
						numOutputChannelsLineIndex = deviceNameLineIndex+i;
						false;
					}, {
						true;
					});
				});
			}, {
				i = i + 1;
			});
		});
		^if (numOutputChannelsLineIndex != nil, {
			stdOutLines[numOutputChannelsLineIndex].findRegexp("Output Channels: ([0-9]+)")[1][1].asInteger;
		});
	}

	*getNumInputChannels { |deviceName = nil|
		var deviceNameLineIndex, numInputChannelsLineIndex;
		deviceName = deviceName ?? this.getDefaultInputDevice;
		deviceNameLineIndex = stdOutLines.detectIndex { |line|
			line.contains(deviceName);
		};
		if (deviceNameLineIndex != nil, {
			var i = 1;
			while ({
				if (stdOutLines[deviceNameLineIndex+i] == nil, {
					false;
				}, {
					if ("Input Channels: ([0-9]+)".matchRegexp(stdOutLines[deviceNameLineIndex+i]), {
						numInputChannelsLineIndex = deviceNameLineIndex+i;
						false;
					}, {
						true;
					});
				});
			}, {
				i = i + 1;
			});
		});
		^if (numInputChannelsLineIndex != nil, {
			stdOutLines[numInputChannelsLineIndex].findRegexp("Input Channels: ([0-9]+)")[1][1].asInteger;
		});
	}

	*getSampleRate { |deviceName = nil|
		var deviceNameLineIndex, sampleRateLineIndex;
		deviceName = deviceName ?? this.getDefaultInputDevice;
		deviceNameLineIndex = stdOutLines.detectIndex { |line|
			line.contains(deviceName);
		};
		if (deviceNameLineIndex != nil, {
			var i = 1;
			while ({
				if (stdOutLines[deviceNameLineIndex+i] == nil, {
					false;
				}, {
					if ("Current SampleRate: ([0-9]+)".matchRegexp(stdOutLines[deviceNameLineIndex+i]), {
						sampleRateLineIndex = deviceNameLineIndex+i;
						false;
					}, {
						true;
					});
				});
			}, {
				i = i + 1;
			});
		});
		^if (sampleRateLineIndex != nil, {
			stdOutLines[sampleRateLineIndex].findRegexp("Current SampleRate: ([0-9]+)")[1][1].asInteger;
		});
	}
}