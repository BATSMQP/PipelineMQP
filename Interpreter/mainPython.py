
import wave
import csv
import sys
##from pickle import encode_long

##when conencted to the AWS/GUI, the data will already be had=> we'll know what it is and all that will be directly handled by the system and not the user, and they can look directly into a folder 

##import the function 
TheMod= input("What is the name of the file.py the function is from, without the .py: ") ##the name of the file we're taking the function form 
path = input("Please list the path to the file where the desired method can be found dont add the .py: ") ##module path
functionName= input("Please name the function you wish to use within the python file: ") ##name of the function
sys.path.append(path)
from TheMod import functionName

##Download/Open/read the data you'd like to analyse
signalPath = input("What datafile woud you like to input: ")
dataType = input(" Is it a .wav = 1., if a .csv= 2: ") ##hopefully this will be automated in the future so taht the user doesnt have to make it explicit every time (make a search algo that check for dots in the last 5 characters of the pathfile and associates it with the specific way to open it)
if dataType==1:
    ##open and visualise the data
    signal= wave.open(signalPath, 'r')
if dataType==2:
    signal= csv.reader(signalPath, newline='')

##prepare to use the method we want
numOfNeeds= input("How many inputs are needed for the function: ")
InputVarNames= [0]*numOfNeeds ##this is the types of things put in ex: ['Classnames', 'file path']
InputVals = [0]* numOfNeeds ## this is the actual list of inputs that will be given to the funtion
##fill the lists with the variables needed for the function 
for x in InputVals:
    InputVarNames[x]= input("What is the variable name: ")
    InputVals[x]= input("Input the input: ")





#######NATHANS STUFF

'''print('This ran')

import sys
sys.path.insert(1,'lib/pyAudioAnalysisMaster/pyAudioAnalysis')

import import_test

import_test

import providedTest

providedTest

# signalPath = input("What file woud you like to input: ")
# signal= wave.open(signalPath, 'r')
# samplingRate= input("Sampling Rate: ")
# st_win=input("window size (sec) [st_Win]: ")
# st_step=input("Window step (sec) [st_step]: ")
# smooth_window=0.5
# weight=0.5
# plot= False

# import audioSegmentation 

# test= audioSegmentation.silence_removal(signal,samplingRate,st_win,st_step, smooth_window,weight,plot)

##Test= getattr(path,functionName)(signal,samplingRate,st_win,st_step, smooth_window,weight,plot)

# print('This ran')
# import matplotlib.pyplot as plt
# import numpy as np
# import import_test

# import_test

# import providedTest

# providedTest '''


