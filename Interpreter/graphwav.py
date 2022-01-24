import sys
#from lib.pyAudioAnalysisMaster import pyAudioAnalysis
sys.path.insert(1,'lib/pyAudioAnalysisMaster/pyAudioAnalysis')
import numpy as np 
import ShortTermFeatures as aF
import audioBasicIO as aIO 
import plotly
import plotly.graph_objs as go 

import IPython

fs, s = aIO.read_audio_file("lib/pyAudioAnalysisMaster/pyAudioAnalysis/data/3WORDS.wav")
duration = len(s) / float(fs)
win, step = 0.050, 0.050
[f, fn] = aF.feature_extraction(s, fs, int(fs * win), 
                                int(fs * step))

# plot short-term energy
# create time axis in seconds
time = np.arange(0, duration - step, win) 
# get the feature whose name is 'energy'
energy = f[fn.index('energy'), :]
mylayout = go.Layout(yaxis=dict(title="frame energy value"),
                     xaxis=dict(title="time (sec)"))
plotly.offline.iplot(go.Figure(data=[go.Scatter(x=time, 
                                                y=energy)], 
                               layout=mylayout))