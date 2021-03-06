# Wise Commute

#### By: Ryan Jones and Dong Gun Lee
#### Version: 06/06/2017
#### Ideal Virtual Device: Nexus 6 API 23 and Pixel 

## Description
Wise Commute is an application that I've wanted to build from the early days at Epicodus. The app will allow users to view train details about current arrivals and also be able to self report on the condition of those trains.

## Demo
<img src="http://g.recordit.co/jdthmGjgIU.gif" width="30%">

## Requirements
* Android Studio
* Java JDK

## Files needed
* At the root directory, create a file called 'gradle.properties'
* Add the following code to 'gradle.properties'
```
Trimet_API_KEY = "3B5160342487A47D436E90CD9"
Twitter_CONSUMER_KEY = "Your_Key_Here"
Twitter_CONSUMER_SECRET = "Your_Key_Here"
Twitter_TOKEN_KEY = "Your_Key_Here"
Twitter_TOKEN_SECRET = "Your_Key_Here"
```
* Click gradle sync

## Twitter API
* Sign up for Twitter's API
* Create an application
* Copy Consumer key/secret and Token key/secret to 'gradle.properties'

## How to run
* Clone this repo
* Open Android Studio
* Select 'Open Project'
* Navigate to the directory
* Android will auto build

## Technologies Used
* Java
* Android Studio
* Firebase
* Trimet API

## Future Features
* The ability for users to self report conditions of the trains they're riding
* All Trimet Train Stops in the Portland Area
* All Trimet Bus Stops in the Portland Area
* Auto location detection and Google map pin points allowing the user to see close stops
* Google Ad support

## App Preview

### Registration
![Registration](https://github.com/Znergy/android-wise-commute/blob/master/app/src/main/res/drawable/register.png)

### Train Selection
![Train Selection](https://github.com/Znergy/android-wise-commute/blob/master/app/src/main/res/drawable/traincolor.png)

![Train Direction](https://github.com/Znergy/android-wise-commute/blob/master/app/src/main/res/drawable/traincolorclicked.png)

### Stop Selection
![Stop Selection](https://github.com/Znergy/android-wise-commute/blob/master/app/src/main/res/drawable/stoplist.png)

### Train Arrivals
![Train Arrivals](https://github.com/Znergy/android-wise-commute/blob/master/app/src/main/res/drawable/arrivals.png)





