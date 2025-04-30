# CMPS 367 Project 3: Hamming Distance App

This Java Swing-based application allows users to explore and visualize **Hamming distances** between Mesonet station IDs. It features a fully interactive interface to calculate, display, and analyze Hamming distances in a clear and accessible way.

---

##  Project Overview

This application reads station IDs from a data file (`Mesonet.txt`) and lets users:

- Select a reference station from a dropdown.
- Choose a target Hamming distance (1 to 4) via a slider.
- View all stations that match that distance.
- Calculate and display how many stations exist at each distance from 0 to 4.
- Dynamically add new stations to the dataset.
- Visualize results using a custom Free Zone panel (see below).

---

##  Left Panel Features

The **left panel** of the application is the primary interactive section. It includes:

###  Hamming Distance Slider
- Users select a target Hamming distance (1–4).
- A non-editable field shows the current selected value.

###  "Show Station" Button
- Displays all stations with the specified Hamming distance from the selected station.
- Results are shown in a scrollable text area.

###  "Compare With" Dropdown
- Contains all valid 4-letter Mesonet station IDs read from the file.
- Used as the reference point for all distance comparisons.

###  "Calculate HD" Button
- Computes and displays the number of stations at **each Hamming distance (0–4)** from the selected station.
- Results populate five non-editable fields labeled `"Distance 0"` to `"Distance 4"`.

###  "Add Station" Input + Button
- Users can input a **new valid 4-letter station ID** to add to the dataset.
- The station is added in sorted order, and the dropdown is refreshed.

---

##  Data Source

The application loads station IDs from the `Mesonet.txt` file, starting at the `"STID"` header. Each unique ID is stored in a `TreeSet` to ensure sorting and prevent duplicates.

---

##  How to Run

1. Ensure `Mesonet.txt` is in the same directory as your `.java` files.
2. Compile and run `HammingDistanceApp.java`.
3. Interact with the GUI using the provided controls.

---

##  Contributors

- John Warren 
- Prashant Shah 

---
