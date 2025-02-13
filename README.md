

---

# Booth's Algorithm GUI

## Overview
The **Booth's Algorithm GUI** is a Java-based graphical user interface (GUI) application that demonstrates Booth's Multiplication Algorithm for multiplying two signed binary numbers. It allows users to enter a **multiplicand (M)** and a **multiplier (Q)**, view step-by-step calculations, and generate a flowchart representation of the algorithm.

## Features
- **Multiplication Using Booth's Algorithm**: Computes the product of two signed integers using Booth's algorithm.
- **GUI Interface**: Uses Java Swing to provide an interactive experience.
- **Step-by-Step Execution**: Displays detailed calculation steps.
- **Binary Representation**: Shows the binary equivalent of results.
- **Flowchart Display**: Visual representation of Booth’s Algorithm using an image.

## Technical Requirements
- **Programming Language**: Java (JDK 8 or later)
- **GUI Library**: Java Swing
- **Development Tools**: IntelliJ IDEA / Eclipse / NetBeans

## Installation & Execution
1. **Clone or Download the Repository**:
   ```sh
   git clone https://github.com/your-repo/booth-algorithm-gui.git
   cd booth-algorithm-gui
   ```
2. **Compile the Program**:
   ```sh
   javac Course/BoothCalc.java
   ```
3. **Run the Program**:
   ```sh
   java Course.BoothCalc
   ```

## How It Works
1. Run the application, and a GUI window will appear.
2. Enter two numbers (multiplicand **M** and multiplier **Q**).
3. Click **"Show Result"** to compute the product.
4. Click **"Show Steps"** to view step-by-step Booth's Algorithm calculations.
5. Click **"Show Flowchart"** to display the algorithm’s visual representation.
6. The final result is displayed both in **decimal** and **binary format**.

## Project Structure
- **BoothCalc.java**: Main class handling GUI components and user interactions.
- **Main.java**: Implements Booth's Algorithm logic, including binary conversions and arithmetic shifts.

## Future Enhancements
- Implement **error handling** for non-integer inputs.
- Improve GUI with a **graphical step animation**.
- Add support for **larger bit-width multiplication**.

## Author
**Shakti Dheerays S**  
.

---
