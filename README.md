# OsxSystemAudioInfo
SuperCollider quark for OS X system audio info

Uses `system_profiler SPAudioDataType` to retrieve OS X system audio info.

Meant to be used in SuperCollider startup file to automatically set numOutputBusChannels, numInputBusChannels and sampleRate:
```supercollider
Platform.case(
	\osx, {
		s.options.numOutputBusChannels = OsxSystemAudioInfo.getNumOutputChannels;
		s.options.numInputBusChannels = OsxSystemAudioInfo.getNumInputChannels;
		s.options.sampleRate = OsxSystemAudioInfo.getSampleRate;
	}
);
```
All methods:
```supercollider
OsxSystemAudioInfo.getDefaultOutputDevice; // default output device name
OsxSystemAudioInfo.getDefaultInputDevice; // default input device name
OsxSystemAudioInfo.getNumOutputChannels("Built-in Output"); // optionally pass deviceName
OsxSystemAudioInfo.getNumInputChannels("Built-in Output"); // optionally pass deviceName
OsxSystemAudioInfo.getSampleRate("Built-in Output"); // optionally pass deviceName
```
