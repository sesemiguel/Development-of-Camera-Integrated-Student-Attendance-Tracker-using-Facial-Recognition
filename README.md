# Development of Camera Integrated Student Attendance Tracker using Facial Recognition

###### A school project in partial fulfillment for the subject Computer System Analysis and Design (LBYCP30) 
###### Electronics and Communications Department
###### De La Salle University - Manila



### General Block Diagram
<img src="screenshots/fig1.png" width="300">

First and foremost, gathering of data includes the general images of various faces to be processed then these data are to be trained via a medium probably OpenCV. After this, the integration of data comes in, then to be directed to the database. The database acts as the main source of all the data and plays a vital role for the whole project. On the other hand, the live input of faces is to be processed and detected, then will go directly to the database to instantly check whether the live input is registered. If it is registered, then it will be recognized and output the student classification

### Software Interfaces
* Java 8
* OpenCV 2.4.13
* XAMPP 7.2.1

### Sample Runs
<img src="screenshots/fig2.png" width="300">

It will require users to input their username and password for security purposes. A user without an existing account can create their own with the “Create Account” Button.

<img src="screenshots/fig3.png" width="300">

It opened from the login interface, the creation of account interface let users input their information for their own personal account with their corresponding account type either student or teacher.

<img src="screenshots/fig4.png" width="300">

The student user interface contains the personal information of the respective student. They are also able to view their login times and time-ins from their classes.

<img src="screenshots/fig5.png" width="300">

The teacher user interface contains the personal information of the respective teacher. They are also able to view their login times and check the attendance of their students in their class.

<img src="screenshots/fig6.png" width="300">

The camera tracking interface contains the camera live input, start, stop, capture, and train buttons. A user can stop or start the camera. While on start state a user is able to capture to recognize, enabling the name, id number, and time-in of the recognized student. The train button is used to train new faces for another training data set.

<img src="screenshots/fig7.png" width="300">

The administrator interface is able to oversee all accounts and accept new pending accounts. The interface is capable of setting the dean’s lister and delete a student or a teacher from the list.

<img src="screenshots/fig8.png" width="300">

The login times interface is accessible from the teacher’s interface. It lets the teacher have an overview of the time-in of their students. It contains the ID number of the student and their respective login day and time.

### Dataflow Diagram
<img src="screenshots/fig9.png" width="300">

### LBPH Flowchart
<img src="screenshots/fig10.png" width="300">

### System Attendance Flowchart
<img src="screenshots/fig11.png" width="300">

### Reliability
The system must first identify a student’s facial feature and then correctly distinguish the students identity. During the testing phase, the system must label 5 sample frontal images of 10 students. The accuracy of the face recognizer is inversely proportional to the size of the database. The program was tested on the 10 different people and detected 7/10 of them correctly. 

### Contributors
* FALLAR, Mac Excel
* RAMOS, Patrick Julian
* SESE, Miguel Karlo
* SERRANO, Fae Nicole
