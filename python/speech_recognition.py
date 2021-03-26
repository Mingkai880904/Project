# -*- coding: utf-8 -*-
"""
Created on Sun Dec 27 20:06:52 2020

@author: 沈明楷
"""


import speech_recognition as sr

#obtain audio from the microphone
r=sr.Recognizer()
with sr.Microphone() as source:
    print("Please wait. Calibrating microphone...")
    #listen for 5 seconds and create the ambient noise energy level
    r.adjust_for_ambient_noise(source, duration=6)
    print("Say something!")
    audio=r.listen(source)

# recognize speech using Google Speech Recognition
try:
    print("Google Speech Recognition thinks you said:")
    print(r.recognize_google(audio, language="zh-TW"))
except sr.UnknownValueError:
    print("Google Speech Recognition could not understand audio")
except sr.RequestError as e:
    print("No response from Google Speech Recognition service: {0}".format(e))