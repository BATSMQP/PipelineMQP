import wave


dataType = input(" Will you be analysing speach(ex: .wav)=1, neuro (ex: .csv)= 2, or facial (ex: .mp4)= 3")
## datatype will be used for the presenting of images
print("Welcome to the interpreter, before we begin, please ensure that all used WORDS/packages have been installed via pip")
path = input("Please list the path to the file where the desired method can be found dont add the .py: ")
functionName= input("Please name the function you wish to use within the python file: ")
signalPath = input("What file woud you like to input: ")

if 
signal= wave.open(signalPath, 'r')
samplingRate= input("Sampling Rate: ")
st_win=input("window size (sec) [st_Win]: ")
st_step=input("Window step (sec) [st_step]: ")
smooth_window=0.5
weight=0.5
plot= False



import audioSegmentation 

test= audioSegmentation.silence_removal(signal,samplingRate,st_win,st_step, smooth_window,weight,plot)

##Test= getattr(path,functionName)(signal,samplingRate,st_win,st_step, smooth_window,weight,plot)




