# OsxSystemAudioInfo
SuperCollider quark for OS X system audio info

Uses `system_profiler SPAudioDataType` to retrieve OS X system audio info.

Meant to be used in SuperCollider startup file:
```supercollider
Platform.case(
	\osx, {
		s.options.numOutputBusChannels = OsxSystemAudioInfo.getNumOutputChannels;
		s.options.numInputBusChannels = OsxSystemAudioInfo.getNumInputChannels;
		s.options.sampleRate = OsxSystemAudioInfo.getSampleRate;
	}
);
```
