# Theater-Ticket-Dispenser-Simulation

This is the final project for the **Object-oriented programming** course.
The objective is to create a Java application in order to prove that I have understand the Object-oriented programming and the UML diagrams given by the teachers.

## Author
---
@MiguelAngelSM

# Technical features
- Implemented with Java using the IDE Apache NetBeans.

- Implemented with AbsoluteLayout.jar, TheaterTicketDispenserSimulator.jar and UrjcBankServer.jar as the GUI given by the teachers.

- Oriented for *Spanish speakers*, *Basque speakers*, *Galician speakers*, *Catalan speakers* and *English speakers*.

# Brief explanation of the project
The Dispenser has two main operations (this amount can increase):
- Select Language: it lets the user change the language configuration of the Dispenser.
- Buy Tickets: it lets the user select the *date*, *area* and *seats* (maximum of 4) for the tickets. Then it ask for a credit card and it finally realizes the payment and prints the tickets.

Some other functionalities and specifications:
- After a payment, if the credit card is not taken in 30 seconds the Dispenser will retain it.
- After 30 secons without interaction with the Dispenser it backs to the Welcome Screen.
- Cancel buttons back to the Welcome Screen.
- On weekends the price is multiplied by a multiplier given in the config file for the theater.
- On Mondays there are not Theater passes.
- There is only one play for the theater.
- There is only one Dispenser in the theater.

---
###### Example of use
---

https://user-images.githubusercontent.com/117299908/215608226-d3cc84dd-821a-4faa-9c75-bc090b231e3c.mp4

---
###### Welcome Screen
---

![WelcomeScreen](https://user-images.githubusercontent.com/117299908/215600063-cd64edec-b50e-493b-a103-a4a171419c02.PNG)

---
###### Language Selection Screen
---

![LanguageSelectionScreen](https://user-images.githubusercontent.com/117299908/215600123-e0a13e62-b5cb-4666-b6b9-2a525568510a.PNG)

---
###### Date Selection Screen
---

![DateSelectionScreen](https://user-images.githubusercontent.com/117299908/215600179-12c01165-1c8a-45c3-bf29-8dd521ef1686.PNG)

---
###### Area Selection Screen on Weekends
---

![AreaSelectionScreen](https://user-images.githubusercontent.com/117299908/215600268-e37e6724-958a-47ac-9a2f-3c120b767cf0.PNG)

---
###### Area Selection Screen on Weekdays
---

![AreaSelectionScreen2](https://user-images.githubusercontent.com/117299908/215600235-e55fb052-35af-4973-8bf1-7c99dc3a6194.PNG)

---
###### Seats Selection Screen
---

![SeatSelectionScreen](https://user-images.githubusercontent.com/117299908/215601089-45451ac1-03eb-4c41-b701-0a11aad764c6.PNG)

---
###### Payment Screen
---

![PaymentScreen](https://user-images.githubusercontent.com/117299908/215601114-6692acf4-92d4-4aea-9f58-dd4c856d2028.PNG)

---
###### Post-Payment Screen
---

![Post-PaymentScreen](https://user-images.githubusercontent.com/117299908/215601197-f3eac3ec-a874-4179-8ad6-2763d9c1ba01.PNG)

---
###### Error Screens
---

![ErrorScreen](https://user-images.githubusercontent.com/117299908/215601244-b2e33126-f311-4953-946d-58dfa6824198.PNG)

![ErrorScreen2](https://user-images.githubusercontent.com/117299908/215601245-244c8dfa-1407-42ce-a9aa-7f3792648302.PNG)
